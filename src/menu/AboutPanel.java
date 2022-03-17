package menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AboutPanel extends JPanel {
    private BufferedImage backgroundImage;

    public AboutPanel() {
        setLayout(new GridBagLayout()); //Egyszeruen centereli a tartalmat

        try { //Hatterkep betoltese
            backgroundImage = ImageIO.read(new File("src\\img\\EditorPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Tartalom :)
        String label = "<html><font color='white'>JAVA Project made by:<br></font><font color='green'><b>Szilágyi Krisztián-Attila</b></font></html>";
        add(new JLabel(label));
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
