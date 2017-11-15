package com.crayonbox.orisa.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import com.jagrosh.jdautilities.menu.selectiondialog.SelectionDialog;
import com.jagrosh.jdautilities.menu.selectiondialog.SelectionDialogBuilder;
import com.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;

public class ArchiveCmd extends Command {

    private final EventWaiter waiter;

    public ArchiveCmd(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "archive";
        this.help = "Command to interact with archived channels.  Modes: list, join, leave";
        this.arguments = "<mode> [id]";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getArgs().split("\\s+");
        switch(args[0]) {
            case "list": {
                int i = 1;
                StringBuilder channels = new StringBuilder();
                channels.append("```markdown\n");
                for(TextChannel channel : event.getGuild().getTextChannels()) {
                    if(channel.getPermissionOverride(event.getGuild().getRoleById("304814463008243713")) != null && !channel.getName().equals("secret-hideout")) {
                        channels.append("[").append(i).append("]: ").append(channel.getName()).append("\n");
                        i++;
                    }
                }
                channels.append("```");

                EmbedBuilder builder = new EmbedBuilder();

                builder.setColor(event.getGuild().getSelfMember().getColor());
                builder.setAuthor("Archived Channels", null, event.getGuild().getIconUrl());
                builder.addField("Channels:", channels.toString(), true);
                builder.setFooter("To gain access to one of these archived channels, use the !archive join <id> command, where id is the corresponding number on the list", null);
                event.replyInDM(builder.build());
                break;
            }
            case "join": {
                int i = 1;
                for(TextChannel channel : event.getGuild().getTextChannels()) {
                    if(channel.getPermissionOverride(event.getGuild().getRoleById("304814463008243713")) != null && !channel.getName().equals("secret-hideout")) {
                        if(args[1].equals(i + "")) {
                            channel.createPermissionOverride(event.getMember()).setAllow(Permission.VIEW_CHANNEL).queue();
                            break;
                        }
                        i++;
                    }
                }
                break;
            }
            default:
                SelectionDialogBuilder sdb = new SelectionDialogBuilder();
                sdb.setEventWaiter(waiter);
                sdb.setSelectionConsumer(integer -> {
                    System.out.println(integer);
                });
                sdb.setSelectedEnds(">", "<");
                sdb.useLooping(true);
                sdb.setText("Pick a letter:");
                SelectionDialog sd = sdb.build();
                event.getAuthor().openPrivateChannel().queue(sd::display);
                break;
        }
    }
}
