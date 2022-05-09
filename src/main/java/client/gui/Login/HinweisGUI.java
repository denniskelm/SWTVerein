package client.gui.Login;

import javax.swing.*;

public class HinweisGUI extends JFrame{
    private JPanel panel1;


    public HinweisGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new HinweisGUI("Hinweis");
        frame.setVisible(true);
    }
}
