package lib.com.carson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CordGenerator {

    public static void one() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Get X and Y coordinates");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().setLayout(new FlowLayout());
        BufferedImage im = ImageIO.read(new File("img/map.png"));
        frame.setSize(im.getWidth(),im.getHeight());
        frame.getContentPane().add(new JLabel(new ImageIcon(im)));


        final JTextField text = new JTextField();;
        frame.add(text,BorderLayout.SOUTH);

        frame.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                System.out.println(x + "," + y);
            }
        });

        frame.setVisible(true);
    }

    public static  <T extends Enum<T>> void two(Class<T> enumClass, String file) throws IOException{

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Get X and Y coordinates");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        frame.getContentPane().setLayout(new FlowLayout());
        BufferedImage im = ImageIO.read(getFile(file));
        frame.setSize(im.getWidth(),im.getHeight());
        frame.getContentPane().add(new JLabel(new ImageIcon(im)));


        final JTextField text = new JTextField();;
        frame.add(text,BorderLayout.SOUTH);
        final List<T> t = Arrays.asList(enumClass.getEnumConstants());
        System.out.println("starting with:" + t.get(0).toString());
        frame.addMouseListener(new MouseListener() {
            int index = 0;
            public void mousePressed(MouseEvent me) { }
            public void mouseReleased(MouseEvent me) { }
            public void mouseEntered(MouseEvent me) { }
            public void mouseExited(MouseEvent me) { }
            public void mouseClicked(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                System.out.println("stateCords.put(" + t.get(index) + ", new Pair<>(" + x + "," + y + "));");
                index++;
                System.out.println("//next:" + t.get(index));
                //stateCords.put(State.AZ, new Pair<>(363, 749));

            }
        });

        frame.setVisible(true);
    }


    public static File getFile(String str){
        return new File("img/cache/" + str + ".png");
    }


}
