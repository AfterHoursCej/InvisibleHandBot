package com.nekomata.revolutionbot.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand extends Command {

	public JoinCommand() {
		super.name = "join";
		super.help = "Have the bot join your voice channel";
		super.hidden = false;
		super.arguments = "";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if (!event.getSelfMember().getVoiceState().inVoiceChannel()) {
			VoiceChannel vc = event.getMember().getVoiceState().getChannel();
			AudioManager audioManager = event.getGuild().getAudioManager();
			audioManager.openAudioConnection(vc);
		} else {
			event.reply("Already in voice channel (" + event.getSelfMember().getVoiceState().getChannel().getName() + ")");
		}
	}
}
