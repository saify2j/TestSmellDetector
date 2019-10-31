package detector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.base.Strings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StatementsLine {

    public String fs = "";
    public Result r;
    public ArrayList<Result> ar =new ArrayList<>();
    public ArrayList<Result> statementsByLine(File projectDir) {

        new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
//            System.out.println(path);

            try {
                new NodeIterator(new NodeIterator.NodeHandler() {

                    @Override
                    public boolean handle(Node node) {
                        if (node instanceof Statement) {
                            // System.out.println(" [Lines " + node.getBegin() + " - " + node.getEnd() + " ] " + node);

                            boolean find = false;
                            node.removeComment();
                            String fullBody = node.toString();
                            int begin = 0;
                            String line;
                            Scanner sc = new Scanner(fullBody);
                            while (sc.hasNextLine()) {
                                line = sc.nextLine();
                                begin++;
                                String str = line;
                                Matcher m = Pattern.compile("\\w://[^\"]+").matcher(str);
                                while (m.find()) {
//                                    System.out.println(m.group());
                                    File tempFile = new File(m.group());
                                    int lineNumber = 0;
                                    boolean exists = tempFile.exists();
                                    if (exists) {
                                        //System.out.println("file exists");
                                        lineNumber = node.getBegin().get().line + begin;
                                    } else {
                                        //System.out.println("no exists");
                                        find = true;
                                        lineNumber = node.getBegin().get().line + begin;
//                                        System.out.println(lineNumber + begin);
                                    }
                                    r = new Result(path, m.group(), lineNumber + 1);
                                    ar.add(r);

                                }


                            }

                            return false;
                        } else {
                            return true;
                        }
                    }
                }).explore(JavaParser.parse(file));
                System.out.println(); // empty line
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }).explore(projectDir);
        //System.out.println(r);
        return ar;
    }


}

