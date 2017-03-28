package gui;

/**
 * Created by Mario on 28.03.2017.
 */
public class GUI {
    private static GUI instance = null;
    public static GUI getInstance(){
        if(instance == null){
           instance = new GUI();
        }
        return instance;
    }

    public static void main(String[] args) {
        GUI.getInstance();

    }
}
