package net.platinumdigitalgroup.sigbench;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author InvokeStatic
 */
public class ResultsController implements Initializable {
    public Label pctLabel;
    public ListView resultList;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setResults(BenchmarkResult results) {
        pctLabel.setText(results.score + "% (" + results.matched + "/" + results.signatures.size() + ")");
        for(SignatureResult result : results.signatures) {
            if(result.matched) {
                Label lbl = new Label("" + String.format("0x%06X -> 0x%06X", result.sourcePosition, result.matchedPosition) + result.toString());
                lbl.setFont(Font.font("monospace"));
                resultList.getItems().add(lbl);
            }
        }
    }
}
