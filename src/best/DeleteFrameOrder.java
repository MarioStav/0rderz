package best;

/**
 * Created by Mario on 20.05.2017.
 */

import database.DBConnect;
import guiAnmeldung.CompleteFrame;
import guiAnmeldung.RegistryFrame;
import guiAnmeldung.SpringUtilities;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DeleteFrameOrder extends Thread {

    public static void deleteElems(JTable kellner, JLabel name, JButton del_button) {

        JFrame del = new JFrame("Löschen");
        del.setSize(400,200);
        del.setLocation(700,300);
        del.setResizable(false);
        del.setLayout(new BorderLayout());
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

        JButton del_confirm = new JButton("Löschen");
        del_confirm.addActionListener(action -> {
            if (field_del.getText().equals("admin") && String.valueOf(field_pw.getPassword()).equals("admin")) {
                DBConnect connect = new DBConnect();
                connect.DBConnect();
                svnrs.forEach((consumer)->{
                    del.setVisible(false);
                    del_button.setVisible(false);
                    kellner.setModel(CompleteFrame.createJTable_k());

                });
                JOptionPane.showConfirmDialog(null, "Benutzer gelöscht!", "Error", JOptionPane.OK_CANCEL_OPTION);

            } else {

                JOptionPane.showConfirmDialog(null, "Falsche Anmeldedaten!", "Error", JOptionPane.OK_CANCEL_OPTION);

            }

        });

        del.add(del_confirm, BorderLayout.SOUTH);
        del.add(panel, BorderLayout.NORTH);
        del.setVisible(true);
        del.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static boolean inDB(String svnr) {
        String query = "SELECT SVNr FROM Bestellung WHERE SVNr = " + svnr;
        String url = "jdbc:sqlite:C:/Users/Mario/IdeaProjects/G2B/";
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
