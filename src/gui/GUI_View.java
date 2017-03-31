package gui;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Mario on 29.03.2017.
 */
public class GUI_View extends JFrame {

    private JTextField fnr = new JTextField(10);
    private JLabel additionLabel = new JLabel("+");
    private JTextField snr = new JTextField(10);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);

    GUI_View(){
        JPanel calcPanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,200);
        calcPanel.add(fnr);
        calcPanel.add(additionLabel);
        calcPanel.add(snr);
        calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);

        this.add(calcPanel);
    }

    public int getFirstNumer(){
        return Integer.parseInt(fnr.getText());

    }

    public int getSecondNumer(){
        return Integer.parseInt(snr.getText());

    }

    public int getCalcSolution(){
        return Integer.parseInt(calcSolution.getText());

    }

    public void setCalcSolution(int solution){
        calcSolution.setText(Integer.toString(solution));
    }

    void addCalculationListener(ActionListener listenerCalcButton){
        calculateButton.addActionListener(listenerCalcButton);
    }

    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
