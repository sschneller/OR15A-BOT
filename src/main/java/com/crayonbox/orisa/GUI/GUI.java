package com.crayonbox.orisa.GUI;

import com.crayonbox.orisa.OrisaListener;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private ArrayList<ComboPanel> consoles = new ArrayList<>();
    private List<Guild> guildList;
    private final OrisaListener bot;

    public GUI(OrisaListener bot) {
        super();
        this.bot = bot;
    }

    public void init(List<Guild> guilds) {
        guildList = guilds;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("OR15A");
        ImageIcon ii =  new ImageIcon("C:\\Users\\MadCrayolaz\\Pictures\\icon-portrait.png");
        setIconImage(ii.getImage());
        JTabbedPane tabs = new JTabbedPane();
        for(int i = 0; i < guildList.size(); i++) {
            consoles.add(new ComboPanel(guildList.get(i).getName(), new ConsolePanel()));
            tabs.add((i + 1) + "", consoles.get(i));
        }
        getContentPane().add(tabs);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                bot.shutdown();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public void writeConsoleMessage(MessageReceivedEvent event) {
        if(guildList.contains(event.getGuild())) {
            ComboPanel cp = consoles.get(guildList.indexOf(event.getGuild()));
            cp.getPan().getCon().printf("[%s] %s: %s\n",
                    event.getTextChannel().getName(),
                    event.getMember().getEffectiveName(),
                    event.getMessage().getContent());
        }
    }
}