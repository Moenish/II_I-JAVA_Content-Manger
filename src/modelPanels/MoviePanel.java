package modelPanels;

import models.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MoviePanel extends JPanel {
    private Movie movie;
    private BufferedImage image;
    private JTextField[] fields;
    private JButton[] buttons;

    //MoviePanel===============================================================================
    public MoviePanel(Movie movie) {
        this.movie = movie;

        setLayout(new GridLayout(1, 2,10,0));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setOpaque(false);

        if (movie.getImgLink() != null) {
            try {
                image = ImageIO.read(new File(movie.getImgLink()));
            } catch (IOException e) {
                try {
                    image = ImageIO.read(new File("src\\img\\default_image.png"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            try {
                image = ImageIO.read(new File("src\\img\\default_image.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Icon imageIcon = new ImageIcon(image.getScaledInstance(256, 256, Image.SCALE_SMOOTH));

        JLabel titleLabel = new JLabel("<html><font color='white'>Title: </font></html>");
        JLabel publisherLabel = new JLabel("<html><font color='white'>Publisher: </font></html>");
        JLabel wikiLinkLabel = new JLabel("<html><font color='white'>WikiLink: </font></html>");
        JLabel imgLinkLabel = new JLabel("<html><font color='white'>ImgLink: </font></html>");
        JLabel categoryLabel = new JLabel("<html><font color='white'>Category: </font></html>");
        JLabel subCategoryLabel = new JLabel("<html><font color='white'>SubCategory: </font></html>");
        JLabel directorLabel = new JLabel("<html><font color='white'>Director: </font></html>");
        JLabel minutesTotalLabel = new JLabel("<html><font color='white'>Total Minutes: </font></html>");
        JLabel minutesWatchedLabel = new JLabel("<html><font color='white'>Watched Minutes: </font></html>");

        JLabel ratingMaxLabel = new JLabel("<html><font color='white'>Max Rating: </font></html>");
        JLabel ratingCrtLabel = new JLabel("<html><font color='white'>Current Rating: </font></html>");
        JLabel ratingPrsLabel = new JLabel("<html><font color='white'>Personal Rating: </font></html>");
        JLabel ratingNbrLabel = new JLabel("<html><font color='white'>Number of Ratings: </font></html>");

        setBtn();

        fields = new JTextField[13];
        setFields();

        Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container cButton = new Container();
        Container cRating = new Container();

        c1.setLayout(new GridLayout(9, 2));
        c1.add(titleLabel);          c1.add(fields[0]);
        c1.add(publisherLabel);      c1.add(fields[1]);
        c1.add(wikiLinkLabel);       c1.add(fields[2]);
        c1.add(imgLinkLabel);        c1.add(fields[3]);
        c1.add(categoryLabel);       c1.add(fields[4]);
        c1.add(subCategoryLabel);    c1.add(fields[5]);
        c1.add(directorLabel);       c1.add(fields[6]);
        c1.add(minutesTotalLabel);   c1.add(fields[7]);
        c1.add(minutesWatchedLabel); c1.add(fields[8]);

        cRating.setLayout(new GridLayout(4, 2,10 ,10));
        cRating.add(ratingMaxLabel); cRating.add(fields[9]);
        cRating.add(ratingCrtLabel); cRating.add(fields[10]);
        cRating.add(ratingPrsLabel); cRating.add(fields[11]);
        cRating.add(ratingNbrLabel); cRating.add(fields[12]);

        cButton.setLayout(new GridLayout(2,1,0,10));
        cButton.add(buttons[0]); cButton.add(buttons[1]);

        c3.setLayout(new GridLayout(1, 2, 0, 0));
        c3.add(new JLabel(imageIcon)); c3.add(cButton);

        JPanel imagePane = new JPanel();
        imagePane.setLayout(new BorderLayout());
        imagePane.add(c3, BorderLayout.CENTER);
        imagePane.setBorder(BorderFactory.createEmptyBorder(0,0,0,200));
        imagePane.setOpaque(false);

        JScrollPane imageScroll = new JScrollPane(imagePane,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        imageScroll.setOpaque(false);
        imageScroll.getViewport().setOpaque(false);
        imageScroll.setBorder(BorderFactory.createEmptyBorder());

        JPanel ratingPane = new JPanel();
        ratingPane.setLayout(new BorderLayout());
        ratingPane.add(cRating, BorderLayout.CENTER);
        ratingPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,300));
        ratingPane.setOpaque(false);

        JScrollPane ratingScroll = new JScrollPane(ratingPane,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ratingScroll.setOpaque(false);
        ratingScroll.getViewport().setOpaque(false);
        ratingScroll.setBorder(BorderFactory.createEmptyBorder());

        c2.setLayout(new GridLayout(2, 1));
        c2.add(imageScroll); c2.add(ratingScroll);

        JPanel leftSide = new JPanel();
        leftSide.setLayout(new BorderLayout());
        leftSide.add(c1, BorderLayout.CENTER);
        leftSide.setOpaque(false);

        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BorderLayout());
        rightSide.add(c2, BorderLayout.CENTER);
        rightSide.setOpaque(false);

        JScrollPane scrollLeft = new JScrollPane(leftSide,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollLeft.setOpaque(false);
        scrollLeft.getViewport().setOpaque(false);
        scrollLeft.setBorder(BorderFactory.createEmptyBorder());

        JScrollPane scrollRight = new JScrollPane(rightSide,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRight.setOpaque(false);
        scrollRight.getViewport().setOpaque(false);
        scrollRight.setBorder(BorderFactory.createEmptyBorder());

        add(scrollLeft);
        add(scrollRight);
    }

    private void setBtn() {
        ImageIcon btnIcon = new ImageIcon("src\\img\\button_square2.png");
        Image img = btnIcon.getImage();
        btnIcon = new ImageIcon(img.getScaledInstance(60, 40, Image.SCALE_SMOOTH));

        buttons = new JButton[2];
        buttons[0] = new JButton("Save", btnIcon);
        buttons[0].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttons[0].setBounds(0,0,100,50);
        buttons[0].setOpaque(false);
        buttons[0].setContentAreaFilled(false);
        buttons[0].setForeground(Color.WHITE);
        buttons[0].setHorizontalTextPosition(JButton.CENTER);
        buttons[0].setVerticalTextPosition(JButton.CENTER);

        buttons[1] = new JButton("Clear", btnIcon);
        buttons[1].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        buttons[0].setBounds(0,0,100,50);
        buttons[1].setOpaque(false);
        buttons[1].setContentAreaFilled(false);
        buttons[1].setForeground(Color.WHITE);
        buttons[1].setHorizontalTextPosition(JButton.CENTER);
        buttons[1].setVerticalTextPosition(JButton.CENTER);
    }

    private void setFields() {
        for (int i = 0; i < 13; i++) {
            fields[i] = new JTextField("");
            fields[i].setOpaque(false);
            fields[i].setForeground(Color.WHITE);
            fields[i].setCaretColor(Color.WHITE);
            fields[i].setMargin(new Insets(0,10,0,10));
            String text = "";
            if (i == 0) text = movie.getTitle();
            if (i == 1) text = movie.getPublisher();
            if (i == 2) text = movie.getWikiLink();
            if (i == 3) text = movie.getImgLink();
            if (i == 4) text = movie.getCategory();
            if (i == 5) text = movie.getSubCategory();
            if (i == 6) text = movie.getDirector();
            if (i == 7) text = String.valueOf(movie.getMinutesTotal());
            if (i == 8) text = String.valueOf(movie.getMinutesWatched());
            if (i == 9) text = String.valueOf(movie.getRatings().getRatingMax());
            if (i == 10) text = String.valueOf(movie.getRatings().getRatingCrt());
            if (i == 11) text = String.valueOf(movie.getRatings().getRatingPrs());
            if (i == 12) text = String.valueOf(movie.getRatings().getRatingNbr());

            fields[i].setText(text);
        }
    }
    //MoviePanel===============================================================================


    //Listener=================================================================================
    public void addButtonListener(ActionListener actionListener) {
        buttons[0].addActionListener(actionListener);
        buttons[1].addActionListener(actionListener);
    }

    public void removeButtonListener(ActionListener actionListener) {
        buttons[0].removeActionListener(actionListener);
        buttons[1].removeActionListener(actionListener);
    }
    //Listener=================================================================================


    //Getter, Setter===========================================================================
    public JTextField[] getFields() {
        return fields;
    }

    public void setFields(JTextField[] fields) {
        this.fields = fields;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    //Getter, Setter===========================================================================
}
