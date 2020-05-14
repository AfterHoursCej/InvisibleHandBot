package com.nekomata.revolutionbot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.Bot;
import com.nekomata.revolutionbot.Error;
import com.nekomata.revolutionbot.Functions;
import com.nekomata.revolutionbot.Parameter;
import com.nekomata.revolutionbot.info.ServerSetting;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.Guild;

public class SettingsCommand extends Command {
	
	public SettingsCommand() {
		super.name = "setting";
		super.help = "Changes guild settings";
		super.hidden = false;
		super.arguments = "[setting] [value]";
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Parameter> args = Parameter.parseParameters(event.getArgs());
		
		Guild guild = event.getGuild();
		ServerSetting settings = ServerSetting.getServerSetting(guild);
		
		if (args.get(0).isString()) {
			String subcommand = ((String) args.get(0).get()).toLowerCase();
			switch (subcommand) {
				case "defaultrole":
					if (args.get(1).isRole()) {
						settings.defaultRole = ((Role) args.get(1).get()).getIdLong();
					} else if (args.get(1).isString()) {
						settings.defaultRole = Functions.getRoleByName(event.getTextChannel(), ((String) args.get(1).get())).getIdLong();
					} else {
						Error.WrongParameterType.send(event.getTextChannel());
						break;
					}
					event.reply("Default role has been set to " + Bot.jda.getRoleById(settings.defaultRole).getAsMention());
					break;
				default:
					Error.UnknownSubcommand.send(event.getTextChannel());
			}
		} else {
			Error.WrongParameterType.send(event.getTextChannel());
		}
	}
}
