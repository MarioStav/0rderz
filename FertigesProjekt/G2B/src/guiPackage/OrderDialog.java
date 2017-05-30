/**@author Mario \n @since (30.05.2017)
 * @description this dialog asks the servant for adding a new Order to the JTable. Moreover it prints out the JTable*/

package guiPackage;

import database.DBConnect;
import orderPackage.CurrentTime;
import orderPackage.OrdersFrame;
import orderPackage.UtilArrayList;
import SignIn.LoginFrame;

import java.awt.event.WindowEvent;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.util.TreeMap;

import static java.lang.Math.round;

public class OrderDialog extends JDialog{

    /**
     * all necessary data
     */
    //all necessary data
    private LoginFrame lg = new LoginFrame();
    private int tableInt = 0;
    private JComboBox tableComboBox;
    private JComboBox drinkingComboBox;
    private String selectedTable = "";
    private String personnr = "";
    private JComboBox personComboBox;
    private JComboBox eatingComboBox;
    private UtilArrayList eatingArrayList = new UtilArrayList();
    private UtilArrayList drinkingArrayList = new UtilArrayList();
    private boolean issf = false;
    private boolean isst = false;


    /**
     *artificial get method for returning the list with the drinks
     * @return String[]
     */
    //artificial get method for returning the list with the drinks
    public String[] generateDrinkingList(){
        String[] drinkingList = {"Fanta","Cola", "Sprite","Bier","Wein"};
        return drinkingList;
    }

    /**
     * artificial get method for returning the list with the amount of persons
     * @return String[]
     */
    //artificial get method for returning the list with the amount of persons
    public String[] generatePersonList(){
        String[] personList = new String[12];
        for (int i = 0; i < 12; i++) {
            personList[i] = "" + (i + 1);
        }
        return personList;
    }

    /**artificial get method for returning the list with the meals*/
    public String[] generateEatingList(){
        String[] EatingList = {"Pizza", "Schnitzel", "Salat" , "Burger" , "Pommes"};
        return EatingList;
    }

    //normal constructor; checks if someone is logged in
    public OrderDialog(){
        if (lg.loged_in == ""){
            JOptionPane.showConfirmDialog(null, "Sie müssen sich zuerst anmelden, " +
                    "um Bestellungen hinzufügen zu können", "Kein Zugriff", JOptionPane.PLAIN_MESSAGE);
        }else{
            configure(0);
        }
    }

    //artificial get method for returning the list with the tables
    /**@return  tableList is the list with all the tables in it; needed to initialize the JComboBox*/
    public String[] generateTableList(){
        String[] tableList = new String[11];
        for (int i = 0; i < 11; i++) {
            tableList[i] = "Tisch " + (i+1) ;
        }
        return tableList;
    }

    /**
     * method to create OrderDialog; default option with the selected table
     * @param in int
     */
    public OrderDialog(int in){
        if (lg.loged_in == ""){
            JOptionPane.showConfirmDialog(null, "Sie müssen sich zuerst anmelden, " +
                    "um Bestellungen hinzufügen zu können", "Kein Zugriff", JOptionPane.PLAIN_MESSAGE);
        }else{
            this.tableInt = in;
            configure(tableInt);
        }
    }

    //configuring method for the dialog window itself
    public void configure(int ti){
        setLayout(new GridBagLayout());
        setTitle("Neue Bestellung");
        setResizable(false);
        setSize(new Dimension(380,370));
        setLocation(500,300);
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
        setAlwaysOnTop(true);
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
    }

    /**
     * setter method for the drinking - gui - elements
     * @param gb GridBagConstraints
     */
    //setter method for the drinking - gui - elements
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

    /**
     *setter method for the meal - gui - elements
     * @param gb GridBagConstraints
     */
    //setter method for the meal - gui - elements
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
        gb.anchor = GridBagConstraints.SOUTHEAST;
        gb.gridwidth = 2;
        gb.gridheight = 1;
        gb.insets = new Insets(5,5,0,0);
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

    /**
     * setter method for the radio buttons (gui)
     * @param gb GridBagConstraints
     */
    //setter method for the radio buttons (gui)
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

    /**
     * getter for the number of the table
     * @return nr int
     */
    //getter for the number of the table
    public int getTableNr (){
        String[] parts = this.selectedTable.split(" ");
        int nr = Integer.parseInt(parts[1]);
        return nr;
    }

    //returning the string of a treemap; looking like this -> 2x Pizza,...
    /**@return  help is the treemap as a string*/
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

    /**
     * prices and guts are fixed. The servants can't decide whether the restaurant owns something or not.
     * @param eatingTreeMap
     * @param drinkingTreeMap
     * @return erg double
     */
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
        round(erg);
        return erg;
    }

    /**
     * get yes or no from the radio buttons
     * @param isReserved
     * @return isRes boolean
     */
    //get yes or no from the radio buttons
    public String getStringFromBool(boolean isReserved){
        String isRes = "";
        if (isReserved){
            isRes += "Ja";
        }else{
            isRes += "Nein";
        }
        return isRes;
    }


    //setting the BIG commit button
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
            DBConnect dbConnect = new DBConnect();
            CurrentTime ct = new CurrentTime();
            TreeMap<String,Integer> eatingTreeMap = this.eatingArrayList.toTreeMap(this.eatingArrayList);
            TreeMap<String,Integer> drinkingTreeMap = this.drinkingArrayList.toTreeMap(this.drinkingArrayList);
            dbConnect.insert_b(ct.timeString, this.getTableNr() ,this.lg.loged_in, this.personnr, getStringFromBool(this.isst)
                    ,toStringTreeMap(eatingTreeMap), toStringTreeMap(drinkingTreeMap),sumOrders(eatingTreeMap,drinkingTreeMap));
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            OrdersFrame.jTable.setModel(OrdersFrame.createJTable_o());
        });

        add(jb,gb);
    }

    //setting the gui elements of the person row
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

    //setting the gui elements of the table row
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

    //getting and setting the table that was clicked as a jbutton
    public void setAndGetSelectedTable(int i,JComboBox jcb) {
        //set default to the desk that was clicked
        if (i != 0){
            jcb.setSelectedIndex(i-1);
            this.selectedTable = jcb.getSelectedItem().toString();
        }
    }

    /**
     * setting the first row of the dialog
     * @param gb
     * @param head
     */
    //setting the first row of the dialog
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
        OrderDialog orderDialog = new OrderDialog(0);
    }

}
