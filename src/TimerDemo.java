import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;

public class TimerDemo extends JFrame {
    private JButton btnStart;
    private JButton btnStop;
    private JComboBox<String> cboxMin;
    private JComboBox<String> cboxSec;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JLabel lblMin;
    private JLabel lblSec;
    int min, sec;
    Timer timer;
    boolean flag = true;

    public TimerDemo() {
        initComponents();
        for (int i = 0; i < 60; i++) {
            if (i < 10)
                cboxMin.addItem("0" + i);
            else
                cboxMin.addItem("" + i);
        }
        for (int i = 0; i < 60; i++) {
            if (i < 10)
                cboxSec.addItem("0" + i);
            else cboxSec.addItem("" + i);
        }
    }
    private void cboxMinActionPerformed(ActionEvent evt) {
        lblMin.setText("" + cboxMin.getSelectedItem());
        min = Integer.parseInt(lblMin.getText());
        lblMin.setForeground(Color.BLACK);
        lblSec.setForeground(Color.BLACK);
    }
    private void cboxSecActionPerformed(ActionEvent evt) {
        lblSec.setText("" + cboxSec.getSelectedItem());
        sec = Integer.parseInt(lblSec.getText());
        lblMin.setForeground(Color.BLACK);
        lblSec.setForeground(Color.BLACK);
    }
    private void lblMinPropertyChange(PropertyChangeEvent evt) {
    }
    private void btnStartActionPerformed(ActionEvent evt) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sec == 0 && min > 0) {
                    sec = 60;
                    min--;
                }
                if (min == 0 && sec > 0) {
                    lblMin.setForeground(Color.RED);
                    lblSec.setForeground(Color.RED);
                }
                if (min == 0 && sec == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Time up", "Stop", 0);
                    timer.stop();
                }
                if ((min >= 0 && sec > 0) || (min > 0 && sec >= 0)) {
                    sec--;
                    if (sec < 10) {
                        lblSec.setText("0" + sec);
                        flag = false;
                    }
                    if (min < 10) {
                        lblMin.setText("0" + min);
                        if (sec < 10)
                            lblSec.setText("0" + sec);
                        else
                            lblSec.setText("" + sec);
                        flag = false;
                    }
                    if (flag) {
                        lblMin.setText("" + min);
                        lblSec.setText("" + sec);
                    }
                    flag = true;
                }
            }
        });
        timer.start();
    }
    private void btnStopActionPerformed(ActionEvent evt) {
        timer.stop();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new JLabel();
        lblMin = new JLabel();
        lblSec = new JLabel();
        jLabel4 = new JLabel();
        cboxMin = new JComboBox<>();
        cboxSec = new JComboBox<>();
        btnStart = new JButton();
        btnStop = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 1, 28));
        jLabel1.setText("Timer");

        lblMin.setFont(new Font("Tahoma", 1, 28));
        lblMin.setText("00");
        lblMin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblMinPropertyChange(evt);
            }
        });
        lblSec.setFont(new Font("Tahoma", 1, 28));
        lblSec.setText("00");
        jLabel4.setFont(new Font("Tahoma", 1, 28));
        jLabel4.setText(":");
        cboxMin.setFont(new Font("Tahoma", 1, 28));
        cboxMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cboxMinActionPerformed(evt);
            }
        });
        cboxSec.setFont(new Font("Tahoma", 1, 28));
        cboxSec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cboxSecActionPerformed(evt);
            }
        });
        btnStart.setFont(new Font("Tahoma", 1, 28));
        btnStart.setText("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        btnStop.setFont(new Font("Tahoma", 1, 28));
        btnStop.setText("Stop");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(144, 144, 144)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblMin)
                                                        .addComponent(cboxMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addComponent(jLabel4)
                                                                .addGap(54, 54, 54)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(cboxSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblSec)))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 124,GroupLayout.PREFERRED_SIZE)
                                                .addGap(69, 69, 69)
                                                .addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 124,GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMin)
                                        .addComponent(lblSec)
                                        .addComponent(jLabel4))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboxSec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboxMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                .addGap(131, 131, 131)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnStart,GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }
}
