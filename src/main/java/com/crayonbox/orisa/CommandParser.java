package com.crayonbox.orisa;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.ArrayList;

public class CommandParser extends ListenerAdapter {

    private ArrayList<Channel> unwatchedTempChannels = new ArrayList<>();
    private ArrayList<Channel> watchedTempChannels = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        parseCommand(event);
    }

    private void parseCommand(MessageReceivedEvent event) {
        String command = event.getMessage().getContent();
        Role everyone = event.getGuild().getRoleById("304814463008243713");
        if(command.contains(" ")) {
            String[] tokenizedCommand = command.split(" ");
            switch(tokenizedCommand[0]) {
                case "!temp": {
                    createTempVoiceChannel(event.getGuild(), tokenizedCommand[1]);
                }
                case "!archive": {
                    if(tokenizedCommand[1].equals("list")) {
                        event.getGuild().getTextChannels().forEach(tc -> System.out.println(tc.getName() + ": " + tc.getPermissionOverride(everyone)));
                    }
                    else if(tokenizedCommand[1].equals("join")) {

                    }
                    else if(tokenizedCommand[1].equals("leave")) {

                    }
                }
            }
        }
        else {
            switch(command) {
                case "!help": {
                    event.getTextChannel().sendMessage("```Markdown\n" +
                            "# Commands:\n" +
                            "1. !help - This command\n" +
                            "2. !temp <channel_name> - Creates a temporary voice channel with the provided name.  When everyone leaves the channel, it will be deleted.\n" +
                            "3. !archive <mode> <id> - Allows you to work with archived channels.  Modes: list, join, leave\n" +
                            "```").queue();
                }
            }
        }
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        if(unwatchedTempChannels.contains(event.getChannelJoined())) {
            unwatchedTempChannels.remove(event.getChannelJoined());
            watchedTempChannels.add(event.getChannelJoined());
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        if(watchedTempChannels.contains(event.getChannelLeft()) && event.getChannelLeft().getMembers().isEmpty()) {
            watchedTempChannels.remove(event.getChannelLeft());
            event.getChannelLeft().delete().queue();
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        if(unwatchedTempChannels.contains(event.getChannelJoined())) {
            unwatchedTempChannels.remove(event.getChannelJoined());
            watchedTempChannels.add(event.getChannelJoined());
        }
        else if(watchedTempChannels.contains(event.getChannelLeft()) && event.getChannelLeft().getMembers().isEmpty()) {
            watchedTempChannels.remove(event.getChannelLeft());
            event.getChannelLeft().delete().queue();
        }
    }

    private void createTempVoiceChannel(Guild guild, String name) {
        GuildController gc = new GuildController(guild);
        gc.createVoiceChannel(name).queue((channel) -> unwatchedTempChannels.add(channel));
    }
}
