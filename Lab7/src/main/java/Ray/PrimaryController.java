package Ray;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vbox;

    @FXML
    private SplitPane splitpane;

    @FXML
    private Button higher;

    @FXML
    private Button lower;

    @FXML
    private Button correct;

    @FXML
    private Button start_reset;

    @FXML
    private TextField min;

    @FXML
    private TextField max;

    @FXML
    private TextArea output;

    @FXML
    void initialize() {
        int MIN_VALUE = Integer.parseInt(min.getText());
        int MAX_VALUE = Integer.parseInt(max.getText());
        int ANSWER_VALUE = (MAX_VALUE-MIN_VALUE)/2;
        boolean correct = false;
        while(correct == false)
        {
            output.insertText(output.getLength()+1, "Answer:"+ ANSWER_VALUE+"?");
            
        }
    }
    @FXML
    boolean Correct(ActionEvent event) {
        return true;
    }

    @FXML
    void Higher(ActionEvent event) {
        
    }

    @FXML
    void Lower(ActionEvent event) {

    }

    @FXML
    void StartOrResetGame(ActionEvent event) {
    }
}
