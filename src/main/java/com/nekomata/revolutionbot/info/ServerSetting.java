package com.nekomata.revolutionbot.info;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.nekomata.revolutionbot.Bot;
import com.nekomata.revolutionbot.info.files.Json;
import com.nekomata.revolutionbot.listeners.JoinGuildListener;
import com.nekomata.revolutionbot.listeners.JoinMasterVoiceChannelListener;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class ServerSetting {
	
	/*
	 * Static
	 */
	private static HashMap<Long, ServerSetting> servers = new HashMap<Long, ServerSetting>();
	
	public static void load() {
		File folder = new File("./assets/data/servers");
		if (!folder.exists()) folder.mkdir();
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles != null) {
			for (File f : listOfFiles) {
				ServerSetting s = new Json<ServerSetting>(ServerSetting.class, f).load();
				servers.put(s.ID, s);
			}
		}
		
		Collection<ServerSetting> allServer = servers.values();
		for (ServerSetting s : allServer) {
			s.start();
		}
	}
	
	public static void save() {
		Collection<ServerSetting> allServer = servers.values();
		for (ServerSetting s : allServer) {
			new Json<ServerSetting>(ServerSetting.class, "servers", s.ID).save(s);;
		}
	}
	
	public static ServerSetting getServerSetting(Guild guild) {
		ServerSetting s = null;
		try {
			s = servers.get(guild.getIdLong());
		} catch (NullPointerException e) {}
		return s == null? new ServerSetting(guild) : s;
	}
	
	/*
	 * Object
	 */
	public Long ID;
	
	public Long defaultRole;
	
	public ArrayList<VoiceChannel> masterVoiceChannels;
	
	public ServerSetting() {} //Default constructor for Jackson
	
	public ServerSetting(Guild guild) {
		this.ID = guild.getIdLong();
		servers.put(ID, this);
		
		defaultRole = null;
		masterVoiceChannels = new ArrayList<VoiceChannel>(3);
	}
	
	public void start() {
		JoinMasterVoiceChannelListener.addChannels(masterVoiceChannels);
		JoinGuildListener.addGuild(Bot.jda.getGuildById(ID), Bot.jda.getRoleById(defaultRole));
	}
	
	public Role getDeafaultRole() {
		return Bot.jda.getRoleById(defaultRole);
	}
}