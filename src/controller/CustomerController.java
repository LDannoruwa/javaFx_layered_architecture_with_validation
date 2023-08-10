package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import service.ServiceFactory;
import service.custom.CustomerService;
import util.Validator;
import view.tableModule.CustomerTM;

public class CustomerController {

    @FXML
    private TextField txt_customerID;

    @FXML
    private TextField txt_customerName;

    @FXML
    private TextField txt_customerAddress;

    @FXML
    private TextField txt_customerSalary;

    // Table view
    @FXML
    private TableColumn<CustomerTM, String> table_col_cusAddress;

    @FXML
    private TableColumn<CustomerTM, Integer> table_col_cusID;

    @FXML
    private TableColumn<CustomerTM, String> table_col_cusName;

    @FXML
    private TableColumn<CustomerTM, Double> table_col_cusSalary;

    @FXML
    private TableColumn<CustomerTM, Button> table_col_cusDeleteButton;

    @FXML
    private TableView<CustomerTM> table_customerData;

    CustomerService customerService;

    public void initialize() throws Exception {

        customerService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);

        table_col_cusID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        table_col_cusName.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        table_col_cusAddress.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        table_col_cusSalary.setCellValueFactory(new PropertyValueFactory<>("customer_Salary"));
        table_col_cusDeleteButton.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        getCustomerDetails();
    }

    // loading using click event
    public void getCustomerDetails() throws Exception {
        try {
            ArrayList<CustomerDto> allCustomer = customerService.getAllCustomer();
            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            for (CustomerDto customer : allCustomer) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
                btnDelete.setTextFill(Color.web("#ecf0f1"));
                CustomerTM customerTM = new CustomerTM(customer.getCustomer_id(), customer.getCustomer_name(),
                        customer.getCustomer_address(),
                        customer.getCustomer_Salary(), btnDelete);
                tmList.add(customerTM);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();
                    try {
                        if (result.orElse(no) == ok) {
                            if (customerService.deleteCustomer(customerTM.getCustomer_id())) {
                                new Alert(AlertType.CONFIRMATION, "Customer is deleted.!").show();
                                getCustomerDetails();
                                return;
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });
            }
            table_customerData.setItems(tmList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        try {
            // get inserted value from text fields in CustomerView.fxml
            int customerID = Integer.parseInt(txt_customerID.getText());
            String customerName = txt_customerName.getText();
            String customerAddress = txt_customerAddress.getText();
            double customerSalary = Double.parseDouble(txt_customerSalary.getText());

            // create an new CustomerDto type object
            CustomerDto customerDto = new CustomerDto(customerID, customerName, customerAddress, customerSalary);

            boolean isSaved = customerService.saveCustomer(customerDto);

            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Customer is saved.!").show();
                // load all customers after save new one
                getCustomerDetails();
            } else {
                new Alert(AlertType.ERROR, "Customer is not saved..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void searchCustomer() {
        try {
            int id = Integer.parseInt(txt_customerID.getText());
            CustomerDto customerDto = customerService.getCustomer(id);

            if (customerDto != null) {
                txt_customerName.setText(customerDto.getCustomer_name());
                txt_customerAddress.setText(customerDto.getCustomer_address());
                txt_customerSalary.setText(String.valueOf(customerDto.getCustomer_Salary()));
            } else {
                new Alert(AlertType.ERROR, "Customer Not Found. Please check the customer id and try again.!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            // get inserted data from the text fields
            int customerID = Integer.parseInt(txt_customerID.getText());
            String customerName = txt_customerName.getText();
            String customerAddress = txt_customerAddress.getText();
            double customerSalary = Double.parseDouble(txt_customerSalary.getText());

            CustomerDto customerDto = new CustomerDto(customerID, customerName, customerAddress, customerSalary);

            boolean isUpdated = customerService.updateCustomer(customerDto);

            if (isUpdated) {
                new Alert(AlertType.CONFIRMATION, "Customer is updated.!").show();
                // load all customers after save new one
                getCustomerDetails();
            } else {
                new Alert(AlertType.ERROR, "Customer is not updated..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //validation------------------
    @FXML
    void txtCustomerIDOnKeyReleased(KeyEvent event) {
        if (Validator.validateTextField(txt_customerID, "^[0-9]{1,}$")) {
            txt_customerID.setStyle("-fx-focus-color:green");
            if (event.getCode() == KeyCode.ENTER) {
                searchCustomer();
            }
        } else {
            txt_customerID.setStyle("-fx-focus-color:red");
        }
    }
}
