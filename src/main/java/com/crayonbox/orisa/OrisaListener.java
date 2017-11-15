package com.crayonbox.orisa;

import com.crayonbox.orisa.GUI.GUI;
import com.sun.org.apache.xpath.internal.operations.Or;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;
import org.omg.IOP.TAG_ORB_TYPE;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class OrisaListener extends ListenerAdapter {
    private static final String GREETING_PM = "**Welcome to the server!**\nThis is an automated message from the local bot.  I'm going to ask you a few questions, please respond with just the number of the associated answer. This is under construction, so this may break.";
    private static final ArrayList<String> possibleResponses = new ArrayList<>(Arrays.asList(new String[]{new String("What are you gonna do, kill me?"), new String("Attack the fire giant!"), new String("Give Khepri a gun!"), new String("Sobek is my alligator?"), new String("That's gonna be a no from me, dawg."), new String("vvx"), new String("The answer is 1 quart.")}));
    private static final ArrayList<String> caterpillars = new ArrayList<>(Arrays.asList(new String[]{new String("Yes"), new String("No"), new String("Perhaps?")}));
    private ArrayList<Member> question1People = new ArrayList<>();
    private ArrayList<Member> question2People = new ArrayList<>();
    private ArrayList<Member> question3People = new ArrayList<>();
    private ArrayList<Member> question4People = new ArrayList<>();
    private ArrayList<Member> question5People = new ArrayList<>();
    private ArrayList<Member> question6People = new ArrayList<>();
    private ArrayList<Member> question7People = new ArrayList<>();
    private static JDA jda;
    private static GUI gui;

    public static void main(String[] args) throws LoginException, RateLimitedException, InterruptedException {
        OrisaListener bot = new OrisaListener();
        jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking();
        jda.addEventListener(bot, new CommandParser());

        gui = new GUI(bot);
        gui.init(jda.getGuilds());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContent());

            for(Guild g : event.getAuthor().getMutualGuilds()) {
                if(question1People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "304820915399688193", event.getAuthor());
                        question1People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        question1People.remove(g.getMember(event.getAuthor()));
                        question2People.add(g.getMember(event.getAuthor()));
                        sendPM(event.getAuthor(), "Are you a personal friend of Sam?\n\t1. Yes\n\t2. No");
                    }
                    else {
                        // Something went wrong
                    }
                }
                else if(question2People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "306123570918981634", event.getAuthor());
                        question2People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        question2People.remove(g.getMember(event.getAuthor()));
                        question3People.add(g.getMember(event.getAuthor()));
                        sendPM(event.getAuthor(), "Are you a personal friend of Mo?\n\t1. Yes\n\t2. No");
                    }
                    else {
                        // Something went wrong
                    }
                }
                else if(question3People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "304820903227949059", event.getAuthor());
                        question3People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        question3People.remove(g.getMember(event.getAuthor()));
                        question4People.add(g.getMember(event.getAuthor()));
                        sendPM(event.getAuthor(), "Are you a personal friend of Aria/Warling?\n\t1. Yes\n\t2. No");
                    }
                    else {
                        // Something went wrong
                    }
                }
                else if(question4People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "304820716992462859", event.getAuthor());
                        question4People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        question4People.remove(g.getMember(event.getAuthor()));
                        question5People.add(g.getMember(event.getAuthor()));
                        sendPM(event.getAuthor(), "Are you a personal friend of Shavonte?\n\t1. Yes\n\t2. No");
                    }
                    else {
                        // Something went wrong
                    }
                }
                else if(question5People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "305009057767489536", event.getAuthor());
                        question5People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        question5People.remove(g.getMember(event.getAuthor()));
                        question6People.add(g.getMember(event.getAuthor()));
                        sendPM(event.getAuthor(), "Are you a personal friend of Tom?\n\t1. Yes\n\t2. No");
                    }
                    else {
                        // Something went wrong
                    }
                }
                else if(question6People.contains(g.getMember(event.getAuthor()))) {
                    if(event.getMessage().getContent().equals("1")) {
                        addRoleToMember(g, "305037773352599552", event.getAuthor());
                        question6People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else if(event.getMessage().getContent().equals("2")) {
                        addRoleToMember(g, "304820915399688193", event.getAuthor());
                        question6People.remove(g.getMember(event.getAuthor()));
                        // TODO: Do this -> question7People.add(g.getMember(event.getAuthor())); and prompt them for games
                        sendPM(event.getAuthor(), "Congrats!  Roles have been assigned!");
                    }
                    else {
                        // Something went wrong
                    }
                }
            }
        }
        else {
            gui.writeConsoleMessage(event);

            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContent());

            if(event.getMessage().getContent().toLowerCase().contains("gay") && event.getAuthor().getName().equals("PeteyP13")) {
                event.getTextChannel().sendMessage("We know, Petey.").queue();
            }

            if(event.getMessage().getContent().toLowerCase().contains("sad") && event.getGuild().getName().equals("Sad Fire Giant Attack, Ward, and Defend Squadron")) {
                Random r = new Random();
                int a = r.nextInt(possibleResponses.size());
                event.getTextChannel().sendMessage(possibleResponses.get(a)).queue();
            }

            if(event.getMessage().getContent().toLowerCase().contains("do caterpillars have dicks?") && event.getGuild().getName().equals("Sad Fire Giant Attack, Ward, and Defend Squadron")) {
                Random r = new Random();
                int a = r.nextInt(caterpillars.size());
                event.getTextChannel().sendMessage(caterpillars.get(a)).queue();
            }

            if(event.getMessage().getContent().toLowerCase().contains("omae wa mou shindheru")) {
                event.getTextChannel().sendMessage("```NANI*!?!?     *Editor's Note: Nani means \"what\" in Mongolian wannabe islander```").queue();
            }
        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member memberJustJoined = event.getMember();
        User userJustJoined = event.getUser();
        sendPM(userJustJoined, GREETING_PM);
        question1People.add(memberJustJoined);
        for(Guild g : userJustJoined.getMutualGuilds()) {
            if(question1People.contains(g.getMember(userJustJoined))) {
                addRoleToMember(g, "305008295641481219", event.getUser());
            }
        }
        sendPM(userJustJoined, "Were you invited to the server by someone you met online/in a game?\n\t1. Yes\n\t2. No");
    }

    private void sendPM(User user, String content) {
        user.openPrivateChannel().queue((channel) -> channel.sendMessage(content).queue());
    }

    private void addRoleToMember(Guild guild, String roleID, User author) {
        GuildController gc = guild.getController();
        Optional<Role> foundRole = guild.getRoles().stream().filter(r -> r.getId().equals(roleID)).findFirst();
        if(foundRole.isPresent()) gc.addSingleRoleToMember(guild.getMember(author), foundRole.get()).queue();
    }

    public void shutdown(){
        jda.shutdown();
    }
}
