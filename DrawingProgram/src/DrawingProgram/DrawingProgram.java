/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawingProgram;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**9
 *7
 * @author Kasper
 */
public class DrawingProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        


        //Photograph with blurry background: https://udemy-images.udemy.com/course/750x422/394968_538b_7.jpg
        //Test paint picture: https://i.imgur.com/fqoaeEW.png
        ImageManipulator imageManipulator = new ImageManipulator("https://vintage.ponychan.net/chan/files/src/131999656437.png");
        
        System.out.println(imageManipulator.getCommandString());
        System.out.println(imageManipulator.getCounter());
        
        //Connecting to PLC if PLC is setup
//        RobotClient client = new RobotClient("localhost", 12345);
//        client.connect();
//        client.writeString(imageManipulator.getCommandString());

        JFrame pictureFrame = new JFrame();
        JFrame lineFrame = new JFrame();
        
        ImageIcon image = new ImageIcon(imageManipulator.getEdge().getBufferedImage());
        JLabel imageLabel = new JLabel(image);
        
        PaneImage p = new PaneImage(400, 400);
        lineFrame.setSize(400, 400);
        lineFrame.add(p);
        
        //Setting size on pictureFrame
        pictureFrame.setSize(400, 400);
        pictureFrame.add(imageLabel);
        

        //Drawing from commandGenerator
        for(Drawing d : imageManipulator.getDrawings()){
            p.addDrawing(d);
        }

        
        //Set all frames to visible
        pictureFrame.setVisible(true);
        lineFrame.setVisible(true);


    }

}
