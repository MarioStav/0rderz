/**@author Mario \n @since (datum)
 * @description Orderframe itsef; shows all orders*/
package orderPackage;

import database.DBConnect;

import guiPackage.OrderDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class OrdersFrame extends JFrame{

    public static JButton finish = new JButton("Abschließen");
    public static JTable jTable = new JTable();

    public void frameElems() {
        /**main constructor to create the frame that shows the order jtable */
        this.setTitle("Aktuelle Bestellungen");
        this.setSize(700, 500);
        this.setResizable(true);
        this.setLocation(700, 300);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel buttons = new JPanel(new BorderLayout());
        this.add(buttons, BorderLayout.NORTH);
        JPanel output = new JPanel(new BorderLayout());
        jTable.setPreferredScrollableViewportSize(new Dimension(600, 240));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane table_scroll = new JScrollPane(jTable);
        output.add(table_scroll, BorderLayout.CENTER);
        this.add(output, BorderLayout.CENTER);
        jTable.setModel(createJTable_o());
        JLabel name = new JLabel("");
        this.add(name, BorderLayout.SOUTH);
        buttons.add(finish, BorderLayout.EAST);
        finish.setVisible(false);
        finish.addActionListener(action -> {
            deleteElems(jTable);
        });
        //mouselistener in order to delete an order
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable src = (JTable) e.getSource();
                if (src.getSelectedColumn() == 8) {
                    int count = 0;
                    for (int i = 0; i < src.getRowCount(); i++) {
                        boolean selected = (boolean) src.getValueAt(i, src.getSelectedColumn());
                        if (selected) {

                            count++;
                        }
                    }
                    if (count != 0) {
                        finish.setVisible(true);
                    } else {
                        finish.setVisible(false);
                    }
                }
            }
        });
        JButton best = new JButton("Neue Bestellung");
        buttons.add(best, BorderLayout.CENTER);
        best.addActionListener(e -> {
            OrderDialog orderDialog = new OrderDialog();
        });
        this.setVisible(true);
    }

    //deleting from the jtable
    public static void deleteElems(JTable jTable) {
        ArrayList<String> orders = new ArrayList<>();
        for (int i = 0; i < jTable.getRowCount(); i++) {
            boolean selected = (boolean) jTable.getValueAt(i, jTable.getSelectedColumn());
            if (selected) {
                orders.add(jTable.getValueAt(i, 0).toString());
            }
        }
        orders.forEach((string) -> {
            DBConnect db = new DBConnect();
            db.delete_b(string);
            jTable.setModel(createJTable_o());
        });
    }

    //generating and printing the jtable into the frame
    public static DefaultTableModel createJTable_o() {
        //creatin the JTable with the orders.
        String[] column_names = {"Zeit", "TischNr", "SVNr", "PersonNr", "Reservierung", "Essen", "Getraenk", "Preis",
                "Abgeschlossen"};
        String url = "jdbc:sqlite:../databasetest.db";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Zeit", "TischNr", "SVNr", "PersonenAnzahl",
                "Reservierung", "Essen", "Getraenk", "Preis", "Abgeschlossen"}, 0) {
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
                if (column == 8) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Bestellung ORDER BY Zeit DESC")) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getObject(column_names[0]), rs.getObject(column_names[1]), rs.getObject(column_names[2]),
                        rs.getObject(column_names[3]), rs.getObject(column_names[4]), rs.getObject(column_names[5]),
                        rs.getObject(column_names[6]), rs.getObject(column_names[7]), false});

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return model;
    }
}