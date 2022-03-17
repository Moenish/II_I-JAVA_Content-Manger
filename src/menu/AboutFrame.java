package menu;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {
    public AboutFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(550, 250, 500, 500);
        setResizable(false);
        setTitle("About");

        setContentPane(new AboutPanel());
        setVisible(true);
    }
}
