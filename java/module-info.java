module com.ishujaa.my_pdf_toolbox {
    requires javafx.controls;
    requires javafx.fxml;
    requires pdfbox.app;
    requires java.desktop;
    requires javafx.swing;
    requires icepdf.core;
    requires icepdf.viewer;


    opens com.ishujaa.my_pdf_toolbox to javafx.fxml;
    exports com.ishujaa.my_pdf_toolbox;
}