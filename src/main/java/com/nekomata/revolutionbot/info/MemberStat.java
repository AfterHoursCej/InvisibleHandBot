package com.nekomata.revolutionbot.info;

import java.awt.Color;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nekomata.revolutionbot.info.files.Json;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

@JsonDeserialize
public class MemberStat {
	
	/*
	 * Static
	 */
	private static HashMap<Long, MemberStat> members = new HashMap<Long, MemberStat>();
	
	public static void load() {
		File folder = new File("./assets/data/members");
		if (!folder.exists()) folder.mkdir();
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles != null) {
			for (File f : listOfFiles) {
				MemberStat s = new Json<MemberStat>(MemberStat.class, f).load();
				members.put(s.ID, s);
			}
		}
	}
	
	public static void save() {
		for (MemberStat m : members.values()) {
			new Json<MemberStat>(MemberStat.class, "members", m.ID).save(m);
		}
	}
	
	public static MemberStat getMemberStat(Member member) {
		MemberStat m = null;
		try {
			m = members.get(member.getIdLong());
		} catch (NullPointerException e) {}
		return m == null? new MemberStat(member) : m;
	}
	
	public static MemberStat getMemberStat(User user) {
		MemberStat m = null;
		try {
			m = members.get(user.getIdLong());
		} catch (NullPointerException e) {}
		return m == null? new MemberStat(user) : m;
	}
	
	/*
	 * Object
	 */
	public Long ID;
	
	//Waifu Stats
	public double rating;
	public Attribute attribute;
	
	public Map<Achievement, Boolean> achievements;
	
	public MemberStat() {} //Default constructor for Jackson
	
	public MemberStat(Member member) {
		this.ID = member.getIdLong();
		members.put(ID, this);
		
		rating = 0.0;
		attribute = null;
		
		achievements = new HashMap<Achievement, Boolean>();
		for (Achievement a : Achievement.values()) {
			achievements.put(a, false);
		}
	}
	
	public MemberStat(User user) {
		this.ID = user.getIdLong();
		members.put(ID, this);
		
		rating = 0.0;
		attribute = null;
		
		achievements = new HashMap<Achievement, Boolean>();
		for (Achievement a : Achievement.values()) {
			achievements.put(a, false);
		}
	}
	
	/*
	 * Enums
	 */
	public enum Attribute {
		None("Catego— Umm.. I mean... you don't have a dere type... I literally mean no dere type."), 
		Bakadere("Category: Kind; A bakadere refers to a character who is very clumsy and stupid. More often than not, they lack common sense."), 
		Bodere("Category: Arrogant; A bodere refers to a character who is usually shy around those they’re infatuated with, and get embarrassed easily and will lash out to hide their shyness. Bodere types don’t know how to handle their embarrassment and use their fists instead of their words. They are similar to tsunderes. The main difference is the lack of dialogue with a bodere. They are like a combination of tsundere and dandere."), 
		Byoukidere("Category: Rare/Kind; A byoukidere refers to a character who is usually very sweet and kind, but suffers from a physical disease, usually a fatal one. They usually have two fates: one is that they die, and the other is they get some miraculous cure (usually at the hands of the hero). In the case of the latter, they usually end up being the one chosen by the hero. Many byoukideres are confined to wheelchairs or need assistance in getting around. They are often frail and weak physically. The disease causes the byoukidere to become feeble."),
		Dandere("Category: Kind; A dandere type refers to a character who is often silent to themselves. It may be due to shyness or just because they’re the quiet type. However, when alone with the person they are attracted to, they usually come out of their shell and become more loving. Danderes often want to be social, but they are very, very embarrassed, too shy to come out of their comfort zones, or afraid of being hurt."),
		Darudere("Category: Distant; A darudere refers to a character who is often very lazy and dull. That is, despite her massive complaints about having to do anything, she will lift a finger if it's for someone she likes. Darudere characters would rather sit around and show no emotion towards anyone, including their love interest."), 
		Deredere("Category: Kind; Deredere characters are very sweet and energetic. They are usually cheerful and happy and they tend to spread happiness to the ones around them. No matter what may happen, they quickly revert to their cheerful self."), 
		Dorodere("Category: Violent; A dorodere, sometimes spelled doredere, refers to a character who acts cute and sweet on the outside, but is deeply disturbed on the inside. Dorodere characters have Yangire or Yandere thoughts, but usually don't act on them. They appear sweet and lovable on the outside, but are twisted or disturbed on the inside for whatever reason. Doroderes could also have mental problems, illnesses and disorders."),
		Giseidere("Category: Rare/Sad/Violent; A 'Giseidere' refers to a character with \"Stockholm Syndrome\", \"BPD (Borderline Personality Disorder)\", and/or EUPD (Emotionally Unstable Personality Disorder). They often mistake abuse and mistreatment for love. They are often paired with a yandere , a yangire, dorodere, or a sadodere abusing them."),
		Hajidere("Category: Kind; A hajidere refers to a character who is very nervous and embarrassed around their crush. Hajidere characters are basically a dandere set to extreme. They will constantly blush near their love interest, and might even faint from being so bashful. However, they can be social and outgoing in front of everybody else, unlike a Dandere."), 
		Himedere("Category: Arrogant; A Himedere refers to a character who wishes to be treated like a princess by the person they love, even if they aren’t actual royalty. Himedere characters have a princess complex. They usually get any kind of work to be done by other people, giving orders to everyone around them. They put themselves first in any situation, not caring about others, except the one they love. They always want perfection and success everywhere, from everyone and themselves."), 
		Hinedere("Category: Rare/Arrogant/Distant; A hinedere refers to characters who have cynical world views, are cold-hearted, and highly arrogant. Hinedere characters have a soft side deep down that may reveal itself after their love interest breaks through."), 
		Kamidere("Category: Arrogant; A kamidere refers to a character with a god complex. Kamidere characters believe they are a god, they are also highly arrogant and proud, and aren’t afraid to speak their minds and show everyone how right they are. They believe that they are the most perfect and infallible beings, and think they should get special attention and priority."), 
		Kanedere("Category: Rare/Arrogant; A kanedere refers to a character who is attracted to others with money or status. Kanedere characters are the anime and manga equivalent of a gold digger. They often just care about how much money a person has, and nothing else. However, a true love can break their habit."),
		Kekkondere("Category: Rare; A kekkondere refers to a character who wants to get married right away. This is a very rare dere type. Kekkondere characters decide immediately when they meet a person that they're the ultimate person for them, and immediately starts planning their marriage. They want them to get married to them right away. They will often go to absurd lengths to get their person to want them, and usually ends up turning them off initially."),
		Kuudere("Category: Distant/Kind; A kuudere refers to a character who is often cold, blunt, and cynical. They may seem very emotionless on the outside, but on the inside they’re very caring — at least when it comes to the ones they love."), 
		Masodere("Category: Violent; Pain and pleasure go hand in hand for some. Masodere loves dishing out pain, but they all love to receive it (especially from their love interest). Aka Masochist. \"Sticks and stones may break my bones but whips and chains excite me\" "),
		Mayadere("Category: Violent; A mayadere type refers to a character who is often a dangerous antagonist of a series, but switches sides after falling in love with another character."),
		Megadere("Category: Rare/Kind; A Megadere is basically obsessed with their love interest, but not in the insane way a yandere is. They constantly fangirl/fanboy over their love interest whenever they can, and might stalk them on occasion. Despite their obsessiveness, megadere characters don't take their obsessive love to an extent where it causes them to commit violent acts or manipulate their love interest, unlike a Yandere."),
		Nemuidere("Category: Rare/Distant; A nemuidere refers to a character that spends much of his/her time sleeping. Nemuidere characters tend to be very lazy and laconic in nature as well as intelligent and artistic, if they weren't such a sleepyhead. Nemuidere's are not a very common type. They are somewhat similar to the Darudere, but unike the Darudere, they sleep a lot more, and sometimes show interest in the one they love. Often the hero has to motivate her/him through love to become less sleepy. "),
		Nyandere("Category: Rare; A nyandere may be: \n - A girl that starts acting like a cat when dere (grows fang, cat-ears, even if not really, but just drawn so) \n - A girl that acts composed, but when she sees a cat she becomes dere \n - A tsundere with nekomimi (cat ears) \n - An actual cat (or anthro catgirl) that's dere for her owner. \nNyandere characters often times add “nyan” in their sentences. They might even be nekojin, which is part cat and part human."),
		Oujidere("Category: Arrogant; A Oujidere refers to a character who wishes to be treated like a prince by the person they love, even if they aren’t actual royalty. Oujidere characters have a prince complex. They usually get any kind of work to be done by other people, giving orders to everyone around them. They put themselves first in any situation, not caring about others, except the one they love. They always want perfection and success everywhere, from everyone and themselves."),
		Oujodere("Category: Kind; Oujodere is a \"lady-like\" type of character, who's prim and proper with a strict exterior, but a sweet inside. Oujodere comes from the word \"oujo\" or \"oujou\" which means \"princess\", but different from himedere, oujodere isn't arrogant nor bossy, but is a lot more kind. Unlike himedere, they don't demand to be treated like a princess. They just behave like the proper perfect fairy-tale-like princess. Oujodere is a more mature version of deredere, where deredere is more energetic and cheerful, while oujodere is more calm and collected."),
		Sadodere("Category: Violent; Sadodere characters are warped troublemakers who take pleasure in manipulating people’s feelings, causing pain, and sometimes humiliating (others') love. Sadodere characters will abuse their love interest emotions. Sadodere characters are sadists and they enjoy causing pain to people’s emotions. They have sadistic tendencies."),
		Shundere("Category: Sad; Shundere refers to a character who is sad and very depressed. Shunderes often are sad from the start and don't have much of a reason, unlike utsuderes. While a full smile on shundere character's face might be out of the question, their love interest can help them open up and feel accepted. They have a tendency to ignore and avoid others. They are sad all the time. Second type of Shundere is that they don’t show their sadness to people and hide it all the time but they are actually really sad all the time."),
		Teasedere("Category: Kind; Teasedere characters are mischievous at heart and sort of like a flirt as they tease people they like. Inside, however, they care a lot for their loved one, and tease them even more. Teasederes are often an extension of tsundere. The majority of the teasedere characters are also tsunderes."),
		Thugdere("Category: Violent/Arrogant; Thugdere is when a character often mistreats other people including their lover/love interest, often physically or emotionally harming them, but can also be quite affectionate toward them. The \"thugness\" is often a way they hide their sweet, caring, and loving side, because they may often think their sweet side makes them look weak or foolish."),
		Tsundere("Category: Arrogant/Distant/Kind; A tsundere is a character who is initially cold and even hostile towards their love interest, before gradually showing a warmer side over time, and keeps switching back and forth between the two. They are also passionate, wild, and aggressive too. Often, tsunderes are embarrassed by or don't know what to do with their romantic feelings and become even more belligerent and egotistical than normal. Their constant inner struggle between their pride and love is the key to how these characters act."),
		Undere("Category: Rare/Kind; An undere refers to a character who says yes, or maybe, to pretty much everything the one they love says. Undere characters agree as much as possible to become as close as they can to their love interest. They do pretty much whatever their love wants."),
		Utsudere("Category: Sad; An utsudere refers to a character who is often sad and depressed. They are not as sad as the shundere, but are still very similar. Utsuderes have a reason to be depressed, while shunderes are depressed from the start. Utsudere characters have a reason for the character’s despair, such as being bullied at school, a loved one dying, insecurity, etc. However, almost always, they’ll gradually begin to trust others and build relationships after meeting said kind people."),
		Yandere("Category: Violent/Kind; A yandere refers to a character who starts out nice and sweet, but eventually becomes dark and obsessive over the one they love. They become stalkers and use violence on, and possibly even murder, any person who gets close to their love interest — even if they’re too shy to simply speak with that person they have a crush on. Yandere girls are what one would call crazy girls. No, I don't mean like dorky or stunt-loving girls, I mean the batshit psychotic jealous bitch that will murder your entire family and your dog just to stay with you."),
		Yottadere("Category: Any; A yottadere is a girl type that is noteworthy for her constant drinking of alcohol. Yottaderes are rarely seen without some form of alcohol in their hands (usually it's beer or saké). It is very rare that a yottadere girl will be sober, and she only becomes sober when it means getting her man (Or Woman). There are several different types of yottadere girls, ranging from serious and dedicated to wild and uninhibited. Almost all of them undergo some personality change when they get drunk or get sober. Officially, they are not separated into different categories. However, they tend to do things when drunk that they end up regretting if they get sober. Some get very angry and violent when drunk; others just pass out. If she finds the right man (Or Woman), she usually will stop drinking a lot suddenly.");
		
		private String definition;
		
		private Attribute(String definition) {
			this.definition = definition;
		}
		
		public String define() {
			return this.definition;
		}
		
	}
	
	public enum Achievement {
		Pog(":pog:"),
		None("Attempt to define nothing.");
		
		public static void achieve(Achievement a, User user) {
			MemberStat stats = getMemberStat(user);
			if (stats.achievements.get(a) != true) {
				stats.achievements.put(a, true);
				MessageEmbed embed = new EmbedBuilder()
						.setColor(new Color(0, 255, 128))
						.setTitle("**Acheivement Unlocked!**")
						.setDescription(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()))
						.addField("**" + a.toString() + "**", a.description, false)
						.setImage("attachment://wow.png").build();
				user.openPrivateChannel().complete().sendMessage(embed).addFile(new File("./assets/img/wow.png")).queue();
			}
		}
		
		private String description;
		
		private Achievement(String description) {
			this.description = description;
		}
	}
}
