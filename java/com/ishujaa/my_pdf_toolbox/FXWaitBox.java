package com.ishujaa.my_pdf_toolbox;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bouncycastle.est.ESTAuth;

import java.io.IOException;

public class FXWaitBox {

    public static void display(Task task) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Please Wait");
        stage.setMinWidth(250);
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.progressProperty().bind(task.progressProperty());
        VBox layout = new VBox(10);
        layout.getChildren().add(progressBar);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        stage.setScene(scene);

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        stage.show();

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                stage.close();
            }
        });

    }
}

