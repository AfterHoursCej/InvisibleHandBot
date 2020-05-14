package com.nekomata.revolutionbot;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class Functions {

	public static File getAvatarFileByUser(User user) {
		System.out.println("System - Getting Avatar: " + user.getName() + "#" + user.getDiscriminator());
		
		String avatar = user.getAvatarUrl();
		int idx1 = avatar.lastIndexOf('/');
		String filename = avatar.substring(idx1+1);
		
		File temp = new File("./temp/" + filename);
		try {
			FileUtils.copyURLToFile(new URL(avatar), temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        return temp;
	}
	
	public static User getUserByDisplayName(TextChannel textChannel , String displayName) {
		System.out.println("System - Getting User By Display Name: " + displayName);
		
		ArrayList<Member> m = new ArrayList<>();
		m.addAll(textChannel.getGuild().getMembersByName(displayName, true));
		List<Member> temp = textChannel.getGuild().getMembersByNickname(displayName, true);
		for (Member member : temp) {
			if (!m.contains(member)) {
				m.add(member);
			}
		}
		if (m.size() > 1) {
			textChannel.sendMessage("Too many members share the same name, using the first one.").queue();
		} else if (m.size() == 0) {
			textChannel.sendMessage("No members found by that name.").queue();
			return null;
		}
		
		return ((Member) m.toArray()[0]).getUser();
	}

	public static Role getRoleByName(TextChannel textChannel, String roleName) {
		System.out.println("System - Getting Role By Name: " + roleName);
		
		List<Role> u = textChannel.getGuild().getRolesByName(roleName, true);
		if (u.size() > 1) {
			textChannel.sendMessage("Too many roles share the same name, using the first one.").queue();
		} else if (u.size() == 0) {
			textChannel.sendMessage("No roles found by that name.").queue();
			return null;
		}
		
		return (Role) u.toArray()[0];
	}
}
