package com.nekomata.revolutionbot.commands.music;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.Error;
import com.nekomata.revolutionbot.Parameter;
import com.nekomata.revolutionbot.music.PlayerManager;

public class VolumeCommand extends Command {

	public VolumeCommand() {
		super.name = "volume";
		super.help = "Changed the volume of the bot";
		super.hidden = false;
		super.arguments = "[Volume]";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		List<Parameter> args = Parameter.parseParameters(event.getArgs());
		if (!args.get(0).isString()) {
			Error.WrongParameterType.send(event.getTextChannel());
			return;
		}
		
		int vol = Integer.parseInt((String) args.get(0).get());
		
		PlayerManager manager = PlayerManager.getInstance();
		
		manager.getGuildMusicManager(event.getGuild()).player.setVolume(vol);
		
		event.reply("Set volume to " + vol);
	}

}
