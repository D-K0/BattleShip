import javafx.application.Application;
import javafx.stage.Stage;

public class SeaBatle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Welcome welcome=new Welcome();
    }

    public static void main(String[] args) {
        launch(args);
    }
}