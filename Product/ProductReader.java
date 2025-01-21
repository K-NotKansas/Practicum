package Product;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main(String[] args) {
        //Declare area
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src"); //\\personData.txt"); -was previously after src
        Scanner inFile;
        String line = "";
        JFileChooser chooser = new JFileChooser();
        Object rec;
        int lineNum = 0;
        int areWeDoneYetWhyyyyy = 0;
        String splitByThisPleaseItHasBeenOverAnHour = ",";

        //set the chooser to the project source directory
        chooser.setCurrentDirectory(file.toFile());

        //logic
        try {
            //Logic for Jfilechooser
            if (chooser.showDialog(null,null) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile().toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                inFile = new Scanner(file);

                System.out.printf("%-6s %-11s %12s %13s %n", "ID#", "Name", "Product Description", "Price");
                System.out.println("=====================================================");

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    lineNum++;
                    String[] whatEvs = line.split(splitByThisPleaseItHasBeenOverAnHour);
                    System.out.printf("%-6s %-11s %-25s %8s\n", whatEvs[0], whatEvs[1], whatEvs[2], whatEvs[3]);
                }
                inFile.close();

            }
            else //user failed to pick file, closed chooser
            {
                System.out.println("Must select a file. Terminating.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found error, please try again!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
