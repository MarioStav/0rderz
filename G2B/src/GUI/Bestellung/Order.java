package GUI.Bestellung;

import GUI.Anmeldung.SpringUtilities;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christoph on 12.05.2017.
 */
public class Order {

    public static void main(String[] args) {

        orderFrame();

    }

    public static void orderFrame() {

        JFrame order_frame = new JFrame("Bestellung");
        order_frame.setSize(700,600);
        order_frame.setResizable(false);
        order_frame.setLocation(700,300);
        order_frame.setLayout(new BorderLayout());

        JPanel auswahl = new JPanel(new BorderLayout());

        JLabel tischnr = new JLabel("Tisch: #tischnummer vom button");
        auswahl.add(tischnr, BorderLayout.NORTH);

        JLabel anz_pers = new JLabel("Anzahl der Personen:");
        auswahl.add(anz_pers, BorderLayout.CENTER);

        JPanel radio_b = new JPanel();
        JRadioButton p_zwei = new JRadioButton("2");
        radio_b.add(p_zwei);
        JRadioButton p_drei = new JRadioButton("3");
        radio_b.add(p_drei);
        JRadioButton p_vier = new JRadioButton("4");
        radio_b.add(p_vier);
        JRadioButton p_fuenf = new JRadioButton("5");
        radio_b.add(p_fuenf);
        auswahl.add(radio_b, BorderLayout.EAST);

        anz_pers.setLabelFor(radio_b);

        //SpringUtilities.makeGrid(auswahl, 1, 2, 6, 30, 6, 10);

        /*
        JLabel essen_text = new JLabel("Mahlzeit:");
        auswahl.add(essen_text);

        String[] essenAuswahl = {"Schnitzel", "Würstchen", "Suppe", "Toast", "Pommes", "Döner"};
        JComboBox essen_box = new JComboBox(essenAuswahl);
        essen_text.setLabelFor(essen_box);
        auswahl.add(essen_box);


        */
        order_frame.add(auswahl, BorderLayout.CENTER);

        JPanel orders = new JPanel();

        JScrollPane orders_scroll = new JScrollPane(orders, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        order_frame.add(orders_scroll, BorderLayout.CENTER);

        JPanel one_order = new JPanel(new SpringLayout());
        JLabel essen_text = new JLabel("Mahlzeit:");
        one_order.add(essen_text);

        String[] essenAuswahl = {"Schnitzel", "Würstchen", "Suppe", "Toast", "Pommes", "Döner"};
        JComboBox essen_box = new JComboBox(essenAuswahl);
        essen_text.setLabelFor(essen_box);
        one_order.add(essen_box);

        JLabel trinken_text = new JLabel("Getränk:");
        one_order.add(trinken_text);

        String[] trinkenAuswahl = {"Wasser", "Cola", "Fanta", "Sprite", "Uludag", "Ayran"};
        JComboBox trinken_box = new JComboBox(trinkenAuswahl);
        trinken_text.setLabelFor(trinken_box);
        one_order.add(trinken_box);

        SpringUtilities.makeGrid(one_order, 2, 2, 6, 30, 6, 10);

        orders.add(one_order, BorderLayout.WEST);
        order_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        order_frame.setVisible(true);


    }

}
