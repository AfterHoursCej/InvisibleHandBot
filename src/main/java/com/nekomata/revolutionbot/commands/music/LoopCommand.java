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
				
		        new Replay(manager, event.getTextChannel()), 
		        m 
		);
		
		event.reply("Looping " + info.title);
	}
	
	public class Replay extends TimerTask {
		
		PlayerManager manager;
		TextChannel channel;
		
		public Replay(PlayerManager manager, TextChannel channel) {
			this.manager = manager;
			this.channel = channel;
		}
		
		@Override
        public void run() {
			AudioTrackInfo info = manager.queuedInfo(channel);
			
        	manager.loadAndPlay(channel, info.uri);
        	
        	long m = info.length;
    		
    		new java.util.Timer().schedule( 
    		        new Replay(manager, channel), 
    		        m 
    		);
        }
	}
}
