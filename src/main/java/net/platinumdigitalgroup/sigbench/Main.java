package net.platinumdigitalgroup.sigbench;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        primaryStage.setTitle("sigbench");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void submitBenchmark(Benchmark toBench) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("results.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("sigbench results");
            stage.initModality(Modality.APPLICATION_MODAL);
            loader.<ResultsController>getController().setResults(toBench.benchmark());
            stage.showAndWait();
        } catch (Exception e) {}
    }

}
