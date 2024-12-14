import java.util.Scanner;

import javax.swing.*;
import java.io.*;

public class FileInspector {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        JFileChooser fileChooser = new JFileChooser(new File("src"));
        int result = fileChooser.showOpenDialog(null);

        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processFile(selectedFile);
        } else {
            System.out.println("No file selected. Program terminated.");
        }
    }

    private static void processFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        System.out.println("\n--- File Contents ---");

        try(Scanner scan = new Scanner(file)) {
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                lineCount++;
                System.out.println(line);

                String[] words = line.split("\\s+");
                wordCount = wordCount + words.length;
                charCount = charCount + line.length();
            }
            System.out.println("\n--- Summary Report ---");
            System.out.println("File name: " + file.getName());
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Number of words: " + wordCount);
            System.out.println("Number of characters: " + charCount);
        } catch(FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
