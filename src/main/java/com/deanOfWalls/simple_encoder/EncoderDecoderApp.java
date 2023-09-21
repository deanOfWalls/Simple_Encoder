package com.deanOfWalls.simple_encoder;// Import required Java libraries
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

// Define the main class EncoderDecoderApp
public class EncoderDecoderApp {
    // Define the main method
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Declare variables to hold the command and text
        String command;
        String text;
        // Define the file path where encoded text will be saved
        String filePath = "encodedText.txt";

        // Start an infinite loop to keep the program running
        while (true) {
            // Prompt the user to enter a command
            System.out.print("Enter your command (encode/decode): ");
            // Read the command from the user
            command = scanner.nextLine().trim();

            // Check if the command starts with "encode: "
            if (command.startsWith("encode: ")) {
                // Extract the text to be encoded
                text = command.substring(8);
                // Encode the text
                String encodedText = encode(text);
                // Save the encoded text to a file
                saveToFile(encodedText, filePath);
                // Notify the user that the text has been encoded and saved
                System.out.println("Text encoded and saved to " + filePath);
            }
            // Check if the command starts with "decode: "
            else if (command.startsWith("decode: ")) {
                // Extract the text to be decoded
                text = command.substring(8);
                // Decode the text
                String decodedText = decode(text);
                // Display the decoded text
                System.out.println("Decoded text: " + decodedText);
            }
            // Handle invalid commands
            else {
                // Notify the user that the command is invalid
                System.out.println("Invalid command. Please use 'encode: <text>' or 'decode: <text>'");
            }
        }
    }

    // Define a method to encode text using Base64
    public static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    // Define a method to decode text using Base64
    public static String decode(String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);
    }

    // Define a method to save text to a file
    public static void saveToFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the text to the file
            writer.write(text);
        } catch (IOException e) {
            // Handle any IOExceptions that occur
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    // Define a method to read text from a file
    public static String readFromFile(String filePath) {
        try {
            // Read all bytes from the file and convert them to a string
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            // Handle any IOExceptions that occur
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null;
        }
    }
}
