package guifenster;


import best.CurrentTime;
import classesAndMethods.OwnArrayList;
import database.DBConnect;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mario on 09.05.2017.
 */
public class Dia extends JDialog{

    private int tableInt = 0;
    private JComboBox tableComboBox;
    private JComboBox drinkingComboBox;
    public String selectedTable = "";
    public String personnr = "";
    private JComboBox personComboBox;
    private JComboBox eatingComboBox;
    public OwnArrayList eatingArrayList = new OwnArrayList();
    public OwnArrayList drinkingArrayList = new OwnArrayList();
    public boolean issf = false;
    public boolean isst = false;

    public String[] generateDrinkingList(){
        String[] drinkingList = {"Melange","Cappucino","Espresso","Fanta","Capri Sonne","Cola", "Sprite",
                "Orangensaft","Apfelsaft","Bier","Schnaps","Wein"};
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
        String[] EatingList = {"Pizza", "Schnitzel", "Fisch","Salat","Burger","Pommes","Reis"};
        return EatingList;
    }

    public Dia(){
        configure(0);
    }

    public String[] generateTableList(){
        String[] tableList = new String[11];
        for (int i = 0; i < 11; i++) {
            tableList[i] = "Tisch " + (i+1) ;
        }
        return tableList;
    }

    public Dia(int in){
        this.tableInt = in;
        configure(tableInt);
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
        drinkingLabel.setText("Trinken:");
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

    public void setCommit(GridBagConstraints gb){
        JButton jb = new JButton("Bestellung Hinzufügen");
        gb.gridy = 20;
        gb.gridx = 0;
        gb.anchor = GridBagConstraints.WEST;
        gb.insets = new Insets(10,5,5,5);
        jb.addActionListener(e -> {
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
            dbConnect.insert_b(getTableNr(),"3738041099",ct.timeString,
                    this.eatingArrayList.ownToString(eatingArrayList),this.drinkingArrayList.ownToString(drinkingArrayList),20.0);
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
