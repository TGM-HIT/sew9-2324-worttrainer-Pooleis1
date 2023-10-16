package tgm.ac.at.parnold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class WordTrainerGUI extends JFrame {

    private JPanel panel, userInteraction, buttons;
    private JTextField tfield;
    private JLabel image;

    public WordTrainerGUI(WordspellTrainer trainer){
        super("Wordtrainer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setVisible(true);

        panel = new JPanel();
        userInteraction = new JPanel();
        buttons = new JPanel();

        tfield = new JTextField();
        image = new JLabel(new ImageIcon(""));
        JButton submit = new JButton("Abschicken");
        JButton exit = new JButton("Beenden");

        panel.setLayout(new BorderLayout());
        userInteraction.setLayout(new BoxLayout(userInteraction, BoxLayout.PAGE_AXIS));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));

        this.add(panel);
        panel.add(userInteraction, BorderLayout.SOUTH);
        panel.add(image, BorderLayout.CENTER);
        userInteraction.add(tfield);
        userInteraction.add(buttons);
        buttons.add(submit);
        buttons.add(exit);


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(trainer.check(tfield.getText()))
                    JOptionPane.showMessageDialog(null, "Richtig!");
                else
                    JOptionPane.showMessageDialog(null, "Falsch!");
                JOptionPane.showMessageDialog(null, trainer.getStatistic());
                tfield.setText("");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordTrainerSaveManager persistence = new JSONSaveManager();
                //persistence.save(trainer);
                System.exit(0);
            }
        });

    }

    public void setImage(String picture){
        try {
            ImageIcon imageIcon = new ImageIcon(new URL(picture));
            int orWidth = imageIcon.getIconWidth();
            int orHeight = imageIcon.getIconHeight();
            double widthRatio = (double) image.getWidth()/orWidth;
            double heightRatio = (double) image.getHeight()/orHeight;
            double scaleFactor = Math.min(widthRatio, heightRatio);

            int scaledWidth = (int) (imageIcon.getIconWidth() * scaleFactor);
            int scaledHeight = (int) (imageIcon.getIconHeight() * scaleFactor);

            image.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT)));

        } catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }

}
