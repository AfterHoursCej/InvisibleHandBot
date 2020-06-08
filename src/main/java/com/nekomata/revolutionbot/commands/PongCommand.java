package com.nekomata.revolutionbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PongCommand extends Command {

	public PongCommand() {
		super.name = "pong";
		super.help = "@Lav";
		super.hidden = true;
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().getIdLong() == 306232481240055851L) {
			event.reply((event.getAuthor().getAsMention()));
		}
	}

}
