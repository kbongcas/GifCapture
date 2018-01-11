import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ScreenCaptureDevice - captures a screenshot continuously using a Robot.
 * @author kbongcas
 */
public class ScreenCaptureDevice {

    private final int MAX_CAPTURE_TIME = 20000;
    private final int SLEEP_TIME = 0100; // fix this somehow

    private List<BufferedImage> frames;
    private Robot robot;
    private Rectangle areaToCapture;

    /**
     * Constructs the robot that will capture an image and
     * the area where the robot will capture images from.
     * @param areaToCapture the rectangle area to capture.
     */
    public ScreenCaptureDevice( Rectangle areaToCapture ) {
        try {
            this.robot = new Robot();
            this.areaToCapture = areaToCapture;
        }catch( AWTException e ){
            System.err.println("Robot could not be created.");
        }
    }

    /**
     * Creates a list of frames captured by the robot within
     * a given time frame.
     * @param millisToRecord how long the robot will capture screenshots
     * @return Array List of frames
     */
    public List<BufferedImage> capture( int millisToRecord ) {


        /**
         * While loop will not be accurate if code inside takes too much time.
         * @TODO implement ExecutorService.
         */
        try {
            frames = new ArrayList<BufferedImage>();
            BufferedImage frame;
            if (millisToRecord > MAX_CAPTURE_TIME && millisToRecord <= 0) {
                System.err.println("Invalid time"); 
            } else {
                long startTime = System.currentTimeMillis();
                while ((System.currentTimeMillis() - startTime) < millisToRecord) {
                    frame = robot.createScreenCapture(this.areaToCapture);
                    this.frames.add(frame);
                    Thread.sleep(SLEEP_TIME);
                }
            }
        }catch( InterruptedException e ) {
            e.printStackTrace();
        }
        return this.frames;
    }
} 
