package com.ishujaa.my_pdf_toolbox;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;

public class SaveImagesTask extends Task<Void> {
    PDDocument pdDocument;
    ObservableList<Integer> selectedPages;
    int dpi;
    String format;
    String exportLocation;
    SaveImagesTask(PDDocument pdDocument, ObservableList<Integer> selectedPages,
                   int dpi, String format, String exportLocation){
        this.selectedPages = selectedPages;
        this.pdDocument = pdDocument;
        this.exportLocation = exportLocation;
        this.dpi = dpi;
        this.format = format;
    }

    @Override
    protected Void call() throws Exception {

        PDFRenderer renderer = new PDFRenderer(pdDocument);
        int pagesProcessed=0;
        for(Integer page: selectedPages){
            BufferedImage bufferedImage = renderer.renderImageWithDPI(page, dpi);
            ImageIO.write(bufferedImage, format, new File(exportLocation + "\\Page " + (page+1)+"."+format.toLowerCase(Locale.ROOT)));
            updateProgress(pagesProcessed+1, selectedPages.size());
            pagesProcessed+=1;
        }

        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
    }
}
