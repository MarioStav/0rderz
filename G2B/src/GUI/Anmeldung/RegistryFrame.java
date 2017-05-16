package GUI.Anmeldung;

import Database.DBConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Christoph on 29.03.2017.
 */

public class RegistryFrame extends JFrame {

    public static void registryElems(JTable kellner) {

        //create frame
        JFrame registry = new JFrame("Registrierung");
        //set size
        registry.setSize(400, 350);
        registry.setResizable(false);
        registry.setLocation(700,300);
        registry.setLayout(new BorderLayout());
        /*
        String[] labels = {"Vorname: ", "Nachname: ", "SozialversicherungsNr: ", "PLZ: ", "Straße: ", "HausNr: "};
        String[] labels_pw = {"Passwort: ", "Passwort bestätigen: "};
        int anz = labels.length;

        //Create and fill the panel.
        JPanel panel = new JPanel(new SpringLayout());
        for (int i = 0; i < anz; i++) {

            JLabel text = new JLabel(labels[i], JLabel.LEFT);
            panel.add(text);
            JTextField field = new JTextField(10);
            text.setLabelFor(field);
            panel.add(field);

        }
        */
        JPanel panel = new JPanel(new SpringLayout());
        //Vorname
        JLabel text_vname = new JLabel("Vorname:");
        panel.add(text_vname);
        JTextField field_vname = new JTextField(20);
        text_vname.setLabelFor(field_vname);
        panel.add(field_vname);

        //Nachname
        JLabel text_fname = new JLabel("Nachname:");
        panel.add(text_fname);
        JTextField field_fname = new JTextField(20);
        text_fname.setLabelFor(field_fname);
        panel.add(field_fname);

        //Sozialversicherungsnummer
        JLabel text_SVNr = new JLabel("SozialversicherungsNr.:");
        panel.add(text_SVNr);
        JTextField field_SVNr = new JTextField(10);
        text_SVNr.setLabelFor(field_SVNr);
        panel.add(field_SVNr);

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

        //create password fields and texts
        JLabel pw = new JLabel("Passwort", JLabel.LEFT);
        panel.add(pw);
        JPasswordField field_pw = new JPasswordField(10);
        pw.setLabelFor(field_pw);
        panel.add(field_pw);
        JLabel pw2 = new JLabel("Passwort bestätigen", JLabel.LEFT);
        panel.add(pw2);
        JPasswordField field_pw2 = new JPasswordField(10);
        pw.setLabelFor(field_pw2);
        panel.add(field_pw2);

        SpringUtilities.makeGrid(panel, 7, 2, 6, 30, 6, 15);

        JButton confirm = new JButton("Registrieren");

        registry.add(confirm, BorderLayout.PAGE_END);
        registry.add(panel, BorderLayout.NORTH);
        registry.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registryConfirm(confirm, field_pw, field_pw2, field_vname, field_fname, field_SVNr, field_PLZ, field_city, registry, kellner);
        registry.setVisible(true);

    }

