package com.ishujaa.my_pdf_toolbox;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class Helper {
    private PDDocument pdDocument;
    public int pageCount;
    Helper(File file) throws IOException {
        pdDocument = PDDocument.load(file);
        pageCount = pdDocument.getPages().getCount();

    }
    public int getPagesCount(){
        return pageCount;
    }
    public BufferedImage getImage(int pageNo, int dpi) throws IOException {
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        BufferedImage image = renderer.renderImageWithDPI(pageNo, dpi);
        return image;
    }

    public PDDocument getPdDocument(){
        return pdDocument;
    }

    public void exportImages(int dpi, String format, String exportLocation) throws IOException {
        PDFRenderer renderer = new PDFRenderer(pdDocument);
        for(int i=0; i<pageCount; i++){
            BufferedImage bufferedImage = renderer.renderImageWithDPI(i, dpi);
            ImageIO.write(bufferedImage, format, new File(exportLocation + "\\Page " + (i+1)+"."+format.toLowerCase(Locale.ROOT)));
        }
    }
}
