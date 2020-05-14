package com.nekomata.revolutionbot.commands;

import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.nekomata.revolutionbot.Error;
import com.nekomata.revolutionbot.Functions;
import com.nekomata.revolutionbot.Parameter;
import com.nekomata.revolutionbot.info.MemberStat;
import com.nekomata.revolutionbot.info.MemberStat.Achievement;
import com.nekomata.revolutionbot.info.MemberStat.Attribute;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

public class WaifuCommand extends Command {
	
	public WaifuCommand() {
		super.name = "waifu";
		super.help = "Gets the waifu stats of the user specified";
		super.hidden = false;
		super.arguments = "[user] or [reset/define] [user/attribute]";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		List<Parameter> args = Parameter.parseParameters(event.getArgs());
		/*
		 * Base Command
		 */
		if (args.size() == 1) {
			//Pieces
			User user = null;
			if (args.get(0).isUser()) {
				user = (User) args.get(0).get();
			} else if (args.get(0).isString()) {
				user = Functions.getUserByDisplayName(event.getTextChannel(), (String) args.get(0).get());
				if (user == null) {
					return;
				}
			} else {
				Error.WrongParameterType.send(event.getTextChannel());
				return;
			}
			MemberStat stats = MemberStat.getMemberStat(user);
			
			if (stats.attribute == null) { //If stats has not been set yet
				
				newStats(user, stats);
				
			}
			
			MessageEmbed embed = new EmbedBuilder()
				    .setTitle("Waifu Rater")
				    .setDescription("Rates how **Waifu** the mentioned user is!")
				    .addField("Rating: ", user.getAsMention(), false)
				    .addField("Overall Waifu Rating", stats.rating + "%", true)
				    .addField("Waifu Attribute", stats.attribute.toString(), true)
				    .setColor(new Color(77, 26, 127))
				    .setImage(user.getAvatarUrl())
				    .setThumbnail("attachment://waifu.png").build();
			event.getTextChannel().sendMessage(embed)
					.addFile(new File("./assets/img/waifu.png"))
					.queue();
			
 		} else if (args.get(0).isString()) { //Else if the first parameter is a subcommand
 			/*
 			 * Reset
 			 */
 			if (((String) args.get(0).get()).equalsIgnoreCase("reset")) { //If the subcommand is 'reset'
 				
 				if (event.getAuthor().getIdLong() == 236316622153973760L) {
 				
	 				User user = null;
	 				if (args.get(1).isUser()) {
	 					user = (User) args.get(1).get();
	 				} else if (args.get(1).isString()) {
	 					user = Functions.getUserByDisplayName(event.getTextChannel(), (String) args.get(1).get());
	 					if (user == null) {
	 						return;
	 					}
	 				}
	 	 			MemberStat stats = MemberStat.getMemberStat(user);
	 	 			
	 				newStats(user, stats);
	 				
	 				event.reply("Successfully reset " + user.getAsMention() + " 's waifu stats");
 				} else {
 					Error.Authorization.send(event.getTextChannel());
 				}
 				
			/*
 			 * Define
 			 */
 			} else if (((String) args.get(0).get()).equalsIgnoreCase("define")) { //If the subcommand is 'define'
 				
 				String attribute = (String) args.get(1).get();
 				if (!attribute.equalsIgnoreCase("None")) {
	 				for (Attribute a : Attribute.values()) {
	 					if (a.toString().equalsIgnoreCase(attribute)) {
	 						String definition = a.define();
	 						MessageEmbed embed = new EmbedBuilder()
	 							    .setTitle(a.toString())
	 							    .setDescription(definition.substring(definition.indexOf(';')+1))
	 							    .addField("Categories", definition.substring(definition.indexOf(':')+2, definition.indexOf(';')), false)
	 							    .setColor(new Color(77, 26, 127))
	 							    .setFooter("Source: https://the-dere-types.fandom.com/wiki").build();
	 						event.reply(embed);
	 					}
	 				}
 				} else {
 					String definition = Attribute.None.define();
						MessageEmbed embed = new EmbedBuilder()
							    .setTitle("None")
							    .setDescription(definition)
							    .setColor(new Color(77, 26, 127)).build();
						event.reply(embed);
						Achievement.achieve(Achievement.None, event.getAuthor());
 				}
 			}
 		}
	}
	
	private void newStats(User user, MemberStat stats) {
		if (user.isBot()) { //If member is a bot
			stats.attribute = Attribute.None;
			if (user.getIdLong() == 694676436514111589L) {
				stats.rating = 99.9;
			} else {
				stats.rating = new BigDecimal((Math.random() * 100) / 3).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
			}
		} else if (user.getIdLong() == 236316622153973760L) {
			stats.attribute = Attribute.Kuudere;
			stats.rating = 100.0;
		} else {
			Attribute[] attributes = Attribute.values();
			stats.attribute = attributes[(int)(Math.random() * attributes.length)];
			stats.rating = new BigDecimal(Math.random() * 100).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
		}
	}
}