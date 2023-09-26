# EncoderDecoderApp

## Overview

The `EncoderDecoderApp` is a Java application designed to encode and decode text strings and files using Base64 encoding. While primarily intended for obfuscating simple data strings from computer-illiterate users on a shared PC, it now also offers basic file encoding and decoding capabilities.

## Features

- **Encode Text**: Converts a given text string into its Base64 encoded form.
- **Decode Text**: Converts a Base64 encoded string back to its original text form.
- **Encode File**: Converts a file into its Base64 encoded form and saves it to a separate file within the `encoded_files` directory.
- **Decode File**: Converts a Base64 encoded file string back to its original form.
- **Save to File**: Saves the encoded text to a file named `encodedText.txt` and encoded files to individual `.txt` files within the `encoded_files` directory.
- **Read from File**: Although not implemented in the main loop, a method exists for reading text from a file.

## How to Use

1. Run the application.
2. You will be prompted to enter a command. You have four options:
    - To encode text, type `encode: <your_text_here>` and press Enter.
    - To decode text, type `decode: <your_encoded_text_here>` and press Enter.
    - To encode a file, type `encode_file: <filename>` and press Enter.
    - To decode a file, type `decode_file: <filename.txt>` and press Enter.
3. Depending on your command, the application will either display the decoded text, save the encoded text or file to a directory, or decode and save the file.

## Code Structure

- `main(String[] args)`: The main method that handles user input and calls appropriate methods based on the command.
- `encode(String text)`: A static method that takes a text string and returns its Base64 encoded form.
- `decode(String encodedText)`: A static method that takes a Base64 encoded string and returns its decoded form.
- `saveToFile(String text, String filePath)`: A static method that saves a given text string or encoded file to a specified file path.
- `readFromFile(String filePath)`: A static method that reads text from a specified file path and returns it.

## Limitations

- The application uses Base64 encoding, which is not a secure method of encryption. It's only meant for basic obfuscation.
- Encoded text and files are saved to easily accessible files and directories, which could be decoded if accessed.

## Future Enhancements

- Implement a more secure method of encryption.
- Add the ability to specify custom file names for saving encoded data.
- Implement a user interface for easier interaction.

## Dependencies

- Java Standard Library

**Note**: This application is intended for educational purposes and should not be used as a secure method of storing sensitive information.
