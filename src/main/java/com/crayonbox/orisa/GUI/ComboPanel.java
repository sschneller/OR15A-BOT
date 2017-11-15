package com.crayonbox.orisa.GUI;

import javax.swing.*;
import java.awt.*;

public class ComboPanel extends JPanel {

    private ConsolePanel pan;

    ComboPanel(String gName, ConsolePanel cp) {
        super();
        super.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        pan = cp;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        this.add(new Label(gName), c);

        c.gridy = 1;
        this.add(pan, c);
    }

    public ConsolePanel getPan() {
        return pan;
    }
}
