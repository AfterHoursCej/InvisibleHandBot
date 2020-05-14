package com.nekomata.revolutionbot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.music.PlayerManager;

public class QueueCommand extends Command {

	public QueueCommand() {
		super.name = "queue";
		super.help = "See what song is playing";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		PlayerManager manager = PlayerManager.getInstance();
		
		String name = manager.queued(event.getTextChannel());
		event.reply("Current playing song: " + name);
	}

}
