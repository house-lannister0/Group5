package com.oopclass.breadapp.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.oopclass.breadapp.config.StageManager;
import com.oopclass.breadapp.models.Cancel;
import com.oopclass.breadapp.services.impl.CancelService;
import com.oopclass.breadapp.views.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class CancelController implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField productName;

    @FXML
    private Label cancelId;

    @FXML
    private DatePicker doc;

    @FXML
    private RadioButton rbYes;

    @FXML
    private RadioButton rbNo;

    @FXML
    private ToggleGroup cancel;

    @FXML
    private Button reset;

    @FXML
    private Button saveCancel;

    @FXML
    private Button deleteCancel;

    @FXML
    private Button back;

    @FXML
    private Button backHome;

    @FXML
    private TableView<Cancel> cancelTable;

    @FXML
    private TableColumn<Cancel, Long> colCancelId;

    @FXML
    private TableColumn<Cancel, String> colFirstName;

    @FXML
    private TableColumn<Cancel, String> colLastName;

    @FXML
    private TableColumn<Cancel, String> colProductName;

    @FXML
    private TableColumn<Cancel, LocalDate> colDOC;

    @FXML
    private TableColumn<Cancel, String> colCancelOrder;

    @FXML
    private TableColumn<Cancel, Boolean> colEdit;
    @Lazy
    @Autowired
    private StageManager stageManager;

    @Autowired
    private CancelService cancelService;

    private ObservableList<Cancel> cancelList = FXCollections.observableArrayList();

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }
  
    @FXML
    void back (ActionEvent event){
        stageManager.switchScene(FxmlView.PRODUCT);
    }
      
    @FXML
    void backHome (ActionEvent event){
        stageManager.switchScene(FxmlView.HOME);
    }
    @FXML
    void saveCancel(ActionEvent event) {

        if (validate("First Name", getFirstName(), "([a-zA-Z]{3,30}\\s*)+")
                && validate("Lastname", getLastName(), "([a-zA-Z]{3,30}\\s*)+")
                && emptyValidation("DOC", doc.getEditor().getText().isEmpty()));
        {

            if (cancelId.getText() == null || "".equals(cancelId.getText())) {
                if (true) {

                    Cancel cancel = new Cancel();
                    cancel.setFirstName(getFirstName());
                    cancel.setLastName(getLastName());
                    cancel.setProductName(getProductName());
                    cancel.setDoc(getDoc());
                    cancel.setCancel(getCancel());
                    Cancel newCancel = cancelService.save(cancel);
                    saveAlert(newCancel);
                }

            } else {
                Cancel cancel = cancelService.find(Long.parseLong(cancelId.getText()));
                cancel.setFirstName(getFirstName());
                cancel.setLastName(getLastName());
                cancel.setProductName(getProductName());
                cancel.setDoc(getDoc());
                cancel.setCancel(getCancel());
                Cancel updatedCancel = cancelService.update(cancel);
                updateAlert(updatedCancel);
            }

            clearFields();
            loadCancelDetails();
        }

    }

    @FXML
    void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void deleteCancel(ActionEvent event) {
        List<Cancel> cancels = (List<Cancel>) cancelTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            cancelService.deleteInBatch(cancels);
        }

        loadCancelDetails();
    }

    private void clearFields() {
        cancelId.setText(null);
        firstName.clear();
        lastName.clear();
        productName.clear();
        doc.getEditor().clear();
        rbYes.setSelected(true);
        rbNo.setSelected(false);
    }

    private void saveAlert(Cancel cancel) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cancel saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The customer " + cancel.getFirstName() + " " + cancel.getLastName() + " has been created and \n" + cancel.getProductName() + " has been updated. " + " Date of cancellation is " + cancel.getDoc() + " " + " id is " + cancel.getId() + ".");
        alert.showAndWait();
    }

    private void updateAlert(Cancel cancel) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cancel updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The cancel " + cancel.getFirstName() + " " + cancel.getLastName() + " has been updated.");
        alert.showAndWait();

    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getProductName() {
        return productName.getText();
    }

    public LocalDate getDoc() {
        return doc.getValue();

    }

    public String getCancel() {
        return rbYes.isSelected() ? "Yes" : "No";
    }

    /*
	 *  Set All cancelTable column properties
     */
    private void setColumnProperties() {
        /* Override date format in table
		 * colDOC.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
			 String pattern = "dd/MM/yyyy";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		     @Override 
		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     @Override 
		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 }));*/

        colCancelId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colDOC.setCellValueFactory(new PropertyValueFactory<>("doc"));
        colCancelOrder.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        colEdit.setCellFactory(cellFactory);

    }

    Callback<TableColumn<Cancel, Boolean>, TableCell<Cancel, Boolean>> cellFactory
            = new Callback<TableColumn<Cancel, Boolean>, TableCell<Cancel, Boolean>>() {
        @Override
        public TableCell<Cancel, Boolean> call(final TableColumn<Cancel, Boolean> param) {
            final TableCell<Cancel, Boolean> cell = new TableCell<Cancel, Boolean>() {
                Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
                final Button btnEdit = new Button();

                @Override
                public void updateItem(Boolean check, boolean empty) {
                    super.updateItem(check, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnEdit.setOnAction(e -> {
                            Cancel cancel = getTableView().getItems().get(getIndex());
                            updateCancel(cancel);
                        });

                        btnEdit.setStyle("-fx-background-color: transparent;");
                        ImageView iv = new ImageView();
                        iv.setImage(imgEdit);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        btnEdit.setGraphic(iv);

                        setGraphic(btnEdit);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }

                private void updateCancel(Cancel cancel) {
                    cancelId.setText(Long.toString(cancel.getId()));
                    firstName.setText(cancel.getFirstName());
                    lastName.setText(cancel.getLastName());
                    productName.setText(cancel.getProductName());
                    doc.setValue(cancel.getDoc());
                    if (cancel.getCancel().equals("Yes")) {
                        rbYes.setSelected(true);
                    } else {
                        rbNo.setSelected(true);
                    }
                }
            };
            return cell;
        }
    };

    /*
	 *  Add All cancels to observable list and update table
     */
    private void loadCancelDetails() {
        cancelList.clear();
        cancelList.addAll(cancelService.findAll());

        cancelTable.setItems(cancelList);
    }

    /*
	 * Validations
     */
    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) {
            alert.setContentText("Please Select " + field);
        } else {
            if (empty) {
                alert.setContentText("Please Enter " + field);
            } else {
                alert.setContentText("Please Enter Valid " + field);
            }
        }
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cancelTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        setColumnProperties();

        //Add all cancels into table
        loadCancelDetails();
    }

}
