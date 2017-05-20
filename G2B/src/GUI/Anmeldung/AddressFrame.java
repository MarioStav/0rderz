package GUI.Anmeldung;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Christoph on 19.05.2017.
 */
public class AddressFrame {

    public static void createAddressFrame() {

        JFrame errorAddress = new JFrame("Error");
        errorAddress.setLayout(new BorderLayout());
        errorAddress.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        errorAddress.setSize(400,180);
        errorAddress.setLocation(700,300);
        errorAddress.setResizable(false);

        JLabel errorMessage = new JLabel("Error: Keine gültige Adresse");
        JLabel options = new JLabel("Adresse ungültig! Wollen Sie eine neue Adresse hinzufügen?");
        errorAddress.add(errorMessage, BorderLayout.NORTH);
        errorAddress.add(options, BorderLayout.NORTH);

        JPanel panel = new JPanel(new SpringLayout());

        //PLZ
        JLabel text_PLZ = new JLabel("PLZ:");
        panel.add(text_PLZ);
        JTextField field_PLZ = new JTextField(6);
        text_PLZ.setLabelFor(field_PLZ);
        panel.add(field_PLZ);

        //Stadt
        JLabel text_city = new JLabel("Stadt:");
        panel.add(text_city);
        JTextField field_city = new JTextField(20);
        text_city.setLabelFor(field_city);
        panel.add(field_city);

        SpringUtilities.makeGrid(panel, 2, 2, 6, 30, 6, 15);

        errorAddress.add(panel, BorderLayout.SOUTH);
        JButton addButton = new JButton("hinzufügen");
        panel.add(addButton);

        addButton.addActionListener(action -> {

            if (RegistryFrame.isNumber(field_PLZ.getText()) && RegistryFrame.isString(field_city.getText())) {

                //prüfen ob adresse schon in xml datei enthalten ist
                if (RegistryFrame.appendXML("Addresses.xml", "address", RegistryFrame.toInt(field_PLZ.getText()), field_city.getText())) {

                    JOptionPane.showConfirmDialog(null, "Erfolgreich hinzugefügt!", "Succsess", JOptionPane.OK_CANCEL_OPTION);
                    errorAddress.setVisible(false);

                } else {

                    JOptionPane.showConfirmDialog(null, "Adresse schon vorhanden", "Info", JOptionPane.OK_CANCEL_OPTION);

                }

            } else {

                JOptionPane.showConfirmDialog(null, "Keine gültige Eingabe!", "Error", JOptionPane.OK_CANCEL_OPTION);

            }

        });

        errorAddress.setVisible(true);

    }

}
