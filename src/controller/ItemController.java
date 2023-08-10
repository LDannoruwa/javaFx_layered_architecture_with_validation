package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import dto.ItemDto;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import service.ServiceFactory;
import service.custom.ItemService;
import view.tableModule.ItemTM;

public class ItemController {
    @FXML
    private TableColumn<ItemTM, Button> table_col_itemDeleteButton;

    @FXML
    private TableColumn<ItemTM, String> table_col_itemDescription;

    @FXML
    private TableColumn<ItemTM, String> table_col_itemID;

    @FXML
    private TableColumn<ItemTM, Double> table_col_itemQOH;

    @FXML
    private TableColumn<ItemTM, Double> table_col_itemUnitPrice;

    @FXML
    private TableView<ItemTM> table_itemData;

    @FXML
    private TextField txt_QOH;

    @FXML
    private TextField txt_itemDescription;

    @FXML
    private TextField txt_itemID;

    @FXML
    private TextField txt_unitPrice;

    ItemService itemService;

    public void initialize() {
        itemService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM);
        table_col_itemID.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        table_col_itemDescription.setCellValueFactory(new PropertyValueFactory<>("item_description"));
        table_col_itemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("item_unitPrice"));
        table_col_itemQOH.setCellValueFactory(new PropertyValueFactory<>("item_QOH"));
        table_col_itemDeleteButton.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        getItemDetails();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            // get inserted data from the text fields
            String id = txt_itemID.getText();
            String description = txt_itemDescription.getText();
            double unitPrice = Double.parseDouble(txt_unitPrice.getText());
            double qtyOnHand = Double.parseDouble(txt_QOH.getText());

            ItemDto itemDto = new ItemDto(id, description, unitPrice, qtyOnHand);

            boolean isSaved = itemService.saveItem(itemDto);

            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Item is saved.!").show();
                // load all Items after save new one
                getItemDetails();
            } else {
                new Alert(AlertType.ERROR, "Item is not saved..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String id = txt_itemID.getText();
            String description = txt_itemDescription.getText();
            double unitPrice = Double.parseDouble(txt_unitPrice.getText());
            double qtyOnHand = Double.parseDouble(txt_QOH.getText());

            ItemDto itemDto = new ItemDto(id, description, unitPrice, qtyOnHand);

            boolean isUpdated = itemService.updateItem(itemDto);

            if (isUpdated) {
                new Alert(AlertType.CONFIRMATION, "Item is updated.!").show();
                // load all items after update new one
                getItemDetails();
            } else {
                new Alert(AlertType.ERROR, "Item is not updated..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtItemIDOnAction(ActionEvent event) {
        try {
                String id = txt_itemID.getText();
                ItemDto itemDto = itemService.getItem(id);

            if (itemDto != null) {
                txt_itemDescription.setText(itemDto.getItem_description());
                txt_unitPrice.setText(String.valueOf(itemDto.getItem_unitPrice()));
                txt_QOH.setText(String.valueOf(itemDto.getItem_QOH()));
            } else {
                new Alert(AlertType.ERROR, "Item Not Found. Please check the item id and try again.!").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getItemDetails() {

        try {
            ArrayList<ItemDto> allItems = itemService.getAllItem();
            ObservableList<ItemTM> tmList = FXCollections.observableArrayList();
            for (ItemDto item : allItems) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
                btnDelete.setTextFill(Color.web("#ecf0f1"));
                ItemTM itemTM = new ItemTM(item.getItem_id(), item.getItem_description(), item.getItem_unitPrice(),
                        item.getItem_QOH(), btnDelete);
                tmList.add(itemTM);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();
                    try {
                        if (result.orElse(no) == ok) {
                            if (itemService.deleteItem(itemTM.getItem_id())) {
                                new Alert(AlertType.CONFIRMATION, "Item is deleted.!").show();
                                getItemDetails();
                                return;
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });
            }

            table_itemData.setItems(tmList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
