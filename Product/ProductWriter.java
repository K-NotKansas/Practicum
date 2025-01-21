package Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        //declare area
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;
        String ID = "";
        String name = "";
        String description = "";
        String productRecord = "";
        double cost = 00.00;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt"); /*formerly saved data to
         a .txt, now saves to a csv*/

        //operation loop
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [six digits]");
            name = SafeInput.getNonZeroLenString(in, "Provide the product name");
            description = SafeInput.getNonZeroLenString(in, "Provide a succinct product description");
            cost = SafeInput.getDouble(in, "Provide the price");
            productRecord = ID + "," + name + "," + description + "," + cost;
            products.add(productRecord);

            done = SafeInput.getYNConfirm(in, "Are you done?");

        } while (!done);

        //Debug input
        for (String p : products) {
            System.out.println(p);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()))) {

                // Finally can write the file LOL!

                for (String rec : products)//products used to be recs.
                {
                    writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                    // 0 is where to start (1st char) the write
                    // rec. length() is how many chars to write (all)
                    writer.newLine();  // adds the new line

                }
                writer.close(); // must close the file to seal it and flush buffer
                System.out.println("Data file written!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

