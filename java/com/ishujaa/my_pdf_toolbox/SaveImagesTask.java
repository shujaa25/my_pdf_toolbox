package com.ishujaa.my_pdf_toolbox;

import javafx.concurrent.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;

public class SaveImagesTask extends Task<Void> {
    PDDocument pdDocument;
    int pageCount;
    int dpi;
    String format;
    String exportLocation;
    SaveImagesTask(PDDocument pdDocument, int pagesCount, int dpi, String format, String exportLocation){
        this.pageCount = pagesCount;
        this.pdDocument = pdDocument;
        this.exportLocation = exportLocation;
        this.dpi = dpi;
        this.format = format;
    }

    @Override
    protected Void call() throws Exception {

        PDFRenderer renderer = new PDFRenderer(pdDocument);
        for(int i=0; i<pageCount; i++){
            BufferedImage bufferedImage = renderer.renderImageWithDPI(i, dpi);
            ImageIO.write(bufferedImage, format, new File(exportLocation + "\\Page " + (i+1)+"."+format.toLowerCase(Locale.ROOT)));
            updateProgress(i, pageCount);
        }

        return null;
    }
}
