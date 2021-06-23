import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;

public class PanelTest extends JPanel {
    private LocalDateTime startTime;
    private JLabel label;
    private Timer timer;
    public PanelTest(int min) {
        Duration duration = Duration.ofMinutes(min);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        label = new JLabel("Timer");
        label.setFont(new Font("", Font.PLAIN, 100));
        label.setForeground(Color.blue);
        gbc.insets = new Insets(200,200,200,200);
        add(label, gbc);
        JButton btn = new JButton("Start");
        btn.setBounds(90,90,40,100);//chi ashxatum
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                    startTime = null;
                    btn.setText("Start");
                } else {
                    startTime = LocalDateTime.now();
                    timer.start();
                    btn.setText("Stop");
                }
            }
        });
        add(btn, gbc);
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                Duration runningTime = Duration.between(startTime, now);
                Duration timeLeft = duration.minus(runningTime);
                if (timeLeft.isZero() || timeLeft.isNegative()) {
                    timeLeft = Duration.ZERO;
                    btn.doClick();
                }
                label.setText(format(timeLeft));
            }
        });
    }
    public String format(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusMinutes(minutes).toMillis() /1000;
        return String.format("%02dh %02dm %02ds", hours, minutes, seconds);
    }
}
