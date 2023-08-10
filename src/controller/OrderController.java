package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailDto;
import dto.OrderDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.impl.OrderServiceImpl;
import view.tableModule.OrderTM;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OrderController {
    @FXML
    private ComboBox<Integer> comBox_cusID;

    @FXML
    private ComboBox<String> comBox_itemID;

    @FXML
    private Label lbl_customerAddress;

    @FXML
    private Label lbl_customerName;

    @FXML
    private Label lbl_itemDescription;

    @FXML
    private Label lbl_itemQOH;

    @FXML
    private Label lbl_itemUnitPrice;

    @FXML
    private TableColumn<OrderTM, Button> table_col_itemRemoveButton;

    @FXML
    private TableColumn<OrderTM, String> table_col_itemDescription;

    @FXML
    private TableColumn<OrderTM, String> table_col_itemID;

    @FXML
    private TableColumn<OrderTM, Double> table_col_itemQOH;

    @FXML
    private TableColumn<OrderTM, Double> table_col_itemQty;

    @FXML
    private TableColumn<OrderTM, Double> table_col_itemTotal;

    @FXML
    private TableColumn<OrderTM, Double> table_col_itemUnitPrice;

    @FXML
    private TableView<OrderTM> table_orderData;

    @FXML
    private TextField txt_byQTY;

    @FXML
    private Label lbl_date;

    @FXML
    private TextField txt_OrderID;

    @FXML
    private Label lbl_subTotal;

    CustomerService customerService;
    ItemService itemService;
    OrderServiceImpl OrderService;

    public void initialize() {
        customerService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);
        itemService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM);
        OrderService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ORDER);

        lbl_date.setText(String.valueOf(LocalDate.now()));

        loadCustomerID();
        loadItemID();
    }

    ObservableList<OrderTM> tmList = FXCollections.observableArrayList();


    @FXML
    void btnAddToChartOnAction(ActionEvent event) {
        table_col_itemID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        table_col_itemDescription.setCellValueFactory(new PropertyValueFactory<>("item_description"));
        table_col_itemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("item_unitPrice"));
        table_col_itemQOH.setCellValueFactory(new PropertyValueFactory<>("item_QOH"));
        table_col_itemQty.setCellValueFactory(new PropertyValueFactory<>("item_Qty"));
        table_col_itemTotal.setCellValueFactory(new PropertyValueFactory<>("item_total"));
        table_col_itemRemoveButton.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        try {
            String id = comBox_itemID.getValue();
            String description = lbl_itemDescription.getText();
            Double unitPrice = Double.parseDouble(lbl_itemUnitPrice.getText());
            Double qtyOnHand = Double.parseDouble(lbl_itemQOH.getText());
            Double qty = Double.parseDouble(txt_byQTY.getText());
            double total = (qty * unitPrice);
            Button btnRemove = new Button("Remove");
            btnRemove.setMaxSize(100, 50);
            btnRemove.setCursor(Cursor.HAND);
            btnRemove.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
            btnRemove.setTextFill(Color.web("#ecf0f1"));

            if (!tmList.isEmpty()) {
                for (int i = 0; i < table_orderData.getItems().size(); i++) {
                    if (table_col_itemID.getCellData(i).equals(id)) {
                        double tempQty = table_col_itemQty.getCellData(i);
                        tempQty += qty;
                        if (tempQty <= Double.parseDouble(lbl_itemQOH.getText())) {
                            total = (tempQty * unitPrice);
                            tmList.get(i).setItem_Qty(i);
                            tmList.get(i).setItem_total(total);
                            getTotal();
                            table_orderData.refresh();
                            return;
                        }
                    }
                }
            }

            OrderTM orderTM = new OrderTM(id, description, unitPrice, qtyOnHand, qty, total, btnRemove);
            tmList.add(orderTM);

            btnRemove.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);

                Optional<ButtonType> result = alert.showAndWait();
                try {
                    if (result.orElse(no) == ok) {
                        tmList.remove(orderTM);
                        table_orderData.refresh();
                        getTotal();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            });
            table_orderData.setItems(tmList);
            getTotal();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            boolean isSaved = OrderService.saveOrder(getOrder(), getOrderDetail());
            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Order is saved.!").show();

            } else {
                new Alert(AlertType.ERROR, "Order is not saved..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDto getOrder() {
        String orderId = txt_OrderID.getText();
        String orderDate = String.valueOf(LocalDate.now());
        Integer customerID = comBox_cusID.getValue();

        return new OrderDto(orderId, orderDate, customerID);
    }

    public ArrayList<OrderDetailDto> getOrderDetail() {
        String orderId = txt_OrderID.getText();

        ArrayList<OrderDetailDto> orderDetailDTOs = new ArrayList<>();
        for (int i = 0; i < table_orderData.getItems().size(); i++) {
            OrderTM orderTM = tmList.get(i);
            orderDetailDTOs
                    .add(new OrderDetailDto(orderId, orderTM.getItem_id(), orderTM.getItem_Qty(), orderTM.getItem_unitPrice()));
        }
        return orderDetailDTOs;
    }

    public void loadCustomerID() {
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDto> allCustomerID = customerService.getAllCustomerID();
            for (CustomerDto dto : allCustomerID) {
                customerIDList.add(dto.getCustomer_id());
            }
            comBox_cusID.setItems(customerIDList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadItemID() {
        ObservableList<String> itemIDList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDto> allItemId = itemService.getAllItemId();
            for (ItemDto dto : allItemId) {
                itemIDList.add(dto.getItem_id());
            }
            comBox_itemID.setItems(itemIDList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void comBoxCustomerIDOnAction(ActionEvent event) {
        Integer id = comBox_cusID.getValue();

        try {
            CustomerDto dto = customerService.getCustomer(id);
            if (dto != null) {
                lbl_customerName.setText(dto.getCustomer_name());
                lbl_customerAddress.setText(dto.getCustomer_address());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void comBoxItemIDOnAction(ActionEvent event) {
        String id = comBox_itemID.getValue();

        try {
            ItemDto dto = itemService.getItem(id);
            if (dto != null) {
                lbl_itemDescription.setText(dto.getItem_description());
                lbl_itemUnitPrice.setText(String.valueOf(dto.getItem_unitPrice()));
                lbl_itemQOH.setText(String.valueOf(dto.getItem_QOH()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTotal() {
        double total = 0.0;
        for (OrderTM orderTM : tmList) {
            total += orderTM.getItem_total();
        }
        lbl_subTotal.setText(String.valueOf(total));
    }
}
