package Examples;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class frame extends JFrame implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        frame f = new frame();
        f.setVisible(true);
        f.setSize(450,120);
    }

    /**
     * Create the frame.
     */

    public JProgressBar bar;

    public frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 120);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        bar = new JProgressBar();
        bar.setStringPainted(true);
        bar.setBounds(6, 50, 438, 32);
        contentPane.add(bar);

        JLabel lblNewLabel = new JLabel(
                "Percent of for loop completion");
        lblNewLabel.setBounds(6, 6, 279, 16);
        contentPane.add(lblNewLabel);

        JButton btnStart = new JButton("START");
        btnStart.setBounds(327, 1, 117, 29);
        btnStart.addActionListener(this);
        contentPane.add(btnStart);
    }

    public int i, progress;
    public void actionPerformed(ActionEvent e) {
        updater u = new updater();

        u.start();
        for( i =0; i < 100; i++){
            progress = i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println(i);
        }

    }

}