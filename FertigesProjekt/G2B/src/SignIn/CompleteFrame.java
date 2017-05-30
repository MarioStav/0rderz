/**@author Christoph \n @since (datum)
 * @description the whole frame that shows up if you press on sign in. it includes registry, login, delete, addresses and logout*/

package SignIn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class CompleteFrame extends JFrame {
    /**important data*/
    static public JButton del = new JButton("Löschen");
    static public String loged_in = LoginFrame.loged_in;

    /**default creating method for the frame*/
    public static void frameElems() {

        JFrame comp = new JFrame("Anmeldung");
        comp.setSize(500, 500);
        comp.setResizable(false);
        comp.setLocation(700, 300);
        comp.setLayout(new BorderLayout());
        comp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel top = new JPanel(new BorderLayout());
        comp.add(top, BorderLayout.NORTH);

        JPanel ausgabe = new JPanel(new BorderLayout());
        JTable kellner = new JTable();
        kellner.setPreferredScrollableViewportSize(new Dimension(600, 240));
        kellner.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane table_scroll = new JScrollPane(kellner);
        ausgabe.add(table_scroll, BorderLayout.CENTER);
        comp.add(ausgabe, BorderLayout.CENTER);
        kellner.getTableHeader().setReorderingAllowed(false);
        kellner.setModel(createJTable_k());

        JPanel bottom = new JPanel(new BorderLayout());
        JLabel name = new JLabel("");
        bottom.add(name, BorderLayout.WEST);

        JButton log = new JButton("Login");
        top.add(log, BorderLayout.CENTER);

        log.addActionListener(action -> {

            LoginFrame lg = new LoginFrame();
            lg.loginElems(name);

        });

        top.add(del, BorderLayout.WEST);
        del.setVisible(false);

        del.addActionListener(action -> {

            DeleteFrame delete = new DeleteFrame();
            delete.deleteElems(kellner, name, del);

        });

        /**mouse listener added to the servant table*/
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

            RegistryFrame rg = new RegistryFrame();
            rg.registryElems(kellner);

        });

        /**testing the login
         *
         */
        JButton logout = new JButton("Logout");
        bottom.add(logout, BorderLayout.EAST);

        logout.addActionListener(action -> {

            if (LoginFrame.loged_in.length() > 1) {

                JOptionPane.showConfirmDialog(null, "Ausgelogged", "Information", JOptionPane.OK_CANCEL_OPTION);
                LoginFrame.loged_in = "";
                name.setText("");

            } else {

                JOptionPane.showConfirmDialog(null, "Niemand ist angemeldet", "Information", JOptionPane.OK_CANCEL_OPTION);

            }

        });
        comp.add(bottom, BorderLayout.SOUTH);
        comp.setVisible(true);

    }

    /**"drawing" the servant table*/
    public static DefaultTableModel createJTable_k() {

        String[] column_names = {"SVNr", "vname", "fname", "PLZ", "Stadt", "Auswahl"};
        String url = "jdbc:sqlite:../databasetest.db";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"SVNr", "Vorname", "Nachname", "PLZ", "Stadt", "Auswahl"}, 0) {

            @Override
            public Class<?> getColumnClass(int columnIndex) {

                if (columnIndex == column_names.length - 1) {

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

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Kellner")) {

            while (rs.next()) {

                model.addRow(new Object[]{rs.getObject(column_names[0]), rs.getObject(column_names[1]), rs.getObject(column_names[2]),
                        rs.getObject(column_names[3]), rs.getObject(column_names[4]), false});


            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return model;

    }

}
