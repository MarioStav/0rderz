/**@author Christoph \n @since (datum) */

package SignIn;

import javax.swing.*;
import java.awt.*;

public class AddressFrame extends CompleteFrame {

    //default constructor method to create the adrress frame which asks for uploading a new address to the database
    public void createAddressFrame(){

        this.setTitle("Adresse Hinzufügen");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(400,180);
        this.setLocation(700,300);
        this.setResizable(false);

        JLabel errorMessage = new JLabel("Error: Keine gültige Adresse");
        JLabel options = new JLabel("Adresse ungültig! Wollen Sie eine neue Adresse hinzufügen?");
        this.add(errorMessage, BorderLayout.NORTH);
        this.add(options, BorderLayout.NORTH);

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

        this.add(panel, BorderLayout.SOUTH);
        JButton addButton = new JButton("Hinzufügen");
        panel.add(addButton);

        addButton.addActionListener(action -> {

            if (RegistryFrame.isNumber(field_PLZ.getText()) && RegistryFrame.isString(field_city.getText())) {

                //prüfen ob adresse schon in xml datei enthalten ist
                if (RegistryFrame.appendXML("Addresses.xml", "address", RegistryFrame.toInt(field_PLZ.getText()), field_city.getText())) {

                    JOptionPane.showConfirmDialog(null, "Adresse erfolgreich hinzugefügt!", "Success", JOptionPane.OK_CANCEL_OPTION);
                    this.setVisible(false);

                } else {

                    JOptionPane.showConfirmDialog(null, "Adresse schon vorhanden", "Info", JOptionPane.OK_CANCEL_OPTION);

                }

            } else {

                JOptionPane.showConfirmDialog(null, "Keine gültige Eingabe!", "Error", JOptionPane.OK_CANCEL_OPTION);

            }

        });

        this.setVisible(true);

    }

}