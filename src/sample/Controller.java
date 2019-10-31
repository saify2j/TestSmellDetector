package sample;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.base.Strings;
import detector.*;
import detector.StatementsLine;
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
import java.util.ArrayList;
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
        } else {
            folderLoc.setText(String.valueOf(file));
            folderName = String.valueOf(file);
        }
    }

    @FXML
    private TextArea result;

    public void testSmell(ActionEvent actionEvent) {

//        detected.setText(folderName);
        StatementsLine sl = new StatementsLine();
        ArrayList<Result> results = new ArrayList<>();
        results = sl.statementsByLine(new File(folderName));
        //System.out.println(results);
        StringBuilder fieldContent = new StringBuilder("");



        for (Result s : results) {
            System.out.println(s.getSmellpath());
            File f = new File(s.getSmellpath());
            boolean exists = f.exists();
            if (exists) {
                fieldContent.append(s.toString() +" FILE EXISTS"+ "\n");

            }
            else fieldContent.append(s.toString() +" FILE DOES NOT EXIST"+ "\n");
        }
        result.setText(fieldContent.toString());
    }
}
