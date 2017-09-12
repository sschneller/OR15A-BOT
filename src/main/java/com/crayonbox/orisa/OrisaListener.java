package com.crayonbox.orisa;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OrisaListener extends ListenerAdapter {
    private static final String GREETING_PM = "**Welcome to the server!**\nThis is an automated message from the local bot.  I'm going to ask you a few questions, please respond with just the number of the associated answer.";
    private static final ArrayList<String> possibleResponses = new ArrayList<>(Arrays.asList(new String[]{new String("What are you gonna do, kill me?"), new String("Attack the fire giant!"), new String("Give Khepri a gun!"), new String("Sobek is my alligator?")}));
    private ArrayList<Channel> unwatchedTempChannels = new ArrayList<>();
    private ArrayList<Channel> watchedTempChannels = new ArrayList<>();

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking();
        jda.addEventListener(new OrisaListener());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContent());
        }
        else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContent());
        }

        if(event.getMessage().getContent().startsWith("!temp")) {
            createTempVoiceChannel(event.getGuild(), event.getMessage().getContent().split(" ")[1]);
        }

        if(event.getMessage().getContent().contains("gay") && event.getAuthor().getName().equals("PeteyP13")) {
            event.getTextChannel().sendMessage("We know, Petey.").queue();
        }

        if(event.getMessage().getContent().contains("sad") && event.getGuild().getName().equals("Nation's Shit House")) {
            Random r = new Random();
            int a = r.nextInt(possibleResponses.size());
            event.getTextChannel().sendMessage(possibleResponses.get(a)).queue();
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member memberJustJoined = event.getMember();
        User userJustJoined = event.getUser();
        sendPM(userJustJoined, GREETING_PM);
    }

    private void sendPM(User user, String content) {
        user.openPrivateChannel().queue((channel) -> channel.sendMessage(content).queue());
    }

    private void createTempVoiceChannel(Guild guild, String name) {
        GuildController gc = new GuildController(guild);
        gc.createVoiceChannel(name).queue((channel) -> unwatchedTempChannels.add(channel));
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        System.out.println("JOINED");
        System.out.println(event.getChannelJoined().getMembers());
        if(unwatchedTempChannels.contains(event.getChannelJoined())) {
            unwatchedTempChannels.remove(event.getChannelJoined());
            watchedTempChannels.add(event.getChannelJoined());
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        System.out.println("LEFT");
        System.out.println(event.getChannelLeft().getMembers());
        if(watchedTempChannels.contains(event.getChannelLeft()) && event.getChannelLeft().getMembers().isEmpty()) {
            System.out.println("HIT");
            watchedTempChannels.remove(event.getChannelLeft());
            event.getChannelLeft().delete().queue();
        }
    }
}
