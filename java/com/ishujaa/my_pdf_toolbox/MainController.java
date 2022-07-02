package com.ishujaa.my_pdf_toolbox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    protected void btnSaveAsImageClick(){

    }
}