//"jdbc:sqlite:C:/Users/Mario/IdeaProjects/G2B/db";

package guifenster;

import about.About;
import best.OrdersFrame;
import database.DBConnect;
import guiAnmeldung.CompleteFrame;
import guiAnmeldung.RegistryFrame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.*;

public class Fenster extends JFrame implements MenuBarSettings {

    private About about = new About();
    private CompleteFrame completeFrame = new CompleteFrame();
    private OrdersFrame ordersFrame = new OrdersFrame();
    private Font font = new Font("LucidaSans", ITALIC, 20);
    private JMenuBar jMenuBar = new JMenuBar();
    private boolean boolConstant = true;
    private JPanel structurePanel = new JPanel();
    private JPanel smokerPanel = new JPanel();
    private JButton table7 = new JButton();
    private JButton table8 = new JButton();
    private JButton table9 = new JButton();
    private JButton table6 = new JButton();
    private JLabel smokers = new JLabel();
    private JButton table3 = new JButton();
    private JButton table4 = new JButton();
    private JButton table2 = new JButton();
    private JButton table1 = new JButton();
    private JButton table10 = new JButton();
    private JLabel nonSmokers = new JLabel();
    private JButton table11 = new JButton();
    private JButton table5 = new JButton();

    public Fenster() {

        this.setTitle("0rderz");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        this.getContentPane().setLayout(new BorderLayout());
        this.addMenu("Aktuelle Bestellungen");
        this.addMenu("Anmelden");
        this.addMenu("About");
        this.setJMenuBar(this.jMenuBar);
        this.setMainStructure();
        this.setVisible(true);
    }

    public void setMainStructure() {

        //Made with the GUI Designer

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setResizable(false);

        smokerPanel.setBackground(Color.GRAY);

        table7.setText("Tisch 7");
        table7.setBackground(Color.WHITE);
        table7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                initDia(table7);
            }
        });

        table8.setText("Tisch 8");
        table8.setBackground(Color.WHITE);
        table8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table8);
            }
        });

        table9.setText("Tisch 9");
        table9.setBackground(Color.WHITE);
        table9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table9);
            }
        });

        table6.setText("Tisch 6");
        table6.setBackground(Color.WHITE);
        table6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table6);
            }
        });

        smokers.setText("Raucher Bereich");
        smokers.setSize(new Dimension(20, 20));
        smokers.setFont(this.font);
        smokers.setForeground(Color.WHITE);

        javax.swing.GroupLayout smokerPanelLayout = new javax.swing.GroupLayout(smokerPanel);
        smokerPanel.setLayout(smokerPanelLayout);
        smokerPanelLayout.setHorizontalGroup(
                smokerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(smokerPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(smokers, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(table7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                        .addGroup(smokerPanelLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(smokerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(table6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(table9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, smokerPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(table8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
        );
        smokerPanelLayout.setVerticalGroup(
                smokerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(smokerPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(smokerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(table7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(smokers, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(table9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(table8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(table6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
        );

        table3.setText("Tisch 3");
        table3.setBackground(Color.WHITE);
        table3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table3);
            }
        });

        table4.setText("Tisch 4");
        table4.setBackground(Color.WHITE);
        table4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table4);
            }
        });

        table2.setText("Tisch 2");
        table2.setBackground(Color.WHITE);
        table2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table2);
            }
        });

        table1.setText("Tisch 1");
        table1.setBackground(Color.WHITE);
        table1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table1);
            }
        });

        table10.setText("Tisch 10");
        table10.setBackground(Color.WHITE);
        table10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table10);
            }
        });

        table5.setText("Tisch 5");
        table5.setBackground(Color.WHITE);
        table5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table5);
            }
        });

        nonSmokers.setText("Nicht-Raucher Bereich");
        nonSmokers.setSize(new Dimension(20, 20));
        nonSmokers.setFont(this.font);
        nonSmokers.setForeground(Color.WHITE);

        table11.setText("Tisch 11");
        table11.setBackground(Color.WHITE);
        table11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initDia(table11);
            }
        });

        javax.swing.GroupLayout structurePanelLayout = new javax.swing.GroupLayout(structurePanel);
        structurePanel.setLayout(structurePanelLayout);
        structurePanelLayout.setHorizontalGroup(
                structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, structurePanelLayout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addComponent(table2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(table4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(236, 236, 236))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, structurePanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(nonSmokers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(275, 275, 275)
                                .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(table3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(table1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, structurePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(smokerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(table11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(table10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 378, Short.MAX_VALUE))
                        .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, structurePanelLayout.createSequentialGroup()
                                        .addContainerGap(890, Short.MAX_VALUE)
                                        .addComponent(table5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)))
        );
        structurePanelLayout.setVerticalGroup(
                structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(structurePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(structurePanelLayout.createSequentialGroup()
                                                .addComponent(table1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(table11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(137, 137, 137)
                                                .addComponent(table10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(smokerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                                .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(table3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nonSmokers, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(table4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(table2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(structurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(structurePanelLayout.createSequentialGroup()
                                        .addGap(338, 338, 338)
                                        .addComponent(table5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(357, Short.MAX_VALUE)))
        );

        structurePanel.setBackground(Color.LIGHT_GRAY);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(structurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(structurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public void initDia(JButton jb) {
        //method to initialize the dialog frame for the specific button
        String s = jb.getText();
        int i = Character.getNumericValue(s.charAt(s.length() - 1));
        if ((jb != table11) && (jb != table10)) {
            Dia dia = new Dia(i);
        } else {
            if (jb == table11) {
                Dia dia = new Dia(11);
            } else if (jb == table10) {
                Dia dia = new Dia(10);
            }
        }
    }

    public void addMenu(String s) {
        //Adds a new Menu from the right side
        JMenu menu = new JMenu(s);
        menu.setHorizontalTextPosition(SwingConstants.RIGHT);
        menu.setFont(this.font);
        Border bo = new LineBorder(Color.LIGHT_GRAY);
        menu.setBorder(bo);
        if (this.boolConstant) {
            this.jMenuBar.add(Box.createGlue());
            this.boolConstant = false;
        }

        if (s == "Anmelden") {
            menu.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    completeFrame.frameElems();
                }

                @Override
                public void menuDeselected(MenuEvent e) {

                }

                @Override
                public void menuCanceled(MenuEvent e) {

                }
            });
        }

        if (s == "About") {
            menu.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    String[] arg = new String[]{};
                    about.main(arg);
                }

                @Override
                public void menuDeselected(MenuEvent e) {

                }

                @Override
                public void menuCanceled(MenuEvent e) {

                }
            });
        }

        if (s == "Aktuelle Bestellungen") {
            menu.addMenuListener(new MenuListener() {
                @Override
                public void menuSelected(MenuEvent e) {
                    ordersFrame.frameElems();
                }

                @Override
                public void menuDeselected(MenuEvent e) {

                }

                @Override
                public void menuCanceled(MenuEvent e) {

                }
            });
        }
        this.jMenuBar.add(menu);
    }

    public static void main(String[] args) {
        Fenster fenster = new Fenster();
        DBConnect.DBcreate("G2B.db");
        DBConnect.TableCreate();
        RegistryFrame.createXML("addresses", "address", "Addresses.xml");
    }
}

