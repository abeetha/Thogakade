package com.seekerscloud.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    public AnchorPane dashboardContext;
    public Label lblDate;
    public Label lblTime;
    
    public void initialize(){
        setDateAndTime();
    }

    private void setDateAndTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void openCustomerFormOnAction(ActionEvent actionEvent) {
    }

    public void openItemFormOnAction(ActionEvent actionEvent) {
    }

    public void openOrderDetailsFormOnAction(ActionEvent actionEvent) {
    }

    public void openPlaceOrderFormOnAction(ActionEvent actionEvent) {
    }

    private void setUi(String ui){

    }
}
