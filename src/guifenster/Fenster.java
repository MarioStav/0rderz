package guifenster;

/**
 * Created by Mario on 27.03.2017.
 */

import java.awt.*;
import javax.swing.*;

import static java.awt.Font.*;

public class Fenster {



    public static void main(String[] args){

        JFrame frame = new JFrame("0rderz");
        frame.setSize(3000,1000);
        Font ff = new Font("LucidaSans", ITALIC, 30);
        JLabel label = new JLabel("0rderz");
        frame.add(label);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Hallo");
        Font f = new Font("LucidaSans",BOLD + ITALIC,15);
        menu.setFont(f);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        Color cc = JColorChooser.showDialog(null,"Farbauswahl", Color.BLACK);
        frame.getContentPane().setBackground(cc);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
