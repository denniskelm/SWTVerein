package client;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

//TODO Was macht diese Klasse?
public class DefaultsClient {


    //Nimmt einen frame und das dazugehörige Panel der Klasse und nimmt grundlegende Einstellungen vor
    public static JFrame standardizeFrame(JFrame frame, JPanel panel) {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public static String[] getKategorien(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public static void createColumnsFromArray(String[] columns, DefaultTableModel model) {
        for (String c :
                columns) {
            model.addColumn(c);
        }
    }

    public static void insertImageToPanel(JLabel imageLabel, String pathToImageURL) {
        try {
            URL url = new URL(pathToImageURL);
            BufferedImage image = ImageIO.read(url);
            Image resizedIMG = image.getScaledInstance(800, 450, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(resizedIMG));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    //Bei Klick: Entferne den Text "Eingeben", aber nur wenn noch nicht eingegeben wurde
    public static void enhanceTextField(JTextField textField, Map<JTextField, Boolean> onceChanged) {
        onceChanged.put(textField, false);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!onceChanged.get(textField)) {
                    textField.setText("");
                }

            }
        });
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                onceChanged.put(textField, true);
            }

            public void removeUpdate(DocumentEvent e) {
                onceChanged.put(textField, true);
            }

            public void insertUpdate(DocumentEvent e) {
                onceChanged.put(textField, true);
            }
        });
    }

    public static void enhanceTextArea(JTextArea textArea, Map<JTextArea, Boolean> onceChanged) {
        onceChanged.put(textArea, false);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!onceChanged.get(textArea)) {
                    textArea.setText("");
                }

            }
        });
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                onceChanged.put(textArea, true);
            }

            public void removeUpdate(DocumentEvent e) {
                onceChanged.put(textArea, true);
            }

            public void insertUpdate(DocumentEvent e) {
                onceChanged.put(textArea, true);
            }
        });
    }

    public static void addSearchFunctionality(JTable table, JTextField suchenTextField) {
        TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sort);

        suchenTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = suchenTextField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    //(?i) means case insensitive search
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = suchenTextField.getText();
                if (str.trim().length() == 0) {
                    sort.setRowFilter(null);
                } else {
                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

}
