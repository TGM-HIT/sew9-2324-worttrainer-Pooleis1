package tgm.ac.at.parnold;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Die Klasse erstellt ein GUI für die Eingabe eines Benutzers
 * Hier bekommt der Benutzer ein Bild und kann zu diesem das richtige Wort eingeben
 * Außerdem bekommt der Benutzer eine Statistik über die Versuche, sowie die richtigen und falschen Wörter
 * @author Pia Arnold
 * @version 23-10-28
 */
public class WordTrainerGUI extends JFrame {

    private JPanel panel, userInteraction, buttons;
    private JTextField tfield;
    private JLabel image;

    /**
     * Die Methoder erstellt ein GUI, womit der Benutzer zu einem Bild ein Wort raten kann und somit die Statistik über die Versuche, sowie richtigen und falschen Wörter bekommt
     * @param trainer hier wird das Backend an die GUI übergeben
     */
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
        image.setSize(300,150);
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
                JOptionPane.showMessageDialog(null, trainer.getTextStatistic());
                tfield.setText("");
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    trainer.save("saveTrainer.json");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

    }

    /**
     * Die Methode setzt ein Bild auf eine spezielle Größe passend fürs GUI
     * @param picture das gewünschte Bild, welches im GUI angezeigt werden soll wird an die Methode übergeben
     */
    public void setImage(String picture){
        //mby überarbeiten
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
