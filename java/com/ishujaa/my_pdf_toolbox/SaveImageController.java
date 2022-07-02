package com.ishujaa.my_pdf_toolbox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javafx.embed.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveImageController implements Initializable {

    private Helper helper;
    private ObservableList<Integer> currentSelection;
    private FlowPane flowPane;
    private File exportFolder;

    @FXML
    Label fileNameLabel;
    @FXML
    ListView pagesListView;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TextField dpiTextField;
    @FXML
    Label exportFolderLabel;
    @FXML
    ComboBox formatComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] supportedFormats = {"JPEG", "PNG", "BMP", "WEBMP", "GIF"};
        formatComboBox.getItems().addAll(supportedFormats);


        flowPane = new FlowPane(Orientation.HORIZONTAL);
        flowPane.setVgap(5); flowPane.setHgap(5);
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        pagesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pagesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                currentSelection = pagesListView.getSelectionModel().getSelectedIndices();
                flowPane.getChildren().clear();
                for(Integer i: currentSelection){
                    addImage(i);
                }
            }
        });

    }

    private void addImage(int pageNo){
        try{
            BufferedImage bufferedImage = helper.getImage(pageNo, 48);
            Image image =  SwingFXUtils.toFXImage(bufferedImage, null);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(400);
            imageView.setFitHeight(400);

            Label label = new Label();
            label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            label.setGraphic(imageView);
            label.setTooltip(new Tooltip("Page "+(pageNo+1)));


            flowPane.getChildren().add(label);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void btnChoosePDFClick(){
        try{
            FileChooser pdfChooser = new FileChooser();
            pdfChooser.setTitle("Select PDF");
            pdfChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File selectedPDF = pdfChooser.showOpenDialog(null);
            if(selectedPDF != null){

                helper = new Helper(selectedPDF);
                fileNameLabel.setText(selectedPDF.getName());
                int pagesCount = helper.getPagesCount();
                pagesListView.getItems().clear();
                currentSelection = null;
                flowPane.getChildren().clear();
                for (int i=1; i<=pagesCount; i++) pagesListView.getItems().add("Page "+i);
                addImage(0);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void btnExportClick(){
        if(!fileNameLabel.getText().equals("no file selected")){
            if(currentSelection != null){
                if(formatComboBox.getSelectionModel().getSelectedItem() != null){
                    if(exportFolder != null){
                        try{
                            SaveImagesTask task = new SaveImagesTask(helper.getPdDocument(),
                                    currentSelection, Integer.parseInt(dpiTextField.getText()),
                                    formatComboBox.getSelectionModel().getSelectedItem().toString(),
                                    exportFolder.getAbsolutePath());

                            FXWaitBox.display(task);


                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }else{
                        System.out.println("Please select output folder.");
                    }
                }else{
                    System.out.println("Please select the export format.");
                }
            }else{
                System.out.println("Please select at least one page.");
            }
        }else {
            System.out.println("Please load a pdf.");
        }
    }

    @FXML
    protected void btnSelectExportFolderClick(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        exportFolder = directoryChooser.showDialog(null);
        if(exportFolder != null){
            exportFolderLabel.setText(exportFolder.getName());
        }
    }

    @FXML
    protected void btnSelectAllClick(){
        pagesListView.getSelectionModel().selectAll();
    }
    @FXML
    protected void btnDeselectAllClick(){
        pagesListView.getSelectionModel().clearSelection();
    }
}
