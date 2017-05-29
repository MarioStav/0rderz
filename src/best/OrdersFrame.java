package best;

import database.DBConnect;

import guifenster.Dia;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class OrdersFrame {

    public static JButton finish = new JButton("Abschließen");
    public static JTable jtable = new JTable();

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
        JTable orderJTable = jtable;
        orderJTable.setPreferredScrollableViewportSize(new Dimension(600, 240));
        orderJTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        orderJTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane table_scroll = new JScrollPane(orderJTable);
        output.add(table_scroll, BorderLayout.CENTER);
        comp.add(output, BorderLayout.CENTER);
        orderJTable.setModel(createJTable_o());
        JLabel name = new JLabel("");
        comp.add(name, BorderLayout.SOUTH);
        buttons.add(finish, BorderLayout.EAST);
        finish.setVisible(false);
        finish.addActionListener(action -> {
            deleteElems(orderJTable);
        });
        orderJTable.addMouseListener(new MouseAdapter() {
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
            Dia dia = new Dia();
        });
        comp.setVisible(true);
    }

    public static void deleteElems(JTable jTable) {
        ArrayList<String> orders = new ArrayList<>();
        for (int i = 0; i < jTable.getRowCount(); i++) {
            boolean selected = (boolean) jTable.getValueAt(i, jTable.getSelectedColumn());
            if (selected) {
                orders.add(jTable.getValueAt(i,0).toString());
            }
        }
        finish.addActionListener(e -> {
            orders.forEach((string)->{
                DBConnect db = new DBConnect();
                db.delete_b(string);
                jTable.setModel(createJTable_o());
                });
            JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            jOptionPane.add(new JLabel("Die Bestellung wurde abgeschlossen"));
            jOptionPane.setSize(500,200);
            jOptionPane.setLocation(600,500);
            jOptionPane.setVisible(true);
        });
    }

    public static DefaultTableModel createJTable_o() {
        //creatin the JTable with the orders.
        String[] column_names = {"Zeit", "TischNr", "SVNr", "PersonNr", "Reservierung", "Essen", "Getraenk", "Preis",
                "Abgeschlossen"};
        String url = "jdbc:sqlite:database/databasetest.db";
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Zeit", "TischNr", "SVNr","PersonenAnzahl",
                "Reservierung", "Essen", "Getraenk", "Preis", "Abgeschlossen"},0) {
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