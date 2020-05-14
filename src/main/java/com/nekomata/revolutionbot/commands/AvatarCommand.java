package com.nekomata.revolutionbot.commands;

import java.io.File;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.Error;
import com.nekomata.revolutionbot.Functions;
import com.nekomata.revolutionbot.Parameter;

import net.dv8tion.jda.api.entities.User;

public class AvatarCommand extends Command {
	
	public AvatarCommand() {
		super.name = "avatar";
		super.help = "Gets the avatar of the user specified";
		super.hidden = false;
		super.arguments = "[user]";
	}


	@Override
	protected void execute(CommandEvent event) {
		List<Parameter> args = Parameter.parseParameters(event.getArgs());
		if (args.size() > 1) {
			Error.TooManyArguments.send(event.getTextChannel());
			return;
		} else if (args.size() < 1) {
			Error.NotEnoughArguments.send(event.getTextChannel());
			return;
		}
		File f = null;
		if (args.get(0).isUser()) {
			f = Functions.getAvatarFileByUser((User) args.get(0).get());
		} else {
			f = Functions.getAvatarFileByUser(Functions.getUserByDisplayName(event.getTextChannel(), (String) args.get(0).get()));
		}
		if (f != null) {
			String filetype = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf('.'));
			event.reply(f, event.getAuthor().getName() + filetype);
			f.delete();
		}
	}

}
