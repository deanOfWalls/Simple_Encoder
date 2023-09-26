package com.deanOfWalls.simple_encoder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

public class EncoderDecoderApp {

    private static final String ENCODED_FILES_DIR = "encoded_files";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        String text;
        String filePath = "encodedText.txt";

        // Ensure the encoded_files directory exists
        Path encodedFilesPath = Paths.get(ENCODED_FILES_DIR);
        if (!Files.exists(encodedFilesPath)) {
            try {
                Files.createDirectory(encodedFilesPath);
            } catch (IOException e) {
                System.out.println("Error creating encoded_files directory: " + e.getMessage());
                return;
            }
        }

        while (true) {
            System.out.print("Enter your command (encode/decode/encode_file/decode_file): ");
            command = scanner.nextLine().trim();

            if (command.startsWith("encode: ")) {
                text = command.substring(8);
                String encodedText = encode(text);
                saveToFile(encodedText, filePath);
                System.out.println("Text encoded and saved to " + filePath);
            } else if (command.startsWith("decode: ")) {
                text = command.substring(8);
                String decodedText = decode(text);
                System.out.println("Decoded text: " + decodedText);
            } else if (command.startsWith("encode_file: ")) {
                String fileName = command.substring(12).trim();
                try {
                    byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
                    String encodedFile = Base64.getEncoder().encodeToString(fileBytes);
                    String encodedFileName = ENCODED_FILES_DIR + "/" + fileName + ".txt";
                    saveToFile(encodedFile, encodedFileName);
                    Files.delete(Paths.get(fileName));
                    System.out.println("File encoded and saved to " + encodedFileName);
                } catch (IOException e) {
                    System.out.println("An error occurred while encoding the file: " + e.getMessage());
                }
            } else if (command.startsWith("decode_file: ")) {
                String encodedFileName = command.substring(12).trim();
                try {
                    String encodedFile = readFromFile(ENCODED_FILES_DIR + "/" + encodedFileName);
                    if (encodedFile == null) {
                        System.out.println("File not found: " + ENCODED_FILES_DIR + "/" + encodedFileName);
                        continue;
                    }
                    
                    // Log the first few characters of the encoded content for debugging
                    System.out.println("Encoded content (first 100 chars): " + encodedFile.substring(0, Math.min(100, encodedFile.length())));
                    
                    byte[] decodedFileBytes = Base64.getDecoder().decode(encodedFile);
                    String outputFileName = encodedFileName.replace(".txt", "");
                    Files.write(Paths.get(outputFileName), decodedFileBytes);
                    System.out.println("File decoded and saved as " + outputFileName);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error during Base64 decoding: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("An error occurred while decoding the file: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid command. Please use 'encode: <text>', 'decode: <text>', 'encode_file: <filename>', or 'decode_file: <filename.txt>'");
            }
        }
    }

    public static String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decode(String encodedText) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedText);
        return new String(decodedBytes);
    }

    public static void saveToFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    public static String readFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null;
        }
    }
}
