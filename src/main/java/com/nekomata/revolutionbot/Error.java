package com.nekomata.revolutionbot;

import net.dv8tion.jda.api.entities.TextChannel;

public enum Error {
	WrongParameterType("Wrong parameter type"),
	Authorization("Author does not have authorization to execute command"),
	TooManyArguments("Too many arguments"),
	NotEnoughArguments("Not enough arguments"),
	UnknownSubcommand("Unknown subcommand"),
	UserNotInVoiceChannel("Author not in voice channel");
	
	String message;
	
	private Error(String message) {
		this.message = message;
	}
	
	public void send(TextChannel channel) {
		channel.sendMessage(message).queue();
	}
}
