package com.nekomata.revolutionbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.info.MemberStat.Achievement;

public class PingCommand extends Command {

	public PingCommand() {
		super.name = "ping";
		super.help = "Pong!";
		super.hidden = true;
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if ((int) (Math.random() * 100) >= 1) {
			event.reply("Pong");
		} else {
			event.reply(":Pog:");
			Achievement.achieve(Achievement.Pog, event.getAuthor());
		}
	}

}
