package com.nekomata.revolutionbot;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.reflections.Reflections;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.nekomata.revolutionbot.commands.music.PlayCommand;
import com.nekomata.revolutionbot.info.*;
import com.nekomata.revolutionbot.listeners.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot {
	
	public static JDA jda;
	public static TextChannel console;
	public static Set<Class<? extends Command>> commands;
 	
	public static JoinGuildListener joinGuildListener;
	public static JoinMasterVoiceChannelListener joinMasterVoiceChannelListener;
	
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new ShutDownTask());
		try {
			EnumSet<GatewayIntent> intents = GatewayIntent.getIntents(GatewayIntent.DEFAULT);
			jda = JDABuilder.create(Config.DISCORD_TOKEN.key, intents).setMemberCachePolicy(MemberCachePolicy.DEFAULT).disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS).build();
			jda.awaitReady();
			console = jda.getTextChannelById(707523007400116264L);
		} catch (InterruptedException | LoginException e) {
			e.printStackTrace();
		}
		console.sendMessage("Starting...").queue();
		
		CommandClientBuilder builder = new CommandClientBuilder()
				.setPrefix("!")
				.setOwnerId("236316622153973760")
				.setActivity(Activity.playing("Viva La Revolution"));
		
		gatherCommands();
		
		CommandClient client = builder.build();
		addCommands(client);
		jda.addEventListener(client);
		
		jda.addEventListener(new JoinGuildListener());
		jda.addEventListener(new JoinMasterVoiceChannelListener());
		
		console.sendMessage("Loading Servers...").queue();
		ServerSetting.load();
		
		console.sendMessage("Loading Members...").queue();
		MemberStat.load();
		
		console.sendMessage("Initializing voice...").queue();
		PlayCommand.setup();
		
		console.sendMessage("Started!").queue();
	}
	
	private static void gatherCommands() {
		 Reflections reflections = new Reflections("com.nekomata.revolutionbot.commands");
		 commands = reflections.getSubTypesOf(Command.class);
	}
	
	private static void addCommands(CommandClient client) {
		for (Class<? extends Command> c : commands) {
			try {
				client.addCommand(c.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class ShutDownTask extends Thread {
		 
		@Override
		public void run() {
			ServerSetting.save();
			MemberStat.save();
		}
	}
	
}
