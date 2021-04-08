package Ray;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
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
    private TextArea output;

    @FXML
    private Spinner<Integer> min;

    @FXML
    private Spinner<Integer> max;

    @FXML
    private RadioButton style1;

    @FXML
    private ToggleGroup styling;

    @FXML
    private RadioButton style2;

    @FXML
    private RadioButton style3;

    int minvalue;
    int maxvalue;
    int estimatednumber;
    Guesser guesser;

    @FXML
    void Correct(ActionEvent event) 
    {
        output.appendText("Я угадал! Вы загадали ... " + Integer.toString(estimatednumber));
        output.appendText(System.lineSeparator());
        output.appendText("Хотите сыграть ещё раз? Нажмите Start/Reset.");
        output.appendText(System.lineSeparator());
    }

    @FXML
    void Higher(ActionEvent event) 
    {
        if(minvalue == maxvalue)
        {
            output.appendText("Ты жульничаешь, я угадал число");
            output.appendText(System.lineSeparator());
            output.appendText("Хотите сыграть ещё раз? Нажмите Start/Reset.");
            output.appendText(System.lineSeparator());
        }
        else
        {
            minvalue = estimatednumber + 1;
            guesser.setMinNumb(minvalue);
            guess();
        }
    }

    @FXML
    void Lower(ActionEvent event) 
    {
        if(minvalue == maxvalue)
        {
            output.appendText("Ты жульничаешь, я угадал число");
            output.appendText(System.lineSeparator());
            output.appendText("Хотите сыграть ещё раз? Нажмите Start/Reset.");
            output.appendText(System.lineSeparator());
        }
        else
        {
            maxvalue = estimatednumber - 1;
            guesser.setMaxNumb(maxvalue);
            guess();
        }
    }

    @FXML
    void StartOrResetGame(ActionEvent event) 
    {
        output.clear();
        try{
        createguesser();
        guess();
        }
        catch(IllegalArgumentException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void createguesser()
    {
        minvalue = (int) min.getValue();
        maxvalue = (int) max.getValue();
        guesser = new Guesser(minvalue, maxvalue);
    }

    private void guess()
    {
        estimatednumber = guesser.guess();
        output.appendText("Я думаю, что ....");
        output.appendText(Integer.toString(estimatednumber));
        output.appendText(System.lineSeparator());
    }

    @FXML
    void changestyleto1(ActionEvent event) 
    {
        vbox.getStylesheets().clear();
        vbox.getStylesheets().add("MistSilverSkin.css");
    }

    @FXML
    void changetostyle2(ActionEvent event) 
    {
        vbox.getStylesheets().clear();
        vbox.getStylesheets().add("Style.css");
    }

    @FXML
    void changetostyle3(ActionEvent event) 
    {
        vbox.getStylesheets().clear();
        vbox.getStylesheets().add("bootstrap3.css");
    }

    @FXML
    void initialize() 
    {
        vbox.getStylesheets().add("MistSilverSkin.css");
        style1.setText("Mist Silver");
        style2.setText("Dark Theme");
        style3.setText("BootStrap 3");
    }
}
