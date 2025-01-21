package Person;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        //Declare area
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src"); //\\personData.txt"); -was previously after src
        Scanner inFile;
        String line = "";
        JFileChooser chooser = new JFileChooser();
        int lineNum = 0;
        String splitByThisPleaseItHasBeenOverAnHour = ",";

        //set the chooser to the project source directory
        chooser.setCurrentDirectory(file.toFile());

        //logic
        try {
            //Logic for Jfilechooser
            if (chooser.showDialog(null,null) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile().toPath();


                inFile = new Scanner(file);


                System.out.printf("%-6s %-9s %10s %7s %4s %n", "ID#", "Firstname", "Lastname", "title", "YOB");
                System.out.println("=========================================");
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    lineNum++;
                    String[] whatEvs = line.split(splitByThisPleaseItHasBeenOverAnHour);
                    System.out.printf("%-6s %-11s %-10s %-6s %-6s\n", whatEvs[0], whatEvs[1], whatEvs[2], whatEvs[3], whatEvs[4]);
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
