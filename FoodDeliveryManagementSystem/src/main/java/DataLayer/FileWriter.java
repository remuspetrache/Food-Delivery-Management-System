package DataLayer;

import java.io.IOException;

public class FileWriter {
    java.io.FileWriter fileWriter;

    public FileWriter(String path) {
        try {
            fileWriter = new java.io.FileWriter(path + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String string) {
        try {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFileWriter() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
