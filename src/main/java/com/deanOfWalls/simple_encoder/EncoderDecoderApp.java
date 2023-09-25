package com.deanOfWalls.simple_encoder;

// Import necessary Java libraries
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class EncoderDecoderApp {

    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);
        String command;  // Variable to store user command
        String text;     // Variable to store text input from user
        String filePath = "encodedText.txt";  // Default file path for encoded text

        // Infinite loop to keep the program running and accepting commands
        while (true) {
            // Prompt the user for a command
            System.out.print("Enter your command (encode/decode/encode_image/decode_image): ");
            // Read the user's command and trim any leading or trailing whitespace
            command = scanner.nextLine().trim();

            // Check if the command is to encode text
            if (command.startsWith("encode: ")) {
                // Extract the text to be encoded from the command
                text = command.substring(8);
                // Encode the text using Base64
                String encodedText = encode(text);
                // Save the encoded text to a file
                saveToFile(encodedText, filePath);
                // Notify the user of the successful encoding
                System.out.println("Text encoded and saved to " + filePath);
            } 
            // Check if the command is to decode text
            else if (command.startsWith("decode: ")) {
                // Extract the encoded text from the command
                text = command.substring(8);
                // Decode the text from Base64
                String decodedText = decode(text);
                // Display the decoded text to the user
                System.out.println("Decoded text: " + decodedText);
            } 
            // Check if the command is to encode an image
            else if (command.startsWith("encode_image: ")) {
                // Extract the image filename from the command
                String imageName = command.substring(13).trim();
                try {
                    // Read the image file into a byte array
                    byte[] imageBytes = Files.readAllBytes(Paths.get(imageName));
                    // Encode the image bytes using Base64
                    String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
                    // Save the encoded image to a file
                    saveToFile(encodedImage, "encodedFiles.txt");
                    // Add a blank line to separate encoded images in the file
                    saveToFile("", "encodedFiles.txt");
                    // Delete the original image file
                    Files.delete(Paths.get(imageName));
                    // Notify the user of the successful encoding
                    System.out.println("Image encoded and saved to encodedFiles.txt");
                } catch (IOException e) {
                    // Handle any exceptions that occur during the encoding process
                    System.out.println("An error occurred while encoding the image: " + e.getMessage());
                }
            } 
            // Check if the command is to decode an image
            else if (command.startsWith("decode_image: ")) {
                // Extract the index of the encoded image from the command
                int imageIndex = Integer.parseInt(command.substring(13).trim());
                try {
                    // Read all lines from the encoded images file
                    List<String> lines = Files.readAllLines(Paths.get("encodedFiles.txt"));
                    // Get the encoded image string based on the provided index
                    String encodedImage = lines.get(imageIndex);
                    // Decode the image bytes from Base64
                    byte[] decodedImageBytes = Base64.getDecoder().decode(encodedImage);
                    // Define the output filename for the decoded image
                    String outputImageName = "decoded_image_" + imageIndex + ".png";
                    // Write the decoded image bytes to a new image file
                    Files.write(Paths.get(outputImageName), decodedImageBytes);
                    // Notify the user of the successful decoding
                    System.out.println("Image decoded and saved as " + outputImageName);
                } catch (IOException e) {
                    // Handle any exceptions that occur during the decoding process
                    System.out.println("An error occurred while decoding the image: " + e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    // Handle the case where the provided index is out of bounds
                    System.out.println("Invalid image index.");
                }
            } 
            else {
                // Handle any other commands that are not recognized
                System.out.println("Invalid command. Please use 'encode: <text>', 'decode: <text>', 'encode_image: <filename>', or 'decode_image: <index>'");
            }
        }
    }

    // Method to encode a text string using Base64
    public static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    // Method to decode a Base64 encoded string
    public static String decode(String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);
    }

    // Method to save a string to a specified file
    public static void saveToFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(text);  // Write the text to the file
            writer.newLine();   // Move to the next line
        } catch (IOException e) {
            // Handle any exceptions that occur during the file writing process
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    // Method to read a string from a specified file
    public static String readFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            // Handle any exceptions that occur during the file reading process
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null;
        }
    }
}
