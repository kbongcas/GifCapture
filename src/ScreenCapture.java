import java.awt.*;
import java.io.IOException;

public class ScreenCapture {
    public static void main(String[] args) throws InterruptedException, IOException {

        Rectangle rectangle = new Rectangle(500,500,500,500);
        ScreenCaptureDevice scd = new ScreenCaptureDevice(rectangle);
        scd.capture(5000);
        GifMaker gm = new GifMaker("output.gif");
        gm.prepareToWrite();
        gm.writeGif(scd.capture(5000));

    }
}
