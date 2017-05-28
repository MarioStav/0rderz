package guifenster;


import best.CurrentTime;
import best.OrdersFrame;
import classesAndMethods.OwnArrayList;
import database.DBConnect;
import guiAnmeldung.LoginFrame;

import java.awt.event.WindowEvent;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

/**
 * Created by Mario on 09.05.2017.
 */
public class Dia extends JDialog{

    private LoginFrame lg = new LoginFrame();
    private int tableInt = 0;
    private JComboBox tableComboBox;
    private JComboBox drinkingComboBox;
    private String selectedTable = "";
    private String personnr = "";
    private JComboBox personComboBox;
    private JComboBox eatingComboBox;
    private OwnArrayList eatingArrayList = new OwnArrayList();
    private OwnArrayList drinkingArrayList = new OwnArrayList();
    private boolean issf = false;
    private boolean isst = false;

    public String[] generateDrinkingList(){
        String[] drinkingList = {"Fanta","Cola", "Sprite","Bier","Wein"};
        return drinkingList;
    }

    public String[] generatePersonList(){
        String[] personList = new String[12];
        for (int i = 0; i < 12; i++) {
            personList[i] = "" + (i + 1);
        }
        return personList;
    }

    public String[] generateEatingList(){
        String[] EatingList = {"Pizza", "Schnitzel", "Salat" , "Burger" , "Pommes"};
        return EatingList;
    }

    public Dia(){
        if (lg.loged_in == ""){
            JOptionPane.showMessageDialog(null, "Sie müssen sich anmelden, bevor Sie" +
                    " eine Bestellung hinzufügen können");
        }else{
            configure(0);
        }
    }

    public String[] generateTableList(){
        String[] tableList = new String[11];
        for (int i = 0; i < 11; i++) {
            tableList[i] = "Tisch " + (i+1) ;
        }
        return tableList;
    }

    public Dia(int in){
        if (lg.loged_in == ""){
            JOptionPane.showMessageDialog(null, "Sie müssen sich anmelden, bevor Sie" +
                    " eine Bestellung hinzufügen können");
        }else{
            this.tableInt = in;
            configure(tableInt);
        }
    }

    public void configure(int ti){
        setLayout(new GridBagLayout());
        setTitle("Neue Bestellung");
        setResizable(false);
        setSize(new Dimension(380,370));
        setLocation(600,200);
        GridBagConstraints bagConstraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setHeader(bagConstraints, "Neue Bestellung hinzufügen");
        setTableCombo(ti,bagConstraints);
        setPersonCombo(bagConstraints);
        setReservedRadio(bagConstraints);
        setEating(bagConstraints);
        setDrinking(bagConstraints);
        setCommit(bagConstraints);
        setVisible(true);
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    }

    public void setDrinking(GridBagConstraints gb) {
        JLabel drinkingLabel = new JLabel();
        drinkingLabel.setText("Getränke:");
        gb.gridheight = 2;
        gb.gridwidth = 1;
        gb.insets = new Insets(5,5,10,5);
        gb.anchor = GridBagConstraints.WEST;
        drinkingLabel.setFont(new Font("font",Font.ITALIC,16));
        add(drinkingLabel, gb);
        gb.gridwidth = 2;
        gb.gridheight = 1;
        gb.insets = new Insets(5,5,0,0);
        this.drinkingComboBox = new JComboBox(generateDrinkingList());
        add(this.drinkingComboBox, gb);
        JButton addDrinking = new JButton("Hinzufügen");
        addDrinking.addActionListener(e -> {
            this.drinkingArrayList.addToOAL(this.drinkingComboBox,this.drinkingArrayList);
        });
        gb.insets = new Insets(5,0,0,0);
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(addDrinking, gb);
        JPanel nullPanel = new JPanel();
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(nullPanel,gb);
    }

    public void setEating(GridBagConstraints gb) {
        JLabel eatingLabel = new JLabel();
        eatingLabel.setText("Essen:");
        gb.gridheight = 2;
        gb.gridwidth = 1;
        gb.insets = new Insets(5,5,10,5);
        gb.anchor = GridBagConstraints.WEST;
        eatingLabel.setFont(new Font("font",Font.ITALIC,16));
        add(eatingLabel, gb);
        gb.gridwidth = 2;
        gb.gridheight = 1;
        gb.insets = new Insets(5,5,0,0);
        this.eatingComboBox = new JComboBox(generateEatingList());
        add(this.eatingComboBox, gb);
        JButton addEating = new JButton("Hinzufügen");
        addEating.addActionListener(e -> {
            this.eatingArrayList.addToOAL(this.eatingComboBox,this.eatingArrayList);
        });
        gb.insets = new Insets(5,0,0,0);
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(addEating, gb);
        JPanel nullPanel = new JPanel();
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(nullPanel,gb);
    }

    public void setReservedRadio(GridBagConstraints gb) {
        JLabel reservedLabel = new JLabel("Reservierung: ");
        reservedLabel.setFont(new Font("font",Font.ITALIC,16));
        gb.gridheight = 1;
        gb.gridwidth = 1;
        gb.anchor = GridBagConstraints.WEST;
        add(reservedLabel,gb);
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton reservedt = new JRadioButton("Ja", false);
        JRadioButton reservedf = new JRadioButton("Nein", false);
        buttonGroup.add(reservedf);
        buttonGroup.add(reservedt);
        reservedf.addActionListener(e -> {
            if(reservedf.isSelected()) {
                issf = true;
                isst = false;
            }
        });
        reservedt.addActionListener(e -> {
            if(reservedt.isSelected()){
                issf = false;
                isst = true;
            }
        });
        add(reservedt, gb);
        gb.insets = new Insets(0,0,0,10);
        add(reservedf, gb);
        JPanel nullPanel = new JPanel();
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(nullPanel,gb);
    }

