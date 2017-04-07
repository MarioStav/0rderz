package guifenster;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mario on 06.04.2017.
 */
public class Tische extends JPanel {

    private int ktID = 0;
    private  double ktV = 3;

    public void paint(Graphics g) {
        //zeichnet einen weißen Tisch
        super.paint(g);
        for (int i = 20; i < 1100; i += 110) {
            for (int j = 80; j < 1100; j += 250) {
                paintTable(i, j, g);
            }
        }

    }

    public void paintTable(int x, int y, Graphics g) {
        if(this.ktID % this.ktV == 0) {
            g.setColor(new Color(255, 255, 255));
            g.fillOval(x, y, 100, 100);
            g.setColor(new Color(0, 0, 0));
            g.drawOval(x, y, 100, 100);
            if (this.ktID % (this.ktV*4) == 0){
                g.setColor(new Color(0, 0, 0));
                g.fillOval(x, y, 100, 100);
            }
        }
        this.ktID ++;
    }

}