package com.nekomata.revolutionbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class EndCommand extends Command {

	public EndCommand() {
		super.name = "end";
		super.help = "Ends the bot";
		super.hidden = true;
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (event.getAuthor().getIdLong() == 236316622153973760L) {
			event.reply("Ending...");
			System.exit(0);
		} else {
			event.reply("Author does not have authorization to execute End");
		}
	}

}
