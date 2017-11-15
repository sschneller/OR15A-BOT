package com.crayonbox.orisa.GUI;

import com.crayonbox.orisa.Bot;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FirstTime extends JFrame {

    private JPanel panel = new JPanel(new MigLayout("", "[][grow,fill]", "[][fill]"));
    private JTextField jpf = new JTextField();
    private JButton submit = new JButton("Submit");

    public FirstTime(Bot bot) {
        super();

        setSize(300, 100);
        setResizable(false);
        setTitle("First Time Setup");
        setLocationRelativeTo(null);

        panel.add(new JLabel("Token"));
        panel.add(jpf, "wrap");

        submit.addActionListener(e -> {
            System.out.println(this.getSize());
            if(!jpf.getText().isEmpty()) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("settings.json", "UTF-8");
                }
                catch(FileNotFoundException | UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                writer.println("{");
                writer.println("    \"token\": \"" + jpf.getText() + "\"");
                writer.print("}");
                writer.close();

                bot.nextStep(jpf.getText());
                this.setVisible(false);
            }
        });

        panel.add(submit, "span 2, growx");

        add(panel);
    }
}
