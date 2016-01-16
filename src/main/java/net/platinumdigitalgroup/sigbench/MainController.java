package net.platinumdigitalgroup.sigbench;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.naming.ldap.Control;
import javax.swing.border.Border;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public BorderPane dropzone1;
    public BorderPane dropzone2;
    public HBox dropContainer;
    public Label sigsizeLabel;
    public Slider sigsizeSlider;
    public Button benchButton;

    /*
     * TODO: This class is not safe! Move dropzones to model for type safety
     */

    public void onDragDrop(DragEvent event, BorderPane pane) {
        Label label = (Label)pane.getChildrenUnmodifiable().get(1);
        Button clear = (Button)pane.getChildrenUnmodifiable().get(2);

        Dragboard db = event.getDragboard();
        if(db.hasFiles() && db.getFiles().size() == 1) {
            File f = db.getFiles().get(0);
            label.setText(f.getAbsolutePath());
            clear.setDisable(false);
            event.setDropCompleted(true);
        }
        event.consume();
    }

    public void onDragOver(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        } else {
            event.consume();
        }
    }

    public void onDragDropOne(DragEvent event) { onDragDrop(event, dropzone1); }
    public void onDragDropTwo(DragEvent event) { onDragDrop(event, dropzone2); }
    public void onDragOverOne(DragEvent event) { onDragOver(event); }
    public void onDragOverTwo(DragEvent event) { onDragOver(event); }

    public void onClearEx(ActionEvent actionEvent, Button clear) {
        Label label = (Label)clear.getParent().getChildrenUnmodifiable().get(1);

        label.setText("drop file here");
        clear.setDisable(true);
    }

    public void onClear(ActionEvent actionEvent) {
        onClearEx(actionEvent, (Button) actionEvent.getSource());
    }

    public void initialize(URL location, ResourceBundle resources) {
        sigsizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old, Number newVal) {
                sigsizeLabel.setText(Integer.toString(newVal.intValue()));
            }
        });
    }

    public void benchmark(ActionEvent actionEvent) {
        // this is very bad
        Label dz1 = (Label)dropzone1.getChildrenUnmodifiable().get(1);
        Label dz2 = (Label)dropzone2.getChildrenUnmodifiable().get(1);

        String p1 = dz1.getText();
        String p2 = dz2.getText();

        // this is VERY bad
        if(!p1.equals("drop file here") && !p2.equals("drop file here")) {
            Main.submitBenchmark(new Benchmark(new File(p1), new File(p2), (int)sigsizeSlider.getValue()));

        }
    }
}
