package detector;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;
import com.google.common.base.Strings;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StatementsLinesExample {
    public static void statementsByLine(File projectDir) {
        new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
            System.out.println(path);
            System.out.println(Strings.repeat("=", path.length()));
            new NodeIterator(new NodeIterator.NodeHandler() {
                @Override
                public boolean handle(Node node) {
                    if (node instanceof Statement) {
                        // System.out.println(" [Lines " + node.getBegin() + " - " + node.getEnd() + " ] " + node);

                        String fullBody = node.toString() ;
                        String line ;
                        Scanner sc = new Scanner(fullBody) ;
                        while(sc.hasNextLine()) {
                            line = sc.nextLine() ;

                            String str = line ;
                            Matcher m = Pattern.compile("\\w://[^\"]+").matcher(str);
                            while(m.find()) {
                                System.out.println("\\n\\n\\n#########"+m.group());

                                File tempFile = new File(m.group());
                                boolean exists = tempFile.exists();

                                if(exists) {
                                    System.out.println("No smell");
                                }
                                else System.out.println("smells detected");

                            }
                        }

                        return false;
                    } else {
                        return true;
                    }
                }
            }).explore(JavaParser.parse(String.valueOf(file)));
            System.out.println(); // empty line
        }).explore(projectDir);
    }
    public static void main(String[] args) {
        File projectDir = new File("C:\\\\Users\\\\rez1\\\\eclipse-workspace\\\\Game");

        if (projectDir.exists()){
            System.out.println("this directory exists");
        }
        else System.out.println("how to get this working");
        statementsByLine(projectDir);
    }
}