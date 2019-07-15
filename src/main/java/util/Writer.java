package util;

import java.io.*;

public class Writer {

    public static String getErrorMessage(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return "FAIL\n" + sw.toString();
    }

    public static void write(String result) {
        File file = new File("log.txt");
        try {
            file.createNewFile();
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] strToBytes = result.getBytes();
                outputStream.write(strToBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
