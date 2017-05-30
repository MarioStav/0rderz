package GUI.Anmeldung;

import Database.DBConnect;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

/**
 * @author Christoph Reichl
 * @since 28.04.2017
 */

public class CompleteFrame {

    static JButton del = new JButton("Löschen");

    public static void main(String[] args) {

        DBConnect.DBcreate("G2B.db");
        DBConnect.TableCreate();
        File start_sound = new File("Sounds\\Windows-98.wav");
        playSound(start_sound);
        ThreadLoad thread = new ThreadLoad();
        thread.start();
        synchronized (thread) {

            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        frameElems();
        RegistryFrame.createXML("addresses", "address", "Addresses.xml");

    }

    /**
     * creates a Frame where the database and some navigation-buttons are implemented (Main-Frame of the login)
     */
    public static void frameElems() {

        JFrame comp = new JFrame("Anmeldung");
        comp.setSize(500,500);
        comp.setResizable(false);
        comp.setLocation(700,300);
        comp.setLayout(new BorderLayout());
        comp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new BorderLayout());
        comp.add(top, BorderLayout.NORTH);

        JPanel ausgabe = new JPanel(new BorderLayout());
        JTable kellner = new JTable();
        kellner.setPreferredScrollableViewportSize(new Dimension(600, 240));
        kellner.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane table_scroll = new JScrollPane(kellner);
        ausgabe.add(table_scroll, BorderLayout.CENTER);
        //table_scroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        comp.add(ausgabe, BorderLayout.CENTER);
        kellner.getTableHeader().setReorderingAllowed(false);
        kellner.setModel(createJTable_k());

        JPanel bottom = new JPanel(new BorderLayout());
        JLabel name = new JLabel("");
        bottom.add(name, BorderLayout.WEST);

        JButton log = new JButton("Login");
        top.add(log, BorderLayout.CENTER);

        log.addActionListener(action -> {

            LoginFrame.loginElems(name);

        });


        top.add(del, BorderLayout.WEST);
        del.setVisible(false);

        del.addActionListener(action -> {

            DeleteFrame.deleteElems(kellner, name, del);

        });


        kellner.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                JTable src = (JTable) e.getSource();
                if (src.getSelectedColumn() == 5) {

                    int count = 0;
                    for (int i = 0; i < src.getRowCount(); i++) {

                        boolean selected = (boolean) src.getValueAt(i, src.getSelectedColumn());
                        if (selected) {

                            count++;

                        }

                    }

                    if (count != 0) {

                        del.setVisible(true);

                    } else {

                        del.setVisible(false);

                    }

                }

            }
        });

        JButton reg = new JButton("Registrieren");
        top.add(reg, BorderLayout.EAST);
        reg.addActionListener(action -> {

            RegistryFrame.registryElems(kellner);

        });

        JButton logout = new JButton("Logout");
        bottom.add(logout, BorderLayout.EAST);

        logout.addActionListener(action -> {

            if (LoginFrame.loged_in.length() > 1) {

                JOptionPane.showConfirmDialog(null,"ausgelogged", "Information", JOptionPane.OK_CANCEL_OPTION);
                LoginFrame.loged_in = "";
                name.setText("");

            } else {

                JOptionPane.showConfirmDialog(null,"Niemand ist angemeldet", "Information", JOptionPane.OK_CANCEL_OPTION);

            }

        });
        comp.add(bottom, BorderLayout.SOUTH);
        comp.setVisible(true);

    }

    /**
     * creates a new JTable with the content of the database and returns the model of this table
     * @return DefaultTableModel
     */
    public static DefaultTableModel createJTable_k() {

        //Object[][] data = new Object[30][6];  //wie bekommt man unbegrenzt viele einträge
        String[] column_names = {"SVNr", "vname", "fname", "PLZ", "Stadt", "Auswahl"};
        String url = "jdbc:sqlite:DB/G2B.db";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"SVNr", "Vorname", "Nachname", "PLZ", "Stadt", "Auswahl"},0) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == column_names.length-1) {

                    return Boolean.class;

                } else {

                    return String.class;

                }

            }

            @Override
            public boolean isCellEditable(int row, int column) {

                if (column == 5) {

                    return true;

                } else {

                    return false;

                }

            }

        };
        //DefaultTableModel model = new DefaultTableModel(data, column_names);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Kellner")) {
            /*
            rs.last();
            int laenge = rs.getRow();
            System.out.println(laenge);
            rs.beforeFirst();
            int j = 0;
            */

            while (rs.next()) {

                model.addRow(new Object[]{rs.getObject(column_names[0]), rs.getObject(column_names[1]), rs.getObject(column_names[2]),
                        rs.getObject(column_names[3]), rs.getObject(column_names[4]), false});

                /*

                int i = 0;
                String svnr_ein = rs.getString("SVNr");
                data[j][i] = svnr_ein;
                i++;
                String vname_ein = rs.getString("vname");
                data[j][i] = vname_ein;
                i++;
                String fname_ein = rs.getString("fname");
                data[j][i] = fname_ein;
                i++;
                int plz_ein = rs.getInt("PLZ");
                data[j][i] = plz_ein;
                i++;
                String stadt_ein = rs.getString("Stadt");
                data[j][i] = stadt_ein;
                i++;
                j++;
                */

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return model;

    }

    /**
     * plays the error sound if something went wrong with the login
     * @param sound
     */
    public static void playSound(File sound) {

        try {

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

            Thread.sleep(50);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

/*SVNr: 1237010180
        2901061299
 */
