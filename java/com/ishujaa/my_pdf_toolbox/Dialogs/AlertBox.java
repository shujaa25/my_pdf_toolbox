package com.ishujaa.my_pdf_toolbox.Dialogs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AlertBox {
    public static void display(String title, String message){

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);

        Label label = new Label();
        label.setText(message);
        label.setAlignment(Pos.CENTER);

        Button button = new Button("Close");
        button.setOnAction(e -> stage.close());

        vBox.getChildren().addAll(label, button);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
