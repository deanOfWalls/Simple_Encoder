package com.deanOfWalls.simple_encoder;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncoderDecoderApp {

    private static final String ENCODED_FILES_DIR = "encoded_files";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        String filePath = "encodedText.bin";

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
                String text = command.substring(8);
                saveToFile(text.getBytes(), filePath);
                System.out.println("Text encoded and saved to " + filePath);
            } else if (command.startsWith("decode: ")) {
                byte[] decodedBytes = readFromFile(command.substring(8));
                System.out.println("Decoded text: " + new String(decodedBytes));
            } else if (command.startsWith("encode_file: ")) {
                String fileName = command.substring(12).trim();
                try {
                    byte[] fileBytes = Files.readAllBytes(Paths.get(fileName));
                    String encodedFileName = ENCODED_FILES_DIR + "/" + fileName + ".bin";
                    saveToFile(fileBytes, encodedFileName);
                    Files.delete(Paths.get(fileName));
                    System.out.println("File encoded and saved to " + encodedFileName);
                } catch (IOException e) {
                    System.out.println("An error occurred while encoding the file: " + e.getMessage());
                }
            } else if (command.startsWith("decode_file: ")) {
                String encodedFileName = command.substring(12).trim();
                try {
                    byte[] decodedFileBytes = readFromFile(ENCODED_FILES_DIR + "/" + encodedFileName);
                    String outputFileName = encodedFileName.replace(".bin", "");
                    Files.write(Paths.get(outputFileName), decodedFileBytes);
                    System.out.println("File decoded and saved as " + outputFileName);
                } catch (IOException e) {
                    System.out.println("An error occurred while decoding the file: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid command. Please use 'encode: <text>', 'decode: <filename.bin>', 'encode_file: <filename>', or 'decode_file: <filename.bin>'");
            }
        }
    }

    public static void saveToFile(byte[] data, String filePath) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bos.write(data);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    public static byte[] readFromFile(String filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            return bis.readAllBytes();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null;
        }
    }
}
