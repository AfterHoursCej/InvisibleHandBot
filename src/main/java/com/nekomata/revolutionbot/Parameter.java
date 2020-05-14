package com.nekomata.revolutionbot;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Parameter {
	
	public static List<Parameter> parseParameters(String parameterString) {
		String[] rawParams = parameterString.split(" ");
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		for (String s : rawParams) {
			if (s.startsWith("<") && s.endsWith(">")) {
				if (s.charAt(1) == '@') {
					if (s.charAt(2) == '!') {
						//User
						parameters.add(new Parameter(Bot.jda.retrieveUserById(s.substring(3, s.length()-1)).complete()));
					} else if (s.charAt(2) == '&') {
						//Role
						parameters.add(new Parameter(Bot.jda.getRoleById(s.substring(3, s.length()-1))));
					}
				} else if (s.charAt(1) == '#') {
					//TextChannel
					parameters.add(new Parameter(Bot.jda.getTextChannelById(s.substring(2, s.length()-1))));
				} else if (s.charAt(1) == ':') {
					//Emoji
					parameters.add(new Parameter(Bot.jda.getEmoteById(s.substring(s.lastIndexOf(':')+1, s.length()-1))));
				}
			} else if (s.charAt(0) == '@') {
				if (s.equals("@everyone")) {
					//@everyone
					parameters.add(new Parameter(MassPings.EVERYONE));
				} else if (s.equals("@here")) {
					//@here
					parameters.add(new Parameter(MassPings.HERE));
				}
			} else {
				//String
				parameters.add(new Parameter(s));
			}
		}
		return parameters;
	}
	
	public enum MassPings {
		EVERYONE,
		HERE
	}
	
	private Object param;
	private Class<?> type;
	
	public Parameter(String argument) {
		param = argument;
		type = String.class;
	}
	
	public Parameter(User argument) {
		param = argument;
		type = User.class;
	}
	
	public Parameter(Role argument) {
		param = argument;
		type = Role.class;
	}
	
	public Parameter(TextChannel argument) {
		param = argument;
		type = TextChannel.class;
	}
	
	public Parameter(Emote argument) {
		param = argument;
		type = Emote.class;
	}
	
	public Parameter(MassPings argument) {
		param = argument;
		type = MassPings.class;
	}
	
	public boolean isString() {
		return type == String.class;
	}
	
	public boolean isUser() {
		return type == User.class;
	}
	
	public boolean isRole() {
		return type == Role.class;
	}
	
	public boolean isTextChannel() {
		return type == TextChannel.class;
	}
	
	public boolean isEmote() {
		return type == Emote.class;
	}
	
	public boolean isEveryone() {
		return param == MassPings.EVERYONE;
	}
	
	public boolean isHere() {
		return param == MassPings.HERE;
	}
	
	public Object get() {
		return param;
	}
}
