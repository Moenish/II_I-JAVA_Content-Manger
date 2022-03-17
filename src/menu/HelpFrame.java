package menu;

import javax.swing.*;
import java.awt.*;

public class HelpFrame extends JFrame {
    public HelpFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(550, 250, 500, 500);
        setResizable(false);
        setTitle("Help");

        setContentPane(new HelpPanel());
        setVisible(true);
    }
}
