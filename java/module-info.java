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
    exports com.ishujaa.my_pdf_toolbox.Dialogs;
    opens com.ishujaa.my_pdf_toolbox.Dialogs to javafx.fxml;
    exports com.ishujaa.my_pdf_toolbox.Controllers;
    opens com.ishujaa.my_pdf_toolbox.Controllers to javafx.fxml;
    exports com.ishujaa.my_pdf_toolbox.Tasks;
    opens com.ishujaa.my_pdf_toolbox.Tasks to javafx.fxml;
    exports com.ishujaa.my_pdf_toolbox.Extras;
    opens com.ishujaa.my_pdf_toolbox.Extras to javafx.fxml;
}