package menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OptionsPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JButton soundBtn;

    public OptionsPanel(boolean soundEnabled) {
        setLayout(new GridBagLayout());

        try { //Hatterkep betoltese
            backgroundImage = ImageIO.read(new File("src\\img\\EditorPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon btnIcon = new ImageIcon("src\\img\\button_square2.png");
        Image img = btnIcon.getImage();
        btnIcon = new ImageIcon(img.getScaledInstance(120, 60, Image.SCALE_SMOOTH));

        if (soundEnabled) {
            soundBtn = new JButton("<html><font color='lime'>Toggle Sound</font></html>", btnIcon);
        } else {
            soundBtn = new JButton("<html><font color='red'>Toggle Sound</font></html>", btnIcon);
        }
        soundBtn.setOpaque(false);
        soundBtn.setContentAreaFilled(false);
        soundBtn.setBorder(BorderFactory.createEmptyBorder());
        soundBtn.setHorizontalTextPosition(JButton.CENTER);
        soundBtn.setVerticalTextPosition(JButton.CENTER);

        add(soundBtn);
    }

    public void addButtonListener(ActionListener actionListener) {
        soundBtn.addActionListener(actionListener);
    }

    public JButton getSoundBtn() {
        return soundBtn;
    }

    //Hatter kirajzolasa
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
            g2d.dispose();
        }
    }
}
