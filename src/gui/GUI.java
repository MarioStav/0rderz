package gui;

/**
 * Created by Mario on 28.03.2017.
 */
public class GUI {

    //Singleton Instance
    private static GUI instance = null;
    public static GUI getInstance(){
        if(instance == null){
           instance = new GUI();
        }
        return instance;
    }

    public static void main(String[] args) {
        /*GUI.getInstance();
        GUI_View view = new GUI_View();
        GUI_Model model = new GUI_Model();
        GUI_Controller controller = new GUI_Controller(view, model);
        view.setVisible(true);
*/


    }
}
