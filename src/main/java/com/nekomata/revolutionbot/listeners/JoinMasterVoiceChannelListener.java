package com.nekomata.revolutionbot.listeners;

import java.util.ArrayList;
import java.util.List;

import com.nekomata.revolutionbot.Bot;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinMasterVoiceChannelListener extends ListenerAdapter {
	
	private static List<VoiceChannel> allMasterChannels = new ArrayList<VoiceChannel>(3);
	
	public static void addChannels(List<VoiceChannel> masterChannels) {
		allMasterChannels.addAll(masterChannels);
	}

	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		if (allMasterChannels.contains(event.getChannelJoined())) {
			VoiceChannel newVC = event.getChannelJoined().createCopy().complete();
			newVC.getManager().setName(event.getMember().getNickname() + "'s VC").complete();
			Bot.jda.addEventListener(new LeaveCreatedVoiceChannelListner(newVC));
		}
	}
	
	private class LeaveCreatedVoiceChannelListner extends ListenerAdapter {
		
		private final VoiceChannel VC;
		
		public LeaveCreatedVoiceChannelListner(VoiceChannel vc) {
			this.VC = vc;
		}
		
		@Override
		public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
			if (event.getChannelLeft().equals(VC) && VC.getMembers().size() == 0) {
				VC.delete().complete();
			}
				
		}
		
	}
	
}
