package com.nekomata.revolutionbot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.music.PlayerManager;

import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand extends Command {

	public LeaveCommand() {
		super.name = "leave";
		super.help = "Have the bot leave its voice channel";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (event.getSelfMember().getVoiceState().inVoiceChannel()) {
			PlayerManager manager = PlayerManager.getInstance();
			manager.stop(event.getTextChannel());
			
			AudioManager audioManager = event.getGuild().getAudioManager();
			audioManager.closeAudioConnection();
		} else {
			event.reply("Not in Voice Channel");
		}
	}

}
