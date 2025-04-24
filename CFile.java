import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CFile {

    private int[] list = new int[60];
    private File file = new File("file.txt");

    public File getFile() {
        return file;
    }

    public void FFile(){
        File file = new File("file.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter fh= new FileWriter(file, false)) {
            fh.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < 60; i++){
            Random rand = new Random();
            int size = rand.nextInt(100, 10000);
            list[i] = size;
            try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
                for (int j = 0; j < size; j++) {
                    String s = Integer.toString(rand.nextInt(0, 100000)) + " ";
                    outputStream.write(s.getBytes());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public int[] getList() {
        return list;
    }
}

