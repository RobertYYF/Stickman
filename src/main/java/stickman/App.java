package stickman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stickman.model.GameEngine;
import stickman.model.GameEngineImpl;
import stickman.view.GameWindow;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, String> params = getParameters().getNamed();

        String s = "Java 11 sanity check";
        if (s.isBlank()) {
            throw new IllegalStateException("You must be running Java 11+. You won't ever see this exception though" +
                    " as your code will fail to compile on Java 10 and below.");
        }

        GameEngine model = new GameEngineImpl(loadConfigurationFile());
        GameWindow window = new GameWindow(model, 640, 400);
        window.run();

        primaryStage.setTitle("Stickman");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }

    private JSONObject loadConfigurationFile() {
        JSONObject configuration = null;
        String config = "";
        JSONParser parser = new JSONParser();
        try {
            config = Files.readString(Paths.get("src/main/resources/config.json"));
            configuration = (JSONObject) parser.parse(config);
        } catch (ParseException e) {
            System.out.println("Invalid or corrupt configuration file.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
        System.out.println("Error reading configuration file.");
        System.out.println(e.getMessage());
    }

        return configuration;
    }
}
