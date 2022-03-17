package menu;

import javax.swing.*;
import java.awt.*;

public class OptionsFrame extends JFrame {
    private OptionsPanel op;
    private boolean soundEnabled = true;

    public OptionsFrame(boolean soundEnabled) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(550, 250, 500, 500);
        setResizable(false);
        setTitle("Options");

        this.soundEnabled = soundEnabled;

        op = new OptionsPanel(soundEnabled);

        setContentPane(op);
        setVisible(true);
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;

        if (soundEnabled) {
            op.getSoundBtn().setText("<html><font color='lime'>Toggle Sound</font></html>");
        } else {
            op.getSoundBtn().setText("<html><font color='red'>Toggle Sound</font></html>");
        }

        op.revalidate();
        op.repaint();
    }

    public OptionsPanel getOp() {
        return op;
    }
}
