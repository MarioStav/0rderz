/**@author Christoph \n @since (datum)
 * @description A class that creates a frame. In this Frame servants can and
 * deleted but just with authorized admin access*/
package SignIn;

import database.DBConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DeleteFrame extends CompleteFrame {

    /**@link del_button*/  /**@param kellner is the JTable with the registrated servants*/
    //default creating method for the frame
    public void deleteElems(JTable kellner, JLabel name, JButton del_button) {

        this.setTitle("Beutzer Löschen");
        this.setAlwaysOnTop(true);
        this.setSize(400,200);
        this.setLocation(00,300);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new SpringLayout());

        JLabel text_del = new JLabel("Admin:");
        panel.add(text_del);
        JTextField field_del = new JTextField(20);
        text_del.setLabelFor(field_del);
        panel.add(field_del);

        JLabel text_pw = new JLabel("Passwort:");
        panel.add(text_pw);
        JPasswordField field_pw = new JPasswordField(20);
        text_pw.setLabelFor(field_pw);
        panel.add(field_pw);

        SpringUtilities.makeGrid(panel, 2, 2, 6, 30, 6, 10);

        ArrayList<String> svnrs = new ArrayList<>();
        for (int i = 0; i < kellner.getRowCount(); i++) {

            boolean selected = (boolean) kellner.getValueAt(i, kellner.getSelectedColumn());
            if (selected) {

                svnrs.add((String) kellner.getValueAt(i,0));

            }

        }

        //testing if the admin really wants to delete a servant
        JButton del_confirm = new JButton("Löschen");
        del_confirm.addActionListener(action -> {

            if (field_del.getText().equals("admin") && String.valueOf(field_pw.getPassword()).equals("admin")) {

                DBConnect connect = new DBConnect();
                connect.DBConnect();
                svnrs.forEach((consumer)->{

                    connect.delete_k(consumer);
                    if (consumer.equals(LoginFrame.loged_in)) {

                        LoginFrame.loged_in = "";
                        name.setText("");

                    }
                    this.setVisible(false);
                    del_button.setVisible(false);
                    kellner.setModel(CompleteFrame.createJTable_k());

                });
                JOptionPane.showConfirmDialog(null, "Benutzer wurde erfolgreich gelöscht!",
                        "Benutzer entfernen", JOptionPane.OK_CANCEL_OPTION);

            } else {

                JOptionPane.showConfirmDialog(null, "Falsche Anmeldedaten!", "Zugriff verweigert", JOptionPane.OK_CANCEL_OPTION);

            }

        });

        this.add(del_confirm, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.NORTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    //select method in sql
    /**@param svnr the social securitiy number. here is tested if it is contained in a the jtable*/
    public static boolean inDB(String svnr) {
        String query = "SELECT SVNr FROM Kellner WHERE SVNr = " + svnr;
        String url = "jdbc:sqlite:../databasetest.db";
        if (RegistryFrame.isSVNR(svnr)) {


            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                if (rs.next()) {
                    return true;

                } else {

                    return false;

                }

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

        }

        return false;

    }

}