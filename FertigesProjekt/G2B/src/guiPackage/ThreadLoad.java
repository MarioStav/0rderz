/**
 * @author Christoph @since(29.05.2017)
 * @description the first thread (main/gui) waits until the other (loadingscreen) finished.
 * */


package guiPackage;

import javax.swing.*;
import java.awt.*;

public class ThreadLoad extends Thread {

    int max = 0;
    @Override
    public void run() {

        synchronized (this) {

            JFrame loading = new JFrame("Die Software wird geladen!");
            loading.setLayout(new BorderLayout());
            loading.setSize(600,650);
            JPanel loading_p = new JPanel();
            ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("zeroIcon.png")));
            JLabel icon_label = new JLabel();
            icon_label.setIcon(icon);
            loading_p.add(icon_label);
            loading.add(loading_p, BorderLayout.NORTH);
            JPanel bar = new JPanel();
            int max = 0;
            JProgressBar progressBar = new JProgressBar(0,20);
            bar.add(progressBar);
            loading.add(bar, BorderLayout.SOUTH);
            loading.setResizable(false);
            loading.setLocation(700,300);
            loading.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            loading.setVisible(true);
            while (max <= 20) {

                progressBar.setValue(max);
                max++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (max > 20) {

                loading.setVisible(false);

            }
            notify();

        }

    }

}
