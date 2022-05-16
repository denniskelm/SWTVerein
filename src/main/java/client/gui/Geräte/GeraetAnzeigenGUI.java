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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        String pathToImage = "https://bilder.gartenpaul.de/item/images/456/full/456-R1-M1.jpg";
        insertImageToPanel(imageLabel, pathToImage);
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
