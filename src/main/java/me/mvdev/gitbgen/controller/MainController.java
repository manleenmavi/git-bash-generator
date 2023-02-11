package me.mvdev.gitbgen.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import me.mvdev.gitbgen.model.FlagData;
import me.mvdev.gitbgen.model.VariableData;
import me.mvdev.gitbgen.service.BashCommandManager;

import java.util.ArrayList;

/**
 * FXML Controller class, for controlling the main view.
 * All actions are handled here.
 *
 * @author manleenmavi
 * @version 0.0.1.20230209
 */
public class MainController {

    @FXML
    private GridPane mainCenteredPane;

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
    private VBox variableListPane;
    @FXML
    private GridPane variablesRootPane;

    /*
     *  Command Pane
     */
    @FXML
    private TextField commandSyntaxInput;


    /*
     *  Flags Pane
     */
    @FXML
    private GridPane flagsRootPane;
    @FXML
    private TextField flagNameInput;
    @FXML
    private TextField flagValueInput;
    @FXML
    private ImageView flagAddBtn;
    @FXML
    private VBox flagDropDownPane;
    @FXML
    private VBox flagListPane;
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
    private TextField generatedOutputText;
    @FXML
    private ImageView generatedOutputCopy;

    /*
     *  List Nodes - HBox
     */
//    private ArrayList<HBox> variableListNodes;
//    private ArrayList<HBox> flagListNodes;
    private FXMLLoader listNodesFxmlLoader;

    /*
     *  Non-FXML
     */
    private final String variableListNodesFxmlPath = "/me/mvdev/gitbgen/variable-flag-list-node.fxml";
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

        /* Variable Pane */
        //Removing the dropdown panes
        variablesRootPane.getRowConstraints().remove(1);
        variablesRootPane.getChildren().remove(1);

        //Disabling the add button
        variableAddBtn.setDisable(true);
        variableAddBtn.setOpacity(0.5);

        //Initializing the variable list nodes
//        variableListNodes = new ArrayList<>();

        /* Flag Pane */
        //Removing the dropdown panes
        flagsRootPane.getRowConstraints().remove(1);
        flagsRootPane.getChildren().remove(1);

        //Disabling the add button
        flagAddBtn.setDisable(true);
        flagAddBtn.setOpacity(0.5);

