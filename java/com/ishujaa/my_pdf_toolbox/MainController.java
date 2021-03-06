package com.ishujaa.my_pdf_toolbox;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

    }

    @FXML
    protected void btnViewPDFClick(){
        new PDFViewer();
    }
    @FXML
    protected void btnSaveAsImageClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("saveImageScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Export To Image");
        stage.setScene(scene);
        stage.show();
    }
}