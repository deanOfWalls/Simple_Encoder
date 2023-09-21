# EncoderDecoderApp

## Overview

The `EncoderDecoderApp` is a simple Java application designed to encode and decode text strings using Base64 encoding. The primary purpose of this application is to hide simple data strings from computer-illiterate people on a shared PC. While this application does not provide robust security measures, it serves as a basic layer of obfuscation for text data.

## Features

- **Encode Text**: Converts a given text string into its Base64 encoded form.
- **Decode Text**: Converts a Base64 encoded string back to its original text form.
- **Save to File**: Saves the encoded text to a file named `encodedText.txt`.
- **Read from File**: Although not implemented in the main loop, a method exists for reading text from a file.

## How to Use

1. Run the application.
2. You will be prompted to enter a command. You have two options:
    - To encode text, type `encode: <your_text_here>` and press Enter.
    - To decode text, type `decode: <your_encoded_text_here>` and press Enter.
3. The application will either display the decoded text or save the encoded text to a file, depending on your command.

## Code Structure

- `main(String[] args)`: The main method that handles user input and calls appropriate methods based on the command.
- `encode(String text)`: A static method that takes a text string and returns its Base64 encoded form.
- `decode(String encodedText)`: A static method that takes a Base64 encoded string and returns its decoded form.
- `saveToFile(String text, String filePath)`: A static method that saves a given text string to a specified file path.
- `readFromFile(String filePath)`: A static method that reads text from a specified file path and returns it.

## Limitations

- The application uses Base64 encoding, which is not a secure method of encryption. It's only meant for basic obfuscation.
- The encoded text is saved to a file named `encodedText.txt`, which could be easily accessed and decoded.

## Future Enhancements

- Implement a more secure method of encryption.
- Add the ability to specify a custom file name for saving the encoded text.
- Implement a user interface for easier interaction.

## Dependencies

- Java Standard Library

**Note**: This application is intended for educational purposes and should not be used as a secure method of storing sensitive information.
