import java.util.Scanner;
import java.util.ArrayList;

import java.io.*;
import java.io.IOException;
import java.io.FileWriter;

public class DataSaver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean continueInput = true;

        do {
            String firstName = SafeInput.getNonZeroLenString(scan, "Enter your first name");
            String lastName = SafeInput.getNonZeroLenString(scan, "Enter your last name");
            String idNumber = String.format("%06d", SafeInput.getInt(scan, "Enter your 6 digit ID"));
            String email = SafeInput.getNonZeroLenString(scan, "Enter your email");
            int birthYear = SafeInput.getRangedInt(scan, "Enter your birth year", 1900, 2024);

            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, idNumber, email, birthYear);
            records.add(record);

            continueInput = SafeInput.getYNConfirm(scan, "Do you want to enter another record?");
        } while(continueInput);

        String fileName = SafeInput.getNonZeroLenString(scan, "Enter the file name including extension (.csv)");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data has been saved to " + fileName);
        } catch(IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }
    }
}
