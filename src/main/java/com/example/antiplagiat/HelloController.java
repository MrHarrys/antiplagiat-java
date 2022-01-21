package com.example.antiplagiat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;

public class HelloController {

    @FXML
    private Label welcomeText1;
    @FXML
    private Button importButton;
    @FXML
    private Button rewriteButton;
    @FXML
    private Label checkFileWrite;

    String newString = "";

    @FXML
    protected void onImportButtonClick() throws Exception {
        Stage currentStage = HelloApplication.getPrimaryStage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Select text file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All file's", "*.*"),
                new FileChooser.ExtensionFilter("Text file's", "*.txt")
        );
        File workFile = fileChooser.showOpenDialog(currentStage);
        if (workFile != null) {
            System.out.println(fileChooser.getSelectedExtensionFilter());
            welcomeText1.setText("You select " + workFile.getAbsolutePath() + " file");
            importButton.setText("Import new file");
            newString = readFile(workFile);
        } else System.out.println("Something go wrong");
    }

    @FXML
    protected void onRewriteButtonClick() throws Exception {
        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All file's", "*.*"),
                new FileChooser.ExtensionFilter("Text file's", "*.txt")
        );
        File saveFile = saveFileChooser.showSaveDialog(HelloApplication.getPrimaryStage());

        writeFile(newString, saveFile);
    }

    private String readFile(File file) {
        String stringS;
        char[] buf = new char[256];
        int c;
        try (FileReader reader = new FileReader(file)) {
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
            }
            rewriteButton.setVisible(true);
            stringS = new String(buf);
            return stringS;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    private void writeFile(String buffer, File file) throws IOException {

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        fileWriter.write(HelloController.replaceChar(buffer));
        fileWriter.flush();
        checkFileWrite.setText("Success");
    }

    private static String replaceChar(String text) {
        return text
                .replaceAll("А", "A")
                .replaceAll("а", "a")
                .replaceAll("Е", "Е")
                .replaceAll("е", "e")
                .replaceAll("Т", "T")
                .replaceAll("у", "y")
                .replaceAll("О", "O")
                .replaceAll("о", "o")
                .replaceAll("Р", "P")
                .replaceAll("р", "p")
                .replaceAll("Н", "H")
                .replaceAll("К", "K")
                .replaceAll("Х", "X")
                .replaceAll("х", "x")
                .replaceAll("С", "C")
                .replaceAll("с", "c")
                .replaceAll("В", "B")
                .replaceAll("М", "M");
    }

    private static String getFileExtension(File file) {
        String fileExtension = file.getName();
        if (fileExtension.lastIndexOf(".") != -1 && fileExtension.lastIndexOf(".") != 0)
            return fileExtension.substring(fileExtension.lastIndexOf("."));
        else return "";
    }
}