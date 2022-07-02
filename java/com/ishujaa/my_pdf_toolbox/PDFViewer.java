package com.ishujaa.my_pdf_toolbox;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class PDFViewer extends JFrame{
    //JScrollPane jScrollPane;
    PDFViewer(){

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icons/pdf_icon.png");
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("View PDF - My PDF ToolBox");
        setSize(1000, 800);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        //jScrollPane = new JScrollPane();
        //jScrollPane.setLayout(new ScrollPaneLayout());
        //jScrollPane.setPreferredSize(new Dimension(700, 500));
        //jScrollPane.setLocation(0, 0);
        //add(jScrollPane, BorderLayout.CENTER);
        loadPDF();
    }
    public void loadPDF(){
        try {
            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("PDF file (*.pdf)", "pdf");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select the pdf file");
            fileChooser.addChoosableFileFilter(extensionFilter);
            fileChooser.setFileFilter(extensionFilter);
            int res = fileChooser.showOpenDialog(null);
            if(res == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();

                System.getProperties().put("org.icepdf.core.imageReference", "smoothScaled");
                System.getProperties().put("org.icepdf.core.screen.interpolation", "VALUE_INTERPOLATION_BICUBIC");

                SwingController swingController = new SwingController();
                SwingViewBuilder viewBuilder = new SwingViewBuilder(swingController);
                JPanel panel = viewBuilder.buildViewerPanel();
                ComponentKeyBinding.install(swingController, panel);
                swingController.getDocumentViewController().setAnnotationCallback(
                        new MyAnnotationCallback(swingController.getDocumentViewController())
                );
                swingController.openDocument(file.getAbsolutePath());
                //jScrollPane.setViewportView(panel);
                add(panel);
            }else{
                JOptionPane.showMessageDialog(this, "No File Selected.");
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
