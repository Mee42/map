package lib.com.carson;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GenericManipulator {
    protected static final int lineThickness = 3;
    public static void clearCache(){
        new File("img/cache/").mkdirs();
        for(File f : new File("img/cache/").listFiles()){
            f.delete();
        }
    }

    interface I{
        void run(BufferedImage im, Graphics2D g2, Color c);
    }

    private static BufferedImage cacheIm;
    private static Graphics2D cacheG2;


    public static final String CACHE = "cache";

    public static void run(Color c,String from,String to, I i) throws IOException {
        BufferedImage im;
        Graphics2D g2;

        if(!from.equals(CACHE)) {
            im = ImageIO.read(getFile(from));
            g2 = im.createGraphics();
        }else{
            im = cacheIm;
            g2 = cacheG2;
        }
        g2.setColor(c);
        i.run(im,g2,c);
        if(!to.equals(CACHE)) {
            ImageIO.write(im, "png", getFile(to));
        }else{
            cacheIm = im;
            cacheG2 = g2;
        }

    }

    public static void copy(String from, String to) throws IOException {
        if(from.equals(CACHE) || to.equals(CACHE))
            run(Color.BLACK,from,to,(c,im,g2) -> {});
        else
            Files.copy(getFile(from).toPath(),getFile(to).toPath());
    }

    public static void copyForce(String from,String to) throws IOException{
        if(getFile(to).exists())
            getFile(to).delete();
        copy(from,to);
    }


    public static File getFile(String str){
        return new File("img/cache/" + str + ".png");
    }


}
