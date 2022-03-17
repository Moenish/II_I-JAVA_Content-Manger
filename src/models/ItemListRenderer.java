package models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Lista elemeinek kinezeteert felel
public class ItemListRenderer extends JLabel implements ListCellRenderer<Item> {
    private BufferedImage notDone;
    private BufferedImage done;

    public ItemListRenderer() {
        setOpaque(true);
        try { //Befejezett/Meg be nem fejezett tartalom
            notDone = ImageIO.read(new File("src\\img\\cancel.png"));
            done = ImageIO.read(new File("src\\img\\check.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Da magic happenz here
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index, boolean isSelected, boolean cellHasFocus) {
        setText("   " + item.getTitle());
        setIcon(new ImageIcon(item.getDone() ? done : notDone));

        if (isSelected) {
            //setBackground(list.getSelectionBackground());
            setBackground(new Color(200,200,200));
            setForeground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setOpaque(isSelected);

        setForeground(new Color(122, 122, 122));

        return this;
    }
}
