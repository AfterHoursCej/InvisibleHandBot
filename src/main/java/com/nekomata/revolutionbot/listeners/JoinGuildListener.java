package com.nekomata.revolutionbot.listeners;

import java.util.HashMap;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinGuildListener extends ListenerAdapter {
	
	private static HashMap<Guild, Role> guilds = new HashMap<Guild, Role>();

	public static void addGuild(Guild guild, Role role) {
		guilds.put(guild, role);
	}

	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		if (guilds.containsKey(event.getGuild())) {
			event.getGuild().addRoleToMember(event.getMember(), guilds.get(event.getGuild()));
		}
	}
	
}
