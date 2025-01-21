package Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        //declare area
        ArrayList<String> peeps = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;
        String personRecord = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yearOfBirth = 0;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt"); /*formerly saved data to
         a .txt, now saves to a csv*/

        //operation loop
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [six digits]");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the First Name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter the Title");
            yearOfBirth = SafeInput.getRangedInt(in, "Provide the year of birth", 1000, 9999);
            personRecord = ID + "," + firstName + "," + lastName + "," + title + "," + yearOfBirth;
            peeps.add(personRecord);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        }while (!done);

        //Debug input
        for(String p:peeps){
            System.out.println(p);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
            {

                // Finally can write the file LOL!

                for(String rec : peeps)//products used to be recs.
                {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}