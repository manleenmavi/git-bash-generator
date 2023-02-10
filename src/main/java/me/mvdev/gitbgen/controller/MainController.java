package me.mvdev.gitbgen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import me.mvdev.gitbgen.model.FlagData;
import me.mvdev.gitbgen.model.VariableData;
import me.mvdev.gitbgen.service.BashCommandManager;

/**
 * FXML Controller class, for controlling the main view.
 * All actions are handled here.
 *
 * @author manleenmavi
 * @version 0.0.1.20230209
 */
public class MainController {

    /*
     *  Variable Pane
     */
    @FXML
    private Text variableCount;
    @FXML
    private ImageView dropDownUpIcon;
    @FXML
    private TextField variableNameField;
    @FXML
    private TextField variableValueField;
    @FXML
    private ImageView variableCalendarBtn;
    @FXML
    private ImageView variableAddBtn;
    @FXML
    private VBox varialbeDropDownPane;
    @FXML
    private HBox variableListPane;

    /*
     *  Command Pane
     */
    @FXML
    private TextField commandSyntaxInput;


    /*
     *  Flags Pane
     */
    @FXML
    private TextField flagNameInput;
    @FXML
    private TextField flagValueInput;
    @FXML
    private ImageView flagAddBtn;
    @FXML
    private VBox flagDropDownPane;
    @FXML
    private HBox flagListPane;
    @FXML
    private Text flagCount;
    @FXML
    private ImageView flagDropDownUpIcon;
    @FXML
    private ImageView flagCalendarBtn;

    /*
     *  Generate Pane
     */
    @FXML
    private Button generateButton;
    @FXML
    private HBox generatedCodePane;
    @FXML
    private TextFlow generatedOutputText;
    @FXML
    private ImageView generatedOutputCopy;

    /*
     *  Non-FXML
     */
    private boolean isVariableDropDownOpen;
    private boolean isFlagDropDownOpen;
    private BashCommandManager bashCommandManager;
    private FlagData newFlagData;
    private VariableData newVariableData;

    public MainController() {
        bashCommandManager = new BashCommandManager();
        newFlagData = new FlagData();
        newVariableData = new VariableData();
    }


    @FXML
    private void initialize() {
    }

    @FXML
    private void handleVariableAddBtn( MouseEvent event ) {
        variableValueField.setText("1");
    }


}