        //Adding focus property to syntax input, if unfocused, then add the command to the bash command manager
        commandSyntaxInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                bashCommandManager.setSyntax(commandSyntaxInput.getText());
            }
        });


    }

    @FXML
    private void handleVariableDropDown(MouseEvent event) {
        if (isVariableDropDownOpen) {
            variablesRootPane.getRowConstraints().remove(1);
            variablesRootPane.getChildren().remove(1);
            dropDownUpIcon.setRotate(0);
            isVariableDropDownOpen = false;
        } else {
            variablesRootPane.getRowConstraints().add(1, new javafx.scene.layout.RowConstraints(Region.USE_COMPUTED_SIZE));
            variablesRootPane.getChildren().add(1, varialbeDropDownPane);
            dropDownUpIcon.setRotate(180);
            isVariableDropDownOpen = true;
        }
    }

    @FXML
    public void handleVariableNameField(KeyEvent event) {
        String variableName = variableNameField.getText();
        if (variableName.length() > 0) {
            variableAddBtn.setDisable(false);
            variableAddBtn.setOpacity(1);
        } else {
            variableAddBtn.setDisable(true);
            variableAddBtn.setOpacity(0.5);
        }
    }

    @FXML
    private void handleVariableAddBtn(MouseEvent event) {
        String variableName = variableNameField.getText();
        String variableValue = variableValueField.getText();

        //Adding to the bash command manager
        newVariableData = new VariableData(variableName, variableValue);
        bashCommandManager.addVariable(newVariableData);

        //Adding to the list
        try {
            //Fxml load list nodes
            listNodesFxmlLoader = new FXMLLoader(getClass().getResource(variableListNodesFxmlPath));
            HBox variableListNode = listNodesFxmlLoader.load();
            ObservableList<Node> children = variableListNode.getChildren();

            //Name
            TextField textF = (TextField) ((HBox) children.get(0)).getChildren().get(0);
            textF.setText(variableName);

            //Value
            TextField textF2 = (TextField) ((HBox) children.get(1)).getChildren().get(0);
            textF2.setText(variableValue);

            //Removing from pane list and from bash command manager
            ImageView imageView = (ImageView) ((HBox) children.get(2)).getChildren().get(0);
            imageView.setOnMouseClicked(event1 -> {
                //Getting the index of the node
                int index = variableListPane.getChildren().indexOf(variableListNode);
                variableListPane.getChildren().remove(variableListNode);
                bashCommandManager.removeVariable(index);
                updateVariableCount();
            });

            //Add to list
            variableListPane.getChildren().add(variableListNode);

            //Clearing the fields
            variableNameField.clear();
            variableValueField.clear();

            //Disabling the add button
            variableAddBtn.setDisable(true);
            variableAddBtn.setOpacity(0.5);

            //Changing the count using bash command manager
            updateVariableCount();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleFlagDropDown(MouseEvent event) {
        if (isFlagDropDownOpen) {
            flagsRootPane.getRowConstraints().remove(1);
            flagsRootPane.getChildren().remove(1);
            flagDropDownUpIcon.setRotate(0);
            isFlagDropDownOpen = false;
        } else {
            flagsRootPane.getRowConstraints().add(1, new javafx.scene.layout.RowConstraints(Region.USE_COMPUTED_SIZE));
            flagsRootPane.getChildren().add(1, flagDropDownPane);
            flagDropDownUpIcon.setRotate(180);
            isFlagDropDownOpen = true;
        }
    }

    @FXML
    private void handleFlagNameField(KeyEvent event) {
        String flagName = flagNameInput.getText();
        if (flagName.length() > 0) {
            flagAddBtn.setDisable(false);
            flagAddBtn.setOpacity(1);
        } else {
            flagAddBtn.setDisable(true);
            flagAddBtn.setOpacity(0.5);
        }
    }

    @FXML
    private void handleFlagAddBtn(MouseEvent event) {
        String flagName = flagNameInput.getText();
        String flagValue = flagValueInput.getText();

        //Adding to the bash command manager
        newFlagData = new FlagData(flagName, flagValue);
        bashCommandManager.addFlag(newFlagData);

        //Adding to the list
        try {
            //Fxml load list nodes
            listNodesFxmlLoader = new FXMLLoader(getClass().getResource(variableListNodesFxmlPath));
            HBox flagListNode = listNodesFxmlLoader.load();
            ObservableList<Node> children = flagListNode.getChildren();

            //Name
            TextField textF = (TextField) ((HBox) children.get(0)).getChildren().get(0);
            textF.setText(flagName);

            //Value
            TextField textF2 = (TextField) ((HBox) children.get(1)).getChildren().get(0);
            textF2.setText(flagValue);

            //Removing from pane list and from bash command manager
            ImageView imageView = (ImageView) ((HBox) children.get(2)).getChildren().get(0);
            imageView.setOnMouseClicked(event1 -> {
                //Getting the index of the node
                int index = flagListPane.getChildren().indexOf(flagListNode);
                flagListPane.getChildren().remove(flagListNode);
                bashCommandManager.removeFlag(index);
                updateFlagCount();
            });

            //Add to list
            flagListPane.getChildren().add(flagListNode);

            //Clearing the fields
            flagNameInput.clear();
            flagValueInput.clear();

            //Disabling the add button
            flagAddBtn.setDisable(true);
            flagAddBtn.setOpacity(0.5);

            //Changing the count using bash command manager
            updateFlagCount();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void handleGenerateBashEvent(MouseEvent event) {
        generatedOutputText.setText(bashCommandManager.getBashCommand());
    }

    @FXML
    private void handleCopyToClipboardEvent(MouseEvent event) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(bashCommandManager.getBashCommand());
        clipboard.setContent(content);
    }


    /**
     * Updates the variable count
     */
    private void updateVariableCount() {
        variableCount.setText(bashCommandManager.getVariables().size() + "");
    }

    /**
     * Updates the flag count
     */
    private void updateFlagCount() {
        flagCount.setText(bashCommandManager.getFlags().size() + "");
    }



}