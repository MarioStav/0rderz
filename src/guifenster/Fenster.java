package guifenster;

/**
 * Created by Mario on 27.03.2017.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import static java.awt.Font.*;

public class Fenster extends JFrame{

    private Font font = new Font("LucidaSans", ITALIC, 20);
    private JMenuBar jMenuBar = new JMenuBar();
    private boolean boolConstant = true;
    private JPanel OrderPanel = new JPanel();
    private boolean help = true;

    public void addBestellung(JPanel jPanel){
        JPanel pn = new JPanel();
        if(this.help){
            JButton button = new JButton("Add Order");
            pn.setPreferredSize(new Dimension(100,35));
            button.setLocation(100,0);
            pn.add(button);
            jPanel.add(pn);
            this.help = false;
        }else {
            pn.setPreferredSize(new Dimension(400, 100));
            pn.setBackground(new Color(53, 33, 200));
            jPanel.add(pn);
        }
    }

    public Fenster() {

        this.setTitle("0rderz");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(1500, 800);
        this.setLocation(200, 100);
        this.setResizable(false);
        JLabel uberlabel = new JLabel("Restaurantstruktur (inkl. Raucherbereich & Eingang)");
        uberlabel.setFont(this.font);
        uberlabel.setHorizontalTextPosition(SwingConstants.LEFT);
        this.jMenuBar.add(uberlabel);
        this.addMenu("About");
        this.addMenu("Login");
        this.addMenu("Registrieren");
        this.setJMenuBar(this.jMenuBar);
        this.OrderPanel.setSize(1200,500);
        this.OrderPanel.setPreferredSize(new Dimension(400,500));
        this.OrderPanel.setBackground(new Color(120, 125, 125));
        this.addBestellung(this.OrderPanel);
        RestaurantStruktur zb = new RestaurantStruktur();
        this.getContentPane().add(zb);
        this.getContentPane().add(this.OrderPanel,BorderLayout.EAST);
        this.setVisible(true);

    }

    public void addMenu (String s){
        //Adds a new Menu from the right side
        JMenu menu = new JMenu(s);
        menu.setHorizontalTextPosition(SwingConstants.RIGHT);
        menu.setFont(this.font);
        if (this.boolConstant){
            this.jMenuBar.add(Box.createGlue());
            this.boolConstant = false;
        }
        menu.addMenuListener(new MenuListener() {
            JOptionPane jOptionPane = new JOptionPane();
            @Override
            public void menuSelected(MenuEvent e) {
                jOptionPane.showMessageDialog(null,"KA");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        this.jMenuBar.add(menu);
    }

    public static void main(String[] args) {
        Fenster f = new Fenster();
    }
}
