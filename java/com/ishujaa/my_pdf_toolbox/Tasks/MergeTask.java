package com.ishujaa.my_pdf_toolbox.Tasks;

import javafx.concurrent.Task;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.util.List;

public class MergeTask extends Task<Void> {

    private String destFileName;
    private List<File> files;

    public MergeTask(String destFileName, List<File> files){
        this.destFileName = destFileName;
        this.files = files;
    }

    @Override
    protected Void call() throws Exception {

        PDFMergerUtility utility = new PDFMergerUtility();
        utility.setDestinationFileName(destFileName);
        for(File file: files) utility.addSource(file);

        utility.mergeDocuments();

        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
    }
}
