package com.nekomata.revolutionbot.commands.music;

import java.util.TimerTask;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import net.dv8tion.jda.api.entities.TextChannel;

public class LoopCommand extends Command {

	public LoopCommand() {
		super.name = "loop";
		super.help = "Enables/Disables the loop of the current queue";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		PlayerManager manager = PlayerManager.getInstance();
		
		AudioTrackInfo info = manager.queuedInfo(event.getTextChannel());
		
		long m = info.length;
		
		new java.util.Timer().schedule( 
				
		        new Replay(manager, event.getTextChannel(), info), 
		        m 
		);
		
		event.reply("Playing " + info.title + " after " + m + " milliseconds");
	}
	
	public class Replay extends TimerTask {
		
		PlayerManager manager;
		TextChannel channel;
		AudioTrackInfo info;
		
		public Replay(PlayerManager manager, TextChannel channel, AudioTrackInfo info) {
			this.manager = manager;
			this.channel = channel;
			this.info = info;
		}
		
		@Override
        public void run() {
        	long m = info.length;
    		
    		new java.util.Timer().schedule( 
    		        new Replay(manager, channel, info), 
    		        m 
    		);
        }
	}
}
