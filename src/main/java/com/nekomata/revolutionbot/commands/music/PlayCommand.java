package com.nekomata.revolutionbot.commands.music;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.Error;
import com.nekomata.revolutionbot.Parameter;
import com.nekomata.revolutionbot.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class PlayCommand extends Command {

	private static AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
	
	public static void setup() {
		AudioSourceManagers.registerRemoteSources(playerManager);
	}
	
	public PlayCommand() {
		super.name = "play";
		super.help = "Start a song";
		super.hidden = false;
		super.arguments = "[youtube link]";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		List<Parameter> args = Parameter.parseParameters(event.getArgs());
		if (!args.get(0).isString()) {
			Error.WrongParameterType.send(event.getTextChannel());
			return;
		}
		
		if (!event.getSelfMember().getVoiceState().inVoiceChannel()) {
			VoiceChannel vc = event.getMember().getVoiceState().getChannel();
			AudioManager audioManager = event.getGuild().getAudioManager();
			audioManager.openAudioConnection(vc);
		}
		
		PlayerManager manager = PlayerManager.getInstance();
		
		manager.loadAndPlay(event.getTextChannel(), (String) args.get(0).get());
		
		manager.getGuildMusicManager(event.getGuild()).player.setVolume(10);
	}

}
