package GUI.Anmeldung;

import Database.DBConnect;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * @author Christoph Reichl
 * @since 16.04.2017
 */

public class LoginFrame {

    static String loged_in = "";
    //private static Clip clip;
    //public static Mixer mixer;

    public static void loginElems(JLabel name) {
        //creates a Frame to get logged in with the svnr and the password

        JFrame login = new JFrame("Login");
        login.setLayout(new BorderLayout());
        login.setSize(400,200);
        login.setLocation(700,300);
        login.setResizable(false);

        JPanel elems_login = new JPanel(new SpringLayout());
        
        JLabel text_loginsvnr = new JLabel("Sozialversicherungsnummer:");
        elems_login.add(text_loginsvnr);
        JTextField field_loginsvnr = new JTextField(20);
        text_loginsvnr.setLabelFor(field_loginsvnr);
        elems_login.add(field_loginsvnr);

        JLabel text_loginpw = new JLabel("Passwort:");
        elems_login.add(text_loginpw);
        JPasswordField field_loginpw = new JPasswordField(20);
        text_loginpw.setLabelFor(field_loginpw);
        elems_login.add(field_loginpw);

        SpringUtilities.makeGrid(elems_login, 2, 2, 6, 30, 6, 10);

        login.add(elems_login, BorderLayout.NORTH);
        login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        login.setVisible(true);

        JButton b_login = new JButton("Login");
        login.add(b_login, BorderLayout.PAGE_END);
        loginConfirm(b_login, field_loginsvnr, field_loginpw, login, name);

    }

    public static void loginConfirm(JButton button_login, JTextField svnr_field, JPasswordField pw_field, JFrame login, JLabel name) {
        //checks if everything is ok with the svnr and password and finally logs in the selected svnr

        DBConnect connect = new DBConnect();
        connect.DBConnect();

        button_login.addActionListener(action -> {

            /*
            Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();
            for(Mixer.Info info : mixInfo) {

                System.out.println(info.getName() + "---" + info.getDescription());

            }
            mixer = AudioSystem.getMixer(mixInfo[1]);

            DataLine.Info datainfo = new DataLine.Info(Clip.class, null);
            try {

                clip = (Clip) mixer.getLine(datainfo);

            } catch (LineUnavailableException lue) {

                System.out.println(lue.getMessage());

            }

            try {

                URL soundURL = Main.class.getResource("GUI/Error.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
                clip.open(audioStream);

            } catch (LineUnavailableException lue){

                System.out.println(lue.getMessage());

            } catch (UnsupportedAudioFileException uafe) {

                System.out.println(uafe.getMessage());

            } catch (IOException ioe) {

                System.out.println(ioe.getMessage());

            }

            clip.start();

            do {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (clip.isActive());
            */

            File sound_error = new File("Sounds\\Error.wav");
            File sound_tromp = new File("Sounds\\SadTrompete.wav");
            String svnr = svnr_field.getText();
            char[] pw = pw_field.getPassword();
            String query_svnr = "SELECT SVNr FROM Kellner WHERE SVNr = " + svnr;
            String url = "jdbc:sqlite:DB/G2B.db";

            if (RegistryFrame.isNumber(svnr) && DeleteFrame.inDB(svnr)) {

                try (Connection conn = DriverManager.getConnection(url);
                     Statement stmt_svnr = conn.createStatement();
                     ResultSet rs_svnr = stmt_svnr.executeQuery(query_svnr)) {

                    while (rs_svnr.next()) {

                        String check_svnr = rs_svnr.getString("SVNr");

                        if (RegistryFrame.isNumber(svnr)) {

                            if (svnr.equals(check_svnr)) {

                                String query_pw = "SELECT pw FROM Kellner WHERE SVNr = " + check_svnr;
                                Statement stmt_pw = conn.createStatement();
                                ResultSet rs_pw = stmt_pw.executeQuery(query_pw);

                                while (rs_pw.next()) {

                                    String check_pw = rs_pw.getString("pw");
                                    String pw_str = String.valueOf(pw);
                                    if (pw_str.equals(check_pw)) {

                                        loged_in = svnr;
                                        String query_login = "SELECT fname FROM Kellner WHERE SVNr = " + svnr;
                                        Statement stmt_log = conn.createStatement();
                                        ResultSet rs_log = stmt_log.executeQuery(query_login);

                                        while (rs_log.next()) {

                                            JOptionPane.showConfirmDialog(null, "Herzlich Willkommen Herr " +
                                                        rs_log.getString("fname"), "Gute dize", JOptionPane.OK_CANCEL_OPTION);
                                            name.setText("Herr " + rs_log.getString("fname") + " ist eingelogged!");

                                        }

                                    } else {

                                        JOptionPane.showConfirmDialog(null, "Passwort inkorrekt", "Error", JOptionPane.OK_CANCEL_OPTION);

                                    }

                                }

                            } else {

                                JOptionPane.showConfirmDialog(null, "SVNr existiert nicht", "Error", JOptionPane.OK_CANCEL_OPTION);

                            }

                        } else {

                            JOptionPane.showConfirmDialog(null, "Keine gültige Zahl", "Error", JOptionPane.OK_CANCEL_OPTION);

                        }

                    }

                } catch (SQLException e) {

                    System.out.println(e.getMessage());

                }

                login.setVisible(false);

            } else {

                playSound(sound_tromp);
                JOptionPane.showConfirmDialog(null, "Keine gültige SVNr oder nicht vorhanden!", "Error", JOptionPane.OK_CANCEL_OPTION);


            }

        });

    }

    public static void playSound(File sound) {
        //plays the error sound if something went wrong with the login

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
