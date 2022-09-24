package com.seekerscloud.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.seekerscloud.pos.db.Database;
import com.seekerscloud.pos.modal.Customer;
import com.seekerscloud.pos.modal.Item;
import com.seekerscloud.pos.view.tm.CustomerTm;
import com.seekerscloud.pos.view.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;

public class ItemFormController {
    public AnchorPane itemFormContext;
    public TextField txtCode;
    public TextField txtUnitPrice;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXButton btnSaveItem;
    public TextField txtSearch;
    public TableView<ItemTm> tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOption;

    private String searchText="";

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("address"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchItems(searchText);
        tblItem.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {
                        setData(newValue);
                    }
                });
        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchItems(searchText);
                });
    }
    private void setData(ItemTm tm) {
        txtCode.setText(tm.getCode());
        txtDescription.setText(tm.getDescription());
        txtUnitPrice.setText(Double.toString(tm.getUnitPrice()));
        txtQtyOnHand.setText(Integer.toString(tm.getQtyOnHand()));

        btnSaveItem.setText("Update Customer");
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }

    public void newItemOnAction(ActionEvent actionEvent) {
    }

    public void saveItemOnAction(ActionEvent actionEvent) {
        Item i1 = new Item(txtCode.getText(), txtDescription.getText(), Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()));

        if (btnSaveItem.getText().equalsIgnoreCase("Save Item")) {
            boolean isSaved = Database.itemTable.add(i1);
            if (isSaved) {
                searchItems(searchText);
                clearFields();
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } else {
            for (int i = 0; i < Database.itemTable.size(); i++) {
                if (txtCode.getText().equalsIgnoreCase(Database.itemTable.get(i).getCode())) {
                    Database.itemTable.get(i).setDescription(txtDescription.getText());
                    Database.itemTable.get(i).setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                    Database.itemTable.get(i).setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));
                    searchItems(searchText);
                    new Alert(Alert.AlertType.INFORMATION, "Item Updated!").show();
                    clearFields();
                }

            }
        }
    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }

    private void searchItems(String text) {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        for (Item i  : Database.itemTable
        ) {
            if(i.getDescription().contains(text)){
                Button btn = new Button("Delete");
                ItemTm tm = new ItemTm(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand(), btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure whether do you want to delete this item?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        boolean isDeleted = Database.itemTable.remove(i);
                        if (isDeleted) {
                            searchItems(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    }
                });
            }
        }
        tblItem.setItems(tmList);
    }
}
