import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateFile {
    public static void main(String[] args){
        try {
            File readFile = new File("C:\\Users\\user\\OneDrive\\Desktop\\MyJavaBeginnerProjects\\Files\\src\\ReadFile.txt");
            File writeFile = new File("C:\\Users\\user\\OneDrive\\Desktop\\MyJavaBeginnerProjects\\Files\\src\\WriteFile.txt");

            if (readFile.createNewFile()) {
                System.out.println("File created: " + readFile.getName());
            } else {
                System.out.println("ReadFile already exists");
            }

            if (writeFile.createNewFile()) {
                System.out.println("File created: " + writeFile.getName());
            } else {
                System.out.println("WriteFile already exists");
            }

            // Write into WriteFile
            FileWriter writeFile1 = new FileWriter(writeFile);
            writeFile1.write("This is a write file!");
            writeFile1.close();
            System.out.println("WriteFile written successfully.");

            // Write into ReadFile
            FileWriter readFileWriter = new FileWriter(readFile);
            readFileWriter.write("This is a read file!");
            readFileWriter.close();
            System.out.println("ReadFile written successfully.");

            // ✅ Read from ReadFile (use File, not FileWriter)
            Scanner myReader = new Scanner(readFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
