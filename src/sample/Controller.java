package sample;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.base.Strings;
import detector.DirExplorer;
import detector.NodeIterator;
import detector.StatementsLinesExample;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.security.krb5.internal.APRep;

import java.awt.*;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    private TextField folderLoc;
    @FXML
    private Label detected;
    private String folderName;
    public void openFileChooser(ActionEvent actionEvent) {

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Select Project Folder");
            File file = chooser.showDialog(new Stage());
            if (String.valueOf(file).equals("null")) {
                folderLoc.setText("Please Select A Project");
            }
            else {
                folderLoc.setText(String.valueOf(file));
                folderName=String.valueOf(file);
            }
    }

    public void testSmell(ActionEvent actionEvent) {

        detected.setText(folderName);
    }
}
