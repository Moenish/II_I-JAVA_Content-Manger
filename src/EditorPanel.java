import modelPanels.BookPanel;
import modelPanels.MoviePanel;
import modelPanels.SeriesPanel;
import models.Book;
import models.Item;
import models.Movie;
import models.Series;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditorPanel extends JPanel {
    private BufferedImage backgroundImage;

    private Component contentPanel;
    private Item item;

    //EditorPanel=============================================================================
    public EditorPanel() {
        setBackground(new Color(154, 141, 128));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        try {
            backgroundImage = ImageIO.read(new File("src\\img\\EditorPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContent();

        add(contentPanel);
    }

    private void setContent() {
        if (item == null) {
            contentPanel = new JLabel("<html><font color='white'></font></html>");
            return;
        }

        if (Item.class.equals(item.getClass())) {
            contentPanel =  new JLabel("<html><font color='white'></font></html>");
            return;
        }

        if (Movie.class.equals(item.getClass())) {
            contentPanel =  new MoviePanel((Movie) item);
            return;
        }

        if (Series.class.equals(item.getClass())) {
            contentPanel =  new SeriesPanel((Series) item);
            return;
        }

        if (Book.class.equals(item.getClass())) {
            contentPanel =  new BookPanel((Book) item);
            return;
        }

        contentPanel =  new JLabel("<html><font color='white'>null</font></html>");
    }
    //EditorPanel=============================================================================


    //Background===============================================================================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(backgroundImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this);
            g2d.dispose();
        }
    }
    //Background===============================================================================


    //Getter, Setter===========================================================================
    public Component getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(Component contentPanel) {
        this.contentPanel = contentPanel;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
        this.removeAll();
        setContent();
        this.add(contentPanel);
        this.revalidate();
        this.getComponent(0).revalidate();
        this.getComponent(0).repaint();
    }
    //Getter, Setter===========================================================================
}
