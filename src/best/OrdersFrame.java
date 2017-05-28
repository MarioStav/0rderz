package best;

import database.DBConnect;
import guiAnmeldung.*;
import guifenster.Dia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class OrdersFrame {

    public static JButton finish = new JButton("Abschließen");

    public static void frameElems() {
        JFrame comp = new JFrame("Aktuelle Bestellungen");
        comp.setSize(700,500);
        comp.setResizable(true);
        comp.setLocation(700,300);
        comp.setLayout(new BorderLayout());
        comp.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JPanel buttons = new JPanel(new BorderLayout());
        comp.add(buttons, BorderLayout.NORTH);
        JPanel output = new JPanel(new BorderLayout());
        JTable orders = new JTable();
        orders.setPreferredScrollableViewportSize(new Dimension(600, 240));
        orders.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane table_scroll = new JScrollPane(orders);
        output.add(table_scroll, BorderLayout.CENTER);
        comp.add(output, BorderLayout.CENTER);
        orders.getTableHeader().setReorderingAllowed(false);
        orders.setModel(createJTable_o());
        JLabel name = new JLabel("");
        comp.add(name, BorderLayout.SOUTH);
        buttons.add(finish, BorderLayout.EAST);
        finish.setVisible(false);
        finish.addActionListener(action -> {
            deleteElems(orders);
        });
        orders.addMouseListener(new MouseAdapter() {
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
            Dia dia = new Dia();
        });
        comp.setVisible(true);
    }

    public static void deleteElems(JTable order) {
        ArrayList<Integer> orders = new ArrayList<>();
        for (int i = 0; i < order.getRowCount(); i++) {
            boolean selected = (boolean) order.getValueAt(i, order.getSelectedColumn());
            if (selected) {
                orders.add((Integer) order.getValueAt(i,0));
            }
        }
        finish.addActionListener(e -> {
            orders.forEach((something)->{
                DBConnect db = new DBConnect();
                db.delete_b(something);
                });
            JDialog deleteDialog = new JDialog();
            deleteDialog.setTitle("Bestellung abgeschlossen");
            deleteDialog.add(new JLabel("Die Bestellung wurde abgeschlossen"));
            deleteDialog.setSize(100,100);
            deleteDialog.setLocation(800,200);
            deleteDialog.setResizable(false);
            deleteDialog.setVisible(true);
            OrdersFrame ordersFrame = new OrdersFrame();
            ordersFrame.frameElems();
        });
    }

    public static DefaultTableModel createJTable_o() {

        String[] column_names = {"TischNr", "SVNr", "Zeit", "Essen", "Getraenk", "Abgeschlossen"};
        String url = "jdbc:sqlite:C:/Users/Mario/IdeaProjects/G2B/db";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"TischNr", "SVNr", "Zeit", "Essen", "Getraenk", "Abgeschlossen"},0) {
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
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Bestellung")) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getObject(column_names[0]), rs.getObject(column_names[1]), rs.getObject(column_names[2]),
                        rs.getObject(column_names[3]), rs.getObject(column_names[4]), false});

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return model;
    }
}