    public int getTableNr (){
        String[] parts = this.selectedTable.split(" ");
        int nr = Integer.parseInt(parts[1]);
        return nr;
    }

    public String toStringTreeMap(TreeMap<String,Integer> treeMap){
        String help = "";
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            help += value + "x:";
            help += key + " ";
        }
        return help;
    }

    public double sumOrders(TreeMap<String,Integer> eatingTreeMap, TreeMap<String,Integer> drinkingTreeMap){
        //prices and guts are fixed. The servants can't decide whether the restaurant owns something or not.
        double erg = 0;
        for (Map.Entry<String, Integer> entryEating : eatingTreeMap.entrySet()) {
            if (entryEating.getKey() == "Pizza"){
                erg += 7.2 * entryEating.getValue();
            }else if(entryEating.getKey() == "Burger"){
                erg += 4.5 * entryEating.getValue();
            }else if(entryEating.getKey() == "Schnitzel"){
                erg += 8.0 * entryEating.getValue();
            }else if(entryEating.getKey() == "Pommes"){
                erg += 2.2 * entryEating.getValue();
            }else if(entryEating.getKey() == "Salat"){
                erg += 1.5 * entryEating.getValue();
            }
        }
        for (Map.Entry<String, Integer> drinkingEntry : drinkingTreeMap.entrySet()) {
            if (drinkingEntry.getKey() == "Fanta" || drinkingEntry.getKey() == "Cola" || drinkingEntry.getKey()
                    == "Sprite" ){
                erg += 2.3 * drinkingEntry.getValue();
            }else if(drinkingEntry.getKey() == "Bier"){
                erg += 4.5 * drinkingEntry.getValue();
            }else if(drinkingEntry.getKey() == "Wein"){
                erg += 17.8 * drinkingEntry.getValue();
            }
        }
        return erg;
    }

    public void setCommit(GridBagConstraints gb){
        JButton jb = new JButton("Bestellung Hinzufügen");
        gb.gridy = 20;
        gb.gridx = 0;
        gb.anchor = GridBagConstraints.WEST;
        gb.insets = new Insets(10,5,5,5);
        jb.addActionListener(e -> {
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);
            this.personnr = this.personComboBox.getSelectedItem().toString();
            this.selectedTable = this.tableComboBox.getSelectedItem().toString();
            System.out.println(this.selectedTable);
            System.out.println(this.personnr);
            System.out.println("JA: " + this.isst);
            System.out.println("NEIN: " + this.issf);
            System.out.println(this.eatingArrayList.ownToString(this.eatingArrayList));
            System.out.println(this.drinkingArrayList.ownToString(this.drinkingArrayList));
            DBConnect dbConnect = new DBConnect();
            CurrentTime ct = new CurrentTime();
            TreeMap<String,Integer> eatingTreeMap = this.eatingArrayList.toTreeMap(this.eatingArrayList);
            TreeMap<String,Integer> drinkingTreeMap = this.drinkingArrayList.toTreeMap(this.drinkingArrayList);
            System.out.println(sumOrders(eatingTreeMap,drinkingTreeMap));
            dbConnect.insert_b(getTableNr(), this.lg.loged_in ,ct.timeString, toStringTreeMap(eatingTreeMap)
                    , toStringTreeMap(drinkingTreeMap),20.0);
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            OrdersFrame ordersFrame = new OrdersFrame();
            ordersFrame.frameElems();
        });

        add(jb,gb);
    }

    public void setPersonCombo(GridBagConstraints gb){
        JLabel personLabel = new JLabel("Anzahl Personen: ");
        personLabel.setFont(new Font("font",Font.ITALIC,16));
        this.personComboBox = new JComboBox(generatePersonList());
        gb.gridheight = 2;
        gb.gridwidth = 1;
        gb.anchor = GridBagConstraints.WEST;
        add(personLabel, gb);
        gb.gridwidth = GridBagConstraints.REMAINDER;
        add(this.personComboBox, gb);
    }

    public void setTableCombo(int ti,GridBagConstraints gb) {
        //Method to set the first row
        JLabel tableLabel = new JLabel();
        tableLabel.setText("Ausgewählter Tisch: ");
        gb.gridheight = 2;
        gb.gridwidth = 1;
        gb.insets = new Insets(5,5,10,5);
        gb.anchor = GridBagConstraints.WEST;
        tableLabel.setFont(new Font("font",Font.ITALIC,16));
        add(tableLabel, gb);
        gb.gridwidth = GridBagConstraints.REMAINDER;
        gb.gridheight = 3;
        String[] tableList = generateTableList();
        this.tableComboBox = new JComboBox(tableList);
        setAndGetSelectedTable(ti, this.tableComboBox);
        add(this.tableComboBox, gb);
    }

    public void setAndGetSelectedTable(int i,JComboBox jcb) {
        //set default to the desk that was clicked
        if (i != 0){
            jcb.setSelectedIndex(i-1);
            this.selectedTable = jcb.getSelectedItem().toString();
        }
    }

    public void setHeader(GridBagConstraints gb, String head){
        gb.gridwidth = GridBagConstraints.REMAINDER;
        gb.gridheight = 2;
        gb.insets = new Insets(0, 2, 10, 5);
        gb.anchor = GridBagConstraints.WEST;
        Label header = new Label(head);
        header.setFont(new Font("Sans Serif", Font.BOLD, 18));
        add(header, gb);
    }

    public static void main(String[] args) {
        Dia dia = new Dia(0);
    }

}