    public static void registryConfirm(JButton confirm, JPasswordField pw_field, JPasswordField pw2_field,
                                      JTextField vname_field, JTextField fname_field, JTextField svnr_field,
                                      JTextField plz_field, JTextField city_field, JFrame registry, JTable kellner) {

        DBConnect connect = new DBConnect();
        connect.DBConnect();
        confirm.addActionListener((ActionEvent action) -> {

            String vname = vname_field.getText();
            String fname = fname_field.getText();
            String svnr = svnr_field.getText();
            String plz = plz_field.getText();
            String city = city_field.getText();

            if (Arrays.equals(pw_field.getPassword(), pw2_field.getPassword())) {

                if (isWord(vname) && isWord(fname)) {

                    if (isSVNR(svnr) && isNumber(svnr)) {

                        if (isAdress(plz, city)) {

                            String pw = passwordString(pw_field);
                            if (connect.insert_k(svnr, vname, fname, pw, toInt(plz), city)) {

                                JOptionPane.showConfirmDialog(null, "Erflogreich registriert!", "Succsess", JOptionPane.OK_CANCEL_OPTION);
                                registry.setVisible(false);
                                kellner.setModel(CompleteFrame.createJTable_k());

                            } else {

                                JOptionPane.showConfirmDialog(null, "SVNr existiert schon!", "Succsess", JOptionPane.OK_CANCEL_OPTION);

                            }

                        } else {

                            JOptionPane.showConfirmDialog(null, "Keine gültige Adresse", "Error", JOptionPane.OK_CANCEL_OPTION);

                        }

                    } else {

                        JOptionPane.showConfirmDialog(null, "Keine gültige Sozialversicherungsnummer", "Error", JOptionPane.OK_CANCEL_OPTION);

                    }

                } else {

                    JOptionPane.showConfirmDialog(null, "Vorname oder Nachname ist kein gültiger Name", "Error", JOptionPane.OK_CANCEL_OPTION);

                }

            } else if (pw_field.getPassword().toString().isEmpty() || pw2_field.getPassword().toString().isEmpty()){

                JOptionPane.showConfirmDialog(null, "Passwort-Feld ist leer", "Error", JOptionPane.OK_CANCEL_OPTION);

            } else {

                JOptionPane.showConfirmDialog(null, "Passwörter stimmen nicht überein", "Error", JOptionPane.OK_CANCEL_OPTION);

            }

        });

    }

    private static String passwordString(JPasswordField pw_field) {

        String pw = "";
        char[] pw_array = pw_field.getPassword();
        for (int i = 0; i < pw_array.length; i++) {

            pw += pw_array[i];

        }

        return pw;

    }

    public static boolean isSVNR(String input) {

        int sum = 0;
        if (input.length() != 10) {

            return false;

        } else {

            try {

                sum += Character.getNumericValue(input.charAt(0)) * 3;
                sum += Character.getNumericValue(input.charAt(1)) * 7;
                sum += Character.getNumericValue(input.charAt(2)) * 9;
                sum += Character.getNumericValue(input.charAt(4)) * 5;
                sum += Character.getNumericValue(input.charAt(5)) * 8;
                sum += Character.getNumericValue(input.charAt(6)) * 4;
                sum += Character.getNumericValue(input.charAt(7)) * 2;
                sum += Character.getNumericValue(input.charAt(8)) * 1;
                sum += Character.getNumericValue(input.charAt(9)) * 6;
                sum = sum % 11;
                if (Character.getNumericValue(input.charAt(3)) == sum) {

                    return true;

                } else {

                    return false;

                }

            } catch (NumberFormatException ex) {

                return false;

            }

        }

    }

    public static boolean isWord(String input) {

        input = input.trim();

        if (Pattern.matches("[a-zäöüA-ZÄÖÜ]+", input)) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isNumber(String input) {

        input = input.trim();

        if (Pattern.matches("[0-9]+", input)) {

            return true;

        } else {

            return false;

        }

    }

    public static int toInt(String input) {

        String numbers = "0123456789";
        int int_num = 0;
        char[] num_array = numbers.toCharArray();
        char[] in_array = input.toCharArray();
        for (int i = 0; i < in_array.length; i++) {

            for (int j = 0; j < num_array.length; j++) {

                if (in_array[i] == num_array[j] && i != in_array.length - 1) {

                    int_num += j;
                    int_num *= 10;

                } else if (in_array[i] == num_array[j] && i == in_array.length - 1) {

                    int_num += j;

                }

            }

        }

        return int_num;

    }

    public static boolean isAdress(String plz, String name) {

        HashMap addresses = new HashMap<Integer, String>();
        addresses.put(2700, "Neustadt");
        addresses.put(2753, "Piesting");
        addresses.put(2542, "Kottingbrunn");
        addresses.put(2620, "Neunkirchen");

        int int_plz = toInt(plz);

        if (isWord(name) && isNumber(plz)) {

            if (addresses.containsValue(name) && addresses.containsKey(int_plz)) {

                if (addresses.get(int_plz).equals(name)) {

                    return true;

                } else {

                    return false;

                }

            } else {

                return false;

            }

        } else {

            return false;

        }

    }

}




