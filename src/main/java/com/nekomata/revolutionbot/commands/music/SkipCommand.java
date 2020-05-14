package com.nekomata.revolutionbot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.music.PlayerManager;

public class SkipCommand extends Command {

	public SkipCommand() {
		super.name = "skip";
		super.help = "Skip the current playing song";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		PlayerManager manager = PlayerManager.getInstance();
		
		String name = manager.queued(event.getTextChannel());
		event.reply("Skipping " + name + "...");
		manager.skip(event.getTextChannel());
	}

}
