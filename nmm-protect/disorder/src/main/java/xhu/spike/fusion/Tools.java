package xhu.spike.fusion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Tools {
    public static void log(String msg) {
        System.out.println(msg);
    }

    public static List<String> readAsLines(File target) throws IOException {
        return Files.readAllLines(Paths.get(target.toURI()));
    }

    public static String readAsString(File target) throws IOException {
        return new String(Files.readAllBytes(Paths.get(target.toURI())));
    }

    public static void writeAsLines(File target, List<String> lines) throws IOException {
        FileWriter fw = new FileWriter(target);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public static void writeAsString(File target, String content) throws IOException {
        FileWriter fw = new FileWriter(target);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }

    public static String cutStr(String source, String s, String e) {
        int start = source.indexOf(s);
        if (start < 0) {
            return null;
        }
        start += s.length();
        int end = source.indexOf(e, start);
        return source.substring(start, end);
    }

    public static int operateAllFiles(File target, FileOperator operator) {
        int count = 0;
        if (target.isDirectory()) {
            File[] files = target.listFiles();
            if (files != null) {
                for (File file : files) {
                    count += operateAllFiles(file, operator);
                }
            }
        } else if (operator.doAction(target)) {
            count++;
        }
        return count;
    }

    public interface FileOperator{
        boolean doAction(File f);
    }

    public static String readAsString(InputStream is) {
        try {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            return "";
        }
    }
}
