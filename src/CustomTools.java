import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class CustomTools {
    public static JLabel loadImage(String resource){
        BufferedImage image;
        try{
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource); // This loads a resource (typically a file) located relative to the CustomTools class.
            image = ImageIO.read(inputStream); // reads the input stream and converts it into a BufferedImage
            return new JLabel(new ImageIcon(image)); // If the image is successfully read, a new JLabel is created with the image set as its icon (new JLabel(new ImageIcon(image))) and returned
        } catch (Exception e){
            System.out.println("Error loading image: " + resource);
        }
        return null; //  If an exception is caught, the method returns null.
    }
    public static Font createFont(String resource){
        // get the font file path
        String filePath = CustomTools.class.getClassLoader().getResource(resource).getPath();

        // check to see if the path contains a folder with spaces in them
        if(filePath.contains("%20")){
            filePath = CustomTools.class.getClassLoader().getResource(resource).getPath()
                    .replaceAll("%20", " ");
        }

        // create font
        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile);
            return customFont;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
}