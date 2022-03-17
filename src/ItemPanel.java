import models.Item;
import models.ItemListRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemPanel extends JPanel {
    private BufferedImage backgroundImage;
    private final ItemPanel.ListPanel listPanel;
    private final ItemPanel.ButtonPanel buttonPanel;
    private List<Item> itemList;

    //ItemPanel=================================================================================
    public ItemPanel() {
        setBackground(new Color(154, 141, 128));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 40, 50));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridheight = 2;
        gbc.gridwidth = 1;

        try {
            backgroundImage = ImageIO.read(new File("src\\img\\ItemPanel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        itemList = new ArrayList<>();

        DefaultListModel<Item> listModel = new DefaultListModel<>();

        listPanel = new ListPanel(listModel);
        listPanel.setOpaque(false);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.weighty = 0.8f;
        add(listPanel, gbc);


        buttonPanel = new ButtonPanel();
        buttonPanel.setOpaque(false);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.weighty = 0.3f;
        add(buttonPanel, gbc);
    }
    //ItemPanel=================================================================================


    //ListPanel=================================================================================
    public static class ListPanel extends JPanel {
        private JList<Item> itemShow;
        private final JScrollPane scrollPane;

        public ListPanel(DefaultListModel<Item> listModel) {
            setLayout(new BorderLayout());

            itemShow = new JList<>(listModel);
            itemShow.setCellRenderer(new ItemListRenderer());
            itemShow.setOpaque(false);

            scrollPane = new JScrollPane(itemShow);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            add(scrollPane);
        }

        public JList<Item> getItemShow() {
            return itemShow;
        }

        public void setItemShow(JList<Item> itemShow) {
            this.itemShow = itemShow;
            this.removeAll();

            scrollPane.setViewportView(this.itemShow);

            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        }
    }
    //ListPanel=================================================================================


    //ButtonPanel===============================================================================
    public static class ButtonPanel extends JPanel {
        private final JButton[] buttons;

        public ButtonPanel() {
            setLayout(new GridLayout(2,2, 0, 10));
            setBorder(BorderFactory.createEmptyBorder());

            buttons = new JButton[4];

            addButtons();
        }

        public void addButtons() {
            ImageIcon btnIcon = new ImageIcon("src\\img\\button_square2.png");
            Image img = btnIcon.getImage();
            btnIcon = new ImageIcon(img.getScaledInstance(30, 20, Image.SCALE_SMOOTH));

            for (int i = 0; i < 4; i++) {
                buttons[i] = new JButton(getButtonText(i), btnIcon);
                buttons[i].setHorizontalTextPosition(JButton.CENTER);
                buttons[i].setVerticalTextPosition(JButton.CENTER);
                buttons[i].setOpaque(false);
                buttons[i].setContentAreaFilled(false);
                buttons[i].setBorder(BorderFactory.createEmptyBorder());
                add(buttons[i]);
            }
        }

        private String getButtonText(int i) {
            return switch (i) {
                case 0 -> "<html><font style='color:rgb(255,0,0)'>R<br></font></html>";
                case 1 -> "<html><font style='color:rgb(0,255,0)'>M<br></font></html>";
                case 2 -> "<html><font style='color:rgb(0,255,0)'>S<br></font></html>";
                case 3 -> "<html><font style='color:rgb(0,255,0)'>B<br></font></html>";
                default -> "<html><font style='color:rgb(0,0,0)'>n<br></font></html>";
            };
        }

        public void addButtonListener(ActionListener actionListener) {
            Arrays.stream(buttons).forEach(btn -> btn.addActionListener(actionListener));
        }

        public void removeButtonListener(ActionListener actionListener) {
            Arrays.stream(buttons).forEach(btn -> btn.removeActionListener(actionListener));
        }

        public JButton[] getButtons() {
            return buttons;
        }
    }
    //ButtonPanel===============================================================================


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
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public JList<Item> getItemShow() {
        return listPanel.getItemShow();
    }

    public void setItemShow(JList<Item> itemShow) {
        listPanel.setItemShow(itemShow);
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    //Getter, Setter===========================================================================


    //Listener osszekoto=======================================================================
    public void addItemListener(MouseListener mouseListener) {
        listPanel.getItemShow().addMouseListener(mouseListener);
    }

    public void removeItemListener(MouseListener mouseListener) {
        listPanel.getItemShow().removeMouseListener(mouseListener);
    }
    //Listener osszekoto=======================================================================
}
