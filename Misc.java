import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import javax.imageio.ImageWriter;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.IIOImage;
import javax.imageio.stream.FileImageOutputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ScreenCapture{
    
    public static void main (String[] args) throws InterruptedException {

        try {

            ArrayList<BufferedImage> abi = new ArrayList<BufferedImage>(); 
			Robot robot = new Robot();
			Rectangle rectangle = new Rectangle(500,500,500,500);
            BufferedImage bufferedImage = null;
            long startTime = System.currentTimeMillis(); //fetch starting time
            while(false||(System.currentTimeMillis()-startTime)<10000){
				bufferedImage = robot.createScreenCapture(rectangle);
                abi.add(bufferedImage);
				//String fileName = "screen-capture" + i + ".png";
				//File file = new File(fileName);
				//boolean status = ImageIO.write(bufferedImage, "png", file);
				System.out.println("print");
                Thread.sleep(0100);
        	}

        	long stopTime = System.nanoTime();
        	System.out.println(stopTime - startTime);

            File outputFile = new File("ouputfile.gif");
            ImageWriter iw = ImageIO.getImageWritersByFormatName("gif").next();
            ImageOutputStream ios = new FileImageOutputStream(outputFile);
            iw.setOutput(ios);
            iw.prepareWriteSequence(null);
            int i = 0;

            for (BufferedImage bfi : abi){

                BufferedImage src = bfi;
                ImageWriteParam iwp = iw.getDefaultWriteParam();
                IIOMetadata metadata = iw.getDefaultImageMetadata(
                    new ImageTypeSpecifier(src), iwp);

                //configure(metadata, "" + animationFrame.getDelay(), i);

                IIOImage ii = new IIOImage(src, null, metadata);
                iw.writeToSequence(ii, null);
                i++;
            }

            iw.endWriteSequence();
            ios.close();
         
        } catch (AWTException | IOException ex) {
                System.err.println(ex);
        }
    }
}
