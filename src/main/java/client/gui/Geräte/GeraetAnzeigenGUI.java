package client.gui.Geräte;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
TODO Dennis Kelm
*/

import client.DefaultsClient;
import org.intellij.lang.annotations.Flow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//TODO Was macht diese Klasse?
public class GeraetAnzeigenGUI {
    private JPanel geraetAnzeigenGUI;
    private JLabel headlineLabel;
    private JPanel metaOuterPanel;
    private JPanel metaLeftPanel;
    private JPanel metaRightPanel;
    private JLabel metaInfoText1;
    private JLabel metaInfoText2;
    private JLabel metaInfoText3;
    private JLabel metaInfoText4;
    private JPanel imageOuterPanel;
    private JPanel imageHeadlinePanel;
    private JPanel displayImagePanel;
    private JLabel imageHeadlineText;
    private JLabel descriptionText;
    private JButton jetztReservierenButton;
    private JLabel imageLabel;


    public GeraetAnzeigenGUI() {
        JFrame frame = new JFrame("Gerätedetails");
        insertImageToPanel(imageLabel, "https://pixabay.com/get/g0be4ab385c9f7060b89ca00db37e4a2ff0903aa13857046e68c8a617bdac13ded0460d8096e1d0064ffb4af1de9ea83add2c57e86d4c362931816e1a9fa8f5b1ec50d3f4a3ff402e3078b1f88972909a_1920.jpg");
        frame = DefaultsClient.standardizeFrame(frame, geraetAnzeigenGUI);
    }

    public void insertImageToPanel(JLabel imageLabel, String pathToImageURL) {
        try {
            URL url = new URL(pathToImageURL);
            BufferedImage image = ImageIO.read(url);
            Image resizedIMG = image.getScaledInstance(800, 450, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(resizedIMG));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void createUIComponents() {

    }
}
