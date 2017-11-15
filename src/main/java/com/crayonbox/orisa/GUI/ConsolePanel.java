package com.crayonbox.orisa.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class ConsolePanel extends JPanel {

    private PrintStream con;

    public ConsolePanel() {
        super();
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);

        con = new PrintStream(new TextAreaOutputStream(text));
//        System.setOut(con);
//        System.setErr(con);

        JScrollPane pane = new JScrollPane();
        pane.setViewportView(text);

        super.setLayout(new GridLayout(1, 1));
        super.add(pane);
        super.setPreferredSize(new Dimension(400, 300));
    }

    public PrintStream getCon() {
        return con;
    }
}