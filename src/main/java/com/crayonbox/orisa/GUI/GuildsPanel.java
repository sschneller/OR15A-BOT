package com.crayonbox.orisa.GUI;

import com.crayonbox.orisa.OrisaListener;
import net.dv8tion.jda.core.entities.Guild;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.List;

/**
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class GuildsPanel extends JPanel {

    private final OrisaListener bot;
    private final JList guildList;
    private final JTextArea guildQueue;
    private int index = -1;

    public GuildsPanel(OrisaListener bot) {
        super();
        super.setLayout(new GridBagLayout());
        this.bot = bot;

        guildList = new JList();
        guildQueue = new JTextArea();

        guildList.setModel(new DefaultListModel());
        guildList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guildList.setFixedCellHeight(20);
        guildList.setPreferredSize(new Dimension(100, 300));

        guildQueue.setPreferredSize(new Dimension(300, 300));
        guildQueue.setEditable(false);

        JScrollPane pane = new JScrollPane();
        JScrollPane pane2 = new JScrollPane();

        pane.setViewportView(guildList);
        pane2.setViewportView(guildQueue);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridwidth = 1;
        super.add(pane, c);
        c.gridx = 1;
        c.gridwidth = 3;
        super.add(pane2, c);

        guildList.addListSelectionListener((ListSelectionEvent e) -> index = guildList.getSelectedIndex());
    }

    public void updateList(List<Guild> guilds) {
        String[] strs = new String[guilds.size()];
        for(int i = 0; i < guilds.size(); i++) {
            strs[i] = guilds.get(i).getName();
        }
        guildList.setListData(strs);
    }

    public int getIndex() {
        return guildList.getSelectedIndex();
    }
}