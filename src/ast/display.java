package ast;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Image;


public class display {

    public static void main(String[] args){

        try {
            
            //affichage du png de l'ast
            JFrame astframe = new JFrame("Arbre syntaxique CHAOS");
            JPanel astpanel = new JPanel();
            Image image = new ImageIcon("./out/tree.png").getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            double min = Math.min(1800/(double)width, 700/(double)height);
            int newWidth = (int)Math.round(width *min);
            int newHeight = (int)Math.round(height *min);

            // Scale l'image sous 2000x2000
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);
            JLabel label = new JLabel(icon);
            astpanel.add(label);
            astframe.add(astpanel);
            astframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            astframe.pack();
            astframe.setVisible(true);
            

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        

    }
    
}

