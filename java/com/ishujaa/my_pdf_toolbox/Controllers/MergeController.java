package com.ishujaa.my_pdf_toolbox.Controllers;

import com.ishujaa.my_pdf_toolbox.Dialogs.WaitBox;
import com.ishujaa.my_pdf_toolbox.Tasks.MergeTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MergeController implements Initializable{
    @FXML
    ListView<String> listview;
    ArrayList<String> fileNames;
    ArrayList<File> fileList;
    String saveLocation;

    private void setFileNameItems(){
        ObservableList<String> observableList = FXCollections.observableArrayList(fileNames);
        listview.setItems(observableList);
    }

    @FXML
    protected void btnAddClick(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF Files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        try {
            List<File> files = fileChooser.showOpenMultipleDialog(listview.getScene().getWindow());
            for (File file : files) {
                fileNames.add(file.getName());
                fileList.add(file);
            }
            setFileNameItems();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    protected void btnRemoveClick(){
        try{
            int index = listview.getSelectionModel().getSelectedIndex();
            if(index > -1){
                fileNames.remove(index);
                fileList.remove(index);
                setFileNameItems();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    protected void btnMoveUpClick(){

    }
    @FXML
    protected void btnMoveDownClick(){}
    @FXML
    protected void btnExportClick(){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Save Location");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
            saveLocation = fileChooser.showSaveDialog(listview.getScene().getWindow()).getPath();

            if(!saveLocation.isEmpty()){
                MergeTask task = new MergeTask(saveLocation, fileList);
                WaitBox.display(task);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileList = new ArrayList<>();
        fileNames = new ArrayList<>();
        saveLocation = "";
    }
}
