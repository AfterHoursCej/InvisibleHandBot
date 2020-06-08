package com.nekomata.revolutionbot.commands.music;

import java.util.HashMap;
import java.util.TimerTask;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import net.dv8tion.jda.api.entities.TextChannel;

public class LoopCommand extends Command {

	public static HashMap<TextChannel, Replay> loops = new HashMap<TextChannel, Replay>();
	
	public LoopCommand() {
		super.name = "loop";
		super.help = "Enables/Disables the loop of the current queue";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (loops.getOrDefault(event.getTextChannel(), null) == null) {
			PlayerManager manager = PlayerManager.getInstance();
			
			AudioTrackInfo info = manager.queuedInfo(event.getTextChannel());
			
			long m = info.length;
			
			new java.util.Timer().schedule( 
					
			        new Replay(manager, event.getTextChannel(), info), 
			        m 
			);
			
			event.reply("Looping " + info.title);
		} else {
			loops.get(event.getTextChannel()).stop();
			event.reply("Loop stopped");
		}
	}
	
	public class Replay extends TimerTask {
		PlayerManager manager;
		TextChannel channel;
		AudioTrackInfo info;
		
		java.util.Timer timer;
		
		public Replay(PlayerManager manager, TextChannel channel, AudioTrackInfo info) {
			this.manager = manager;
			this.channel = channel;
			this.info = info;
			loops.put(channel, this);
		}
		
		@Override
        public void run() {
			manager.loadAndPlay(channel, info.uri);
			
        	long m = info.length;
        	
    		timer = new java.util.Timer();
    		timer.schedule( 
    		        new Replay(manager, channel, info), 
    		        m 
    		);
        }
		
		public void stop() {
			timer.cancel();
		}
	}
}
