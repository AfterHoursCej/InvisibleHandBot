# RevolutionBot

A Discord Bot for my Discord Server, The [Syrup Society](https://discord.gg/8sq5pgc)

The Discord Server is meant to be slowly transformed to a Democratic-Capitalist society, defying the norms of the monarchy/dictatorship that normal discord servers are.

## Discord

This Discord Bot, while being made in a way to be used in any discord server, is private and is only in The [Syrup Society](https://discord.gg/8sq5pgc).
Although, if you are interested and want to invite this bot to your server, DM me on discord at **Nekomata#0001**

## Changelog

### 0.2.1

* Added Command(s)
	* Loop
		* Loops current playing song
	* Volume {Volume}
		* Chanes music volume

### 0.2.0

* Major reworks to code
	* Switched to JDA API (from Javacord)
	* Now uses JDA-Utilities to do commands instead of custom command classes
* Added Command(s)
	* Voice Commands
		* Join
			* Joins the channel the user is currently in if not already in a channel
		* Play {YouTube/SoundCloud/Bandcamp/Vimeo/Twitch link}
			* Joins the channel the user is currently in if not already in a channel
			* Plays the specified song
		* Leave
			* Leaves the voice channel if in one
		* Queue
			* Says the current playing song
		* Skip
			* Skips the current playing song
* Changed Command(s)
	* Define {Word}
		* Now Waifu Define {word}
	* ResetWaifu {User}
		* Now Waifu Reset {User}
* Removed Command(s)
	* Changing server prefix is now not a settings option
* Added Feature(s)
	* Achievements
		* Unlockable achievements are now achievable
		* These achievements are secret but upon unlocking them the user will get DM confirmation
		* These achievements are unlocked through publicly known commands

### 0.1.5

* Fixed bug(s)
	* Bot not working in Syrup Society
		* Fixed and added preventative measures to the situation that caused it
* Added Command(s)
	* Pong
		* Unique Command (Can only be used by specific users)
			* Can only be used by Lav#0421
		* Pings Lav#0421
* The bot has been put up on a VPS and should run 24/7

### 0.1.4

* Added Command(s)
	* Settings {setting} {value}
		* Current settings are:
			* Prefix (Default: !)
			* DefaultRole (Default: Null)
				* DefaultRole's value can be a role mention or silent called (Name of role)
		* Currently requires author level perms (Bot author @Nekomata#0001) but this will be changed in the near future
* Added functional default role
	* On join, the new member will automatically be given the default role
* Fixed bug(s)
	* Waifu reset command did not require author level perms
* Changed past version numbers


### 0.1.3

* Changed Commands
	* Resetwaifu {Username Mention}
		* Is now a subcommand of Waifu
		* Waifu reset {Username Mention}

### 0.1.2

* Added Commands
  	* Define {Word}
    	* Currently only defines Waifu Attributes
* Fixed Bugs
  	* Avatar command not downloading picture
* Reworked entire command structure
  	* Conforms to OOP (Object Oriented Principles)
  	* Organized
  	* Easier and faster to create commands
* Added some framework for future functionality with DMs

### 0.1.1

* Added Commands
  	* Waifu {Username Mention}
    	* Gets the waifu rating of the user
  	* Resetwaifu {Username Mention}
    	* Resets waifu rating of user
    	* Bot admin only
* Known bugs
  	* Avatar command not downloading picture
* Data saving
  	* Waifu data saves
  	* Settings (Commands not implemented yet) data saves
* Added server versatility
* Added optimization for Syrup Society server

### 0.1.0

* Created Bot
* Added Commands
  	* Ping
    	* *Pong*
  	* Avatar {Username Mention}
    	* Gets the avatar of a user
* Set prefix to "**!**"
* Set status to "Playing **Viva La Revolution**"
