/**@author Christoph \n @since (datum)
 * @description A class that creates a frame. In this Frame servants can login*/
package SignIn;

import database.DBConnect;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LoginFrame extends CompleteFrame {

    public static String loged_in = "";

    //default create method for the login frame
    public void loginElems(JLabel name) {

        this.setLayout(new BorderLayout());
        this.setTitle("Login");
        this.setSize(400,200);
        this.setLocation(700,300);
        this.setResizable(false);
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

        this.add(elems_login, BorderLayout.NORTH);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        JButton b_login = new JButton("Login");
        this.add(b_login, BorderLayout.PAGE_END);
        loginConfirm(b_login, field_loginsvnr, field_loginpw, this, name);

    }

    /**@param name the gui textfield in order to test if someone authorized loggs in*/
    public static void loginConfirm(JButton button_login, JTextField svnr_field, JPasswordField pw_field, JFrame login, JLabel name) {
        //tests if logging in is possible or not
        DBConnect connect = new DBConnect();
        connect.DBConnect();

        button_login.addActionListener(action -> {

            String svnr = svnr_field.getText();
            char[] pw = pw_field.getPassword();
            String query_svnr = "SELECT SVNr FROM Kellner WHERE SVNr = " + svnr;
            String url = "jdbc:sqlite:database/databasetest.db";

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
                                        String query_login = "SELECT vname FROM Kellner WHERE SVNr = " + svnr;
                                        Statement stmt_log = conn.createStatement();
                                        ResultSet rs_log = stmt_log.executeQuery(query_login);
                                        while (rs_log.next()) {
                                            JOptionPane.showConfirmDialog(null, "Herzlich Willkommen " +
                                                    rs_log.getString("vname"), "Login erfolgreich", JOptionPane.OK_CANCEL_OPTION);
                                            name.setText(rs_log.getString("vname") + " ist eingelogged!");
                                        }
                                    } else {
                                        JOptionPane.showConfirmDialog(null, "Sozialversicherungsnummer oder " +
                                                "Passwort inkorrekt", "Error", JOptionPane.OK_CANCEL_OPTION);
                                    }
                                }
                                rs_pw.close();
                                stmt_pw.close();
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
                JOptionPane.showConfirmDialog(null, "Keine gültige SVNr oder nicht vorhanden!", "Error", JOptionPane.OK_CANCEL_OPTION);


            }

        });

    }


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