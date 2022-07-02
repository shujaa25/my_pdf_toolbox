package com.ishujaa.my_pdf_toolbox;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bouncycastle.est.ESTAuth;

import java.io.IOException;

public class FXWaitBox {
    static Stage stage;

    @FXML
    static ProgressBar progressBar1;

    public static void startTask(Task task){
        progressBar1.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        System.out.println("Task started.");
    }

    public static void display(Task task) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("waitDialogScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Please Wait");
        stage.setScene(scene);
        stage.show();

        startTask(task);

    }
    public static void close(){
        stage.close();
    }
}

