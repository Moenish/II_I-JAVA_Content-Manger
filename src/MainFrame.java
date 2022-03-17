import menu.AboutFrame;
import menu.HelpFrame;
import menu.OptionsFrame;
import modelPanels.BookPanel;
import modelPanels.MoviePanel;
import modelPanels.SeriesPanel;
import models.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MainFrame extends JFrame implements ActionListener {
    private JMenuItem save, load, clear, options, help, about;

    private final ItemPanel itemPanel;
    private final EditorPanel editorPanel;

    private boolean soundEnabled = true;


    //Frame==========================================================================
    public MainFrame() {
        //Alkalmazas meretenek dinamikus beallitasa
        int screenWidth  = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        float screenFactor = 1.2f;
        int windowWidth = (int) (screenWidth / screenFactor);
        int windowHeight = (int) (screenHeight / screenFactor) + 20;
        int x = (screenWidth + windowWidth) / 2 - windowWidth;
        int y = (screenHeight + windowHeight) / 2 - windowHeight;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(x, y, windowWidth, windowHeight);
        setResizable(true);

        setTitle("Project");

        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(154, 141, 128));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 1.0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;

        itemPanel = new ItemPanel();
        editorPanel = new EditorPanel();

        addItemPanelListener();
        addItemButtonPanelListener();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.1;
        mainPanel.add(itemPanel, gbc);
        gbc.weightx = 0.9;
        mainPanel.add(editorPanel, gbc);

        setJMenuBar(createMenu());
        setContentPane(mainPanel);

        setVisible(true);
    }
    //Frame==========================================================================


    //Listenerek=====================================================================
    //ItemPanel listajanak listenerje
    private void addItemPanelListener() {
        itemPanel.addItemListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                java.util.List<Item> list = itemPanel.getItemList();
                int index = itemPanel.getItemShow().locationToIndex(e.getPoint());
                Item item = new Item();

                if (index >= 0) {
                    item = list.get(index);
                }

                //Item aktualizalas
                editorPanel.setItem(item);
                //Editor panelen levo gombok ujra aktualizalasa
                addEditorButtonListener();
            }
        });
    }

    //ItemPanel gombjainak listenerje
    private void addItemButtonPanelListener() {
        itemPanel.getButtonPanel().addButtonListener(e -> {
            JButton[] buttons = itemPanel.getButtonPanel().getButtons();

            //Torles
            if (e.getSource() == buttons[0]) {
                int selectedIndex = itemPanel.getItemShow().getSelectedIndex();

                if (selectedIndex >= 0) {
                    //Elobb toroljuk az ItemShow-bol
                    DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                    itemListModel.removeElementAt(selectedIndex);
                    itemPanel.getItemList().remove(selectedIndex);

                    //Frissitjuk az ItemShow-t
                    JList<Item> itemShow = new JList<>(itemListModel);
                    itemShow.setCellRenderer(new ItemListRenderer());
                    itemShow.setOpaque(false);
                    itemPanel.setItemShow(itemShow);

                    //Frissitjuk az EditorPanel tartalmat
                    editorPanel.setItem(new Item());

                    //System.out.println("ItemButtonPanel: Cleared selected item");
                    playSound("src\\sound\\click_1_16.wav");
                } else {
                    //System.out.println("ItemButtonPanel: No item selected to clear");
                    playSound("src\\sound\\error_1_16.wav");
                }
            }

            //Movie hozzaadas
            if (e.getSource() == buttons[1]) {
                Item item = new Movie();
                item.setTitle("models.Movie");
                item.setRatings(new Ratings());
                //ItemList-hez hozzaadas
                java.util.List<Item> itemList = itemPanel.getItemList();
                itemList.add(item);
                itemPanel.setItemList(itemList);

                //ItemShow-hoz hozzaadas
                DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                itemListModel.addElement(item);

                JList<Item> itemShow = new JList<>(itemListModel);
                itemShow.setCellRenderer(new ItemListRenderer());
                itemShow.setOpaque(false);
                itemPanel.setItemShow(itemShow);

                //System.out.println("ItemButtonPanel: Added new models.Movie");
                playSound("src\\sound\\click_1_16.wav");
            }

            //Series hozzaadas
            if (e.getSource() == buttons[2]) {
                Item item = new Series();
                item.setTitle("models.Series");
                item.setRatings(new Ratings());
                java.util.List<Item> itemList = itemPanel.getItemList();
                itemList.add(item);
                itemPanel.setItemList(itemList);

                DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                itemListModel.addElement(item);

                JList<Item> itemShow = new JList<>(itemListModel);
                itemShow.setCellRenderer(new ItemListRenderer());
                itemShow.setOpaque(false);
                itemPanel.setItemShow(itemShow);

                //System.out.println("ItemButtonPanel: Added new models.Series");
                playSound("src\\sound\\click_1_16.wav");
            }

            //Book hozzaadas
            if (e.getSource() == buttons[3]) {
                Item item = new Book();
                item.setTitle("models.Book");
                item.setRatings(new Ratings());
                java.util.List<Item> itemList = itemPanel.getItemList();
                itemList.add(item);
                itemPanel.setItemList(itemList);

                DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                itemListModel.addElement(item);

                JList<Item> itemShow = new JList<>(itemListModel);
                itemShow.setCellRenderer(new ItemListRenderer());
                itemShow.setOpaque(false);
                itemPanel.setItemShow(itemShow);

                playSound("src\\sound\\paper_2_16.wav");
            }

            //Mivel minden esetben uj listat rendeltunk a panelhez, ujra fel kell iratkozni a listenerre
            addItemPanelListener();
        });
    }

    private void addEditorButtonListener() {
        Component contentPanel;
        try {
            //Megprobalja megszerezni az EditorPanelt, ha nem sikerul Exception-t dob
            contentPanel = editorPanel.getContentPanel();
            if (contentPanel == null) throw new Exception();

            //Ha MoviePanel
            try {
                //Itt nem tortenhet meg, hogy a contentPanel null, mert mar le volt egyszer ellenorizve
                MoviePanel cp = (MoviePanel) contentPanel;
                //Listener hozzarendeles adott panel gombjaihoz
                cp.addButtonListener(e -> {
                    JButton[] buttons = cp.getButtons();

                    //Save
                    if (e.getSource() == buttons[0]) {
                        //Ertekek kimentese
                        Movie movie = new Movie();
                        movie.setTitle(cp.getFields()[0].getText());
                        movie.setPublisher(cp.getFields()[1].getText());
                        movie.setWikiLink(cp.getFields()[2].getText());
                        movie.setImgLink(cp.getFields()[3].getText());
                        movie.setCategory(cp.getFields()[4].getText());
                        movie.setSubCategory(cp.getFields()[5].getText());

                        movie.setDirector(cp.getFields()[6].getText());
                        movie.setMinutesTotal(Integer.parseInt(cp.getFields()[7].getText()));
                        movie.setMinutesWatched(Integer.parseInt(cp.getFields()[8].getText()));

                        Ratings ratings = new Ratings();
                        ratings.setRatingMax(Integer.parseInt(cp.getFields()[9].getText()));
                        ratings.setRatingCrt(Integer.parseInt(cp.getFields()[10].getText()));
                        ratings.setRatingPrs(Integer.parseInt(cp.getFields()[11].getText()));
                        ratings.setRatingNbr(Integer.parseInt(cp.getFields()[12].getText()));

                        movie.setRatings(ratings);

                        //ItemPanel-en levo ertekek aktualizalasa
                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, movie);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, movie);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        //EditorPanel ujra aktualizalasa az uj ertekekkel (Foleg a kep miatt)
                        editorPanel.setItem(movie);
                        //ItemPanel listajanak ujra feliratkoztatasa a listenerre
                        addItemPanelListener();
                    }

                    //Clear
                    if (e.getSource() == buttons[1]) {
                        Movie movie = new Movie();
                        movie.setTitle("newMovie");

                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, movie);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, movie);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        editorPanel.setItem(movie);
                        addItemPanelListener();
                    }
                });
                return;
            } catch (Exception ignored) {}

            //Ha SeriesPanel
            try {
                SeriesPanel cp = (SeriesPanel) contentPanel;
                cp.addButtonListener(e -> {
                    JButton[] buttons = cp.getButtons();

                    if (e.getSource() == buttons[0]) {
                        Series series = new Series();
                        series.setTitle(cp.getFields()[0].getText());
                        series.setPublisher(cp.getFields()[1].getText());
                        series.setWikiLink(cp.getFields()[2].getText());
                        series.setImgLink(cp.getFields()[3].getText());
                        series.setCategory(cp.getFields()[4].getText());
                        series.setSubCategory(cp.getFields()[5].getText());

                        series.setDirector(cp.getFields()[6].getText());
                        series.setEpisodesTotal(Integer.parseInt(cp.getFields()[7].getText()));
                        series.setEpisodesWatched(Integer.parseInt(cp.getFields()[8].getText()));
                        series.setSeasonsTotal(Integer.parseInt(cp.getFields()[9].getText()));
                        series.setSeasonsWatched(Integer.parseInt(cp.getFields()[10].getText()));

                        Ratings ratings = new Ratings();
                        ratings.setRatingMax(Integer.parseInt(cp.getFields()[11].getText()));
                        ratings.setRatingCrt(Integer.parseInt(cp.getFields()[12].getText()));
                        ratings.setRatingPrs(Integer.parseInt(cp.getFields()[13].getText()));
                        ratings.setRatingNbr(Integer.parseInt(cp.getFields()[14].getText()));

                        series.setRatings(ratings);

                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, series);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, series);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        editorPanel.setItem(series);
                        addItemPanelListener();
                    }

                    if (e.getSource() == buttons[1]) {
                        Series series = new Series();
                        series.setTitle("newSeries");

                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, series);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, series);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        editorPanel.setItem(series);
                        addItemPanelListener();
                    }
                });
                return;
            } catch (Exception ignored) {}

            //Ha BookPanel
            try {
                BookPanel cp = (BookPanel) contentPanel;
                cp.addButtonListener(e -> {
                    JButton[] buttons = cp.getButtons();

                    if (e.getSource() == buttons[0]) {
                        Book book = new Book();
                        book.setTitle(cp.getFields()[0].getText());
                        book.setPublisher(cp.getFields()[1].getText());
                        book.setWikiLink(cp.getFields()[2].getText());
                        book.setImgLink(cp.getFields()[3].getText());
                        book.setCategory(cp.getFields()[4].getText());
                        book.setSubCategory(cp.getFields()[5].getText());

                        book.setAuthor(cp.getFields()[6].getText());
                        book.setPagesTotal(Integer.parseInt(cp.getFields()[7].getText()));
                        book.setPagesRead(Integer.parseInt(cp.getFields()[8].getText()));
                        book.setChaptersTotal(Integer.parseInt(cp.getFields()[9].getText()));
                        book.setChaptersRead(Integer.parseInt(cp.getFields()[10].getText()));

                        Ratings ratings = new Ratings();
                        ratings.setRatingMax(Integer.parseInt(cp.getFields()[11].getText()));
                        ratings.setRatingCrt(Integer.parseInt(cp.getFields()[12].getText()));
                        ratings.setRatingPrs(Integer.parseInt(cp.getFields()[13].getText()));
                        ratings.setRatingNbr(Integer.parseInt(cp.getFields()[14].getText()));

                        book.setRatings(ratings);

                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, book);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, book);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        editorPanel.setItem(book);
                        addItemPanelListener();
                    }

                    if (e.getSource() == buttons[1]) {
                        Book book = new Book();
                        book.setTitle("newBook");

                        int selectedIndex = itemPanel.getItemShow().getSelectedIndex();
                        if (selectedIndex >= 0) {
                            java.util.List<Item> itemList = itemPanel.getItemList();
                            itemList.add(selectedIndex, book);
                            itemList.remove(selectedIndex + 1);
                            itemPanel.setItemList(itemList);

                            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                            itemListModel.removeElementAt(selectedIndex);
                            itemListModel.add(selectedIndex, book);

                            JList<Item> itemShow = new JList<>(itemListModel);
                            itemShow.setCellRenderer(new ItemListRenderer());
                            itemShow.setOpaque(false);
                            itemPanel.setItemShow(itemShow);

                            playSound("src\\sound\\click_1_16.wav");
                        } else {
                            playSound("src\\sound\\error_1_16.wav");
                        }

                        editorPanel.setItem(book);
                        addItemPanelListener();
                    }
                });
            } catch (Exception ignored) {}
        } catch(Exception exc) {    //Barmifele Exception
            exc.printStackTrace();
        }

    }
    //Listenerek=====================================================================


    //JMenu==========================================================================
    //Menu ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        //Save
        if (e.getSource() == save) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);

            FileNameExtensionFilter fileNameFilter;
            fileNameFilter = new FileNameExtensionFilter("Only serialized files", "ser");
            fc.addChoosableFileFilter(fileNameFilter);

            int r = fc.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                try {
                    File fObj = new File(fc.getSelectedFile().getAbsolutePath());
                    if (fObj.exists()) fObj.delete();

                    FileOutputStream fileOut = new FileOutputStream(fObj + ".ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);

                    out.writeObject(itemPanel.getItemList());

                    out.close();
                    fileOut.close();

                    System.out.println("Serialized data is saved in " + fObj + ".ser");
                } catch(IOException exception) {
                    System.out.println("An error occurred");
                    exception.printStackTrace();
                }
            }
        }

        //Load
        if (e.getSource() == load) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);

            FileNameExtensionFilter fileNameFilter;
            fileNameFilter = new FileNameExtensionFilter("Only serialized files", "ser");
            fc.addChoosableFileFilter(fileNameFilter);

            int r = fc.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                java.util.List<Item> itemList;
                try {
                    FileInputStream fileIn = new FileInputStream(fc.getSelectedFile().getAbsolutePath());
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    itemList = (java.util.List<Item>) in.readObject();

                    itemPanel.setItemList(itemList);
                    DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
                    itemListModel.removeAllElements();
                    itemList.forEach(itemListModel::addElement);

                    JList<Item> itemShow = new JList<>(itemListModel);
                    itemShow.setCellRenderer(new ItemListRenderer());
                    itemShow.setOpaque(false);
                    itemPanel.setItemShow(itemShow);

                    itemPanel.revalidate();
                    itemPanel.repaint();
                    addItemPanelListener();

                    in.close();
                    fileIn.close();
                } catch (IOException exception) {
                    System.out.println("An error occurred during loading");
                    exception.printStackTrace();
                } catch (ClassNotFoundException c) {
                    System.out.println("Class not found");
                    c.printStackTrace();
                }
            }
        }

        //Teljes lista torlese, Clear
        if (e.getSource() == clear) {
            java.util.List<Item> itemList = itemPanel.getItemList();
            itemList.clear();
            itemPanel.setItemList(itemList);

            DefaultListModel<Item> itemListModel = (DefaultListModel<Item>) itemPanel.getItemShow().getModel();
            itemListModel.removeAllElements();
            JList<Item> itemShow = new JList<>(itemListModel);
            itemShow.setCellRenderer(new ItemListRenderer());
            itemShow.setOpaque(false);
            itemPanel.setItemShow(itemShow);

            itemPanel.repaint();
            editorPanel.setItem(new Item());
            repaint();
        }

        //OptionsFrame
        if (e.getSource() == options) {
            OptionsFrame of = new OptionsFrame(soundEnabled);
            of.getOp().addButtonListener(e1 -> {
                if (e1.getSource() == of.getOp().getSoundBtn()) {
                    soundEnabled = !soundEnabled;
                    of.setSoundEnabled(soundEnabled);
                }
            });
        }

        //HelpFrame
        if (e.getSource() == help) {
            new HelpFrame();
        }

        //AboutFrame
        if (e.getSource() == about) {
            new AboutFrame();
        }
    }

    private JMenuBar createMenu() {
        JMenuBar tempMenuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        save  = new JMenuItem("Save");
        load  = new JMenuItem("Load");
        clear = new JMenuItem("Clear");
        options = new JMenuItem("Options");
        help = new JMenuItem("Help");
        about = new JMenuItem("About");

        save.addActionListener(this);
        load.addActionListener(this);
        clear.addActionListener(this);
        options.addActionListener(this);
        help.addActionListener(this);
        about.addActionListener(this);

        menu.setMaximumSize(new Dimension(80, 20));
        help.setMaximumSize(new Dimension(80, 20));
        options.setMaximumSize(new Dimension(80, 20));
        about.setMaximumSize(new Dimension(80, 20));

        menu.add(save);
        menu.add(load);
        menu.add(clear);

        tempMenuBar.add(menu);
        tempMenuBar.add(options);
        tempMenuBar.add(help);
        tempMenuBar.add(about);

        return tempMenuBar;
    }
    //JMenu==========================================================================


    //Hang===========================================================================
    public void playSound(String soundName) {
        if (soundEnabled) {
            //Kulon szal, hogy ne kelljen bevarni a hang lejatszodasat
            Thread sound = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    }
                    catch(Exception ex) {
                        System.out.println("Error playing sound.");
                        ex.printStackTrace();
                    }
                }
            };
            sound.start();
        }
    }
}


