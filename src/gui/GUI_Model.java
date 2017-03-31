package gui;

/**
 * Created by Mario on 29.03.2017.
 */
public class GUI_Model {
    private int calculationValue;

    public void addTwoNumbers(int nr1, int nr2){
        calculationValue = nr1 + nr2;

    }

    public int getCalculationValue(){
        return calculationValue;
    }


}
