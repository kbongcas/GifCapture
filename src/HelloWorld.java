import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class HelloWorld extends Application {

    Button button;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Title of the Window");
        button = new Button();
        button.setText("The button name");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);

        //listens to window size // use lambda notation
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) ->
                System.out.println("Width: " + newSceneWidth));
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) ->
                System.out.println("Height: " + newSceneHeight));
        primaryStage.xProperty().addListener((observableValue, oldSceneX, newSceneX) ->
                System.out.println("X: " + newSceneX));
        primaryStage.yProperty().addListener((observableValue, oldSceneY, newSceneY) ->
                System.out.println("Y: " + newSceneY));
        primaryStage.show();

    }
}