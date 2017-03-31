package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mario on 29.03.2017.
 */
public class GUI_Controller {
    private GUI_View view;
    private GUI_Model model;

    public GUI_Controller(GUI_View view, GUI_Model model){

        this.view = view;
        this.model = model;

        this.view.addCalculationListener(new CalculationListener());
    }

    class CalculationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int fnr, snr = 0;

            try{
                fnr = view.getFirstNumer();
                snr = view.getSecondNumer();

                model.addTwoNumbers(fnr,snr);

                view.setCalcSolution(model.getCalculationValue());
            }

            catch (NumberFormatException ex){

                view.displayErrorMessage("Enter 2 Ints");
            }
        }
    }

}
