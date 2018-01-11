import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GifMaker {

    private File outputFile;
    private ImageWriter iw;
    private ImageOutputStream ios;



    GifMaker(String filename) {
        //long stopTime = System.nanoTime();
        //System.out.println(stopTime - startTime);

        this.outputFile = new File(filename);
        this.iw = ImageIO.getImageWritersByFormatName("gif").next();
    }

    public void prepareToWrite() throws IOException {
        this.ios = new FileImageOutputStream(this.outputFile);
        iw.setOutput(ios);
        iw.prepareWriteSequence(null);
    }

    public File writeGif( List<BufferedImage> abi) throws IOException {
        for (BufferedImage bfi : abi){

            BufferedImage src = bfi;
            ImageWriteParam iwp = iw.getDefaultWriteParam();
            IIOMetadata metadata = iw.getDefaultImageMetadata(
                    new ImageTypeSpecifier(src), iwp);

            //configure(metadata, "" + animationFrame.getDelay(), i);

            IIOImage ii = new IIOImage(src, null, metadata);
            iw.writeToSequence(ii, null);
            //i++;
        }

        iw.endWriteSequence();
        ios.close();
        return  this.outputFile;
    }
}
