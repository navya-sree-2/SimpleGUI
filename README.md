# Text Editor with Automatic Word Count

This is a simple Java Swing application that functions as a basic text editor. It allows users to open, edit, and save text files. Additionally, it automatically updates the word count as the user types in real-time.

## Features

- **Open and Save Files**: Users can open and save `.txt` files using the file chooser.
- **Real-Time Word Count**: The word count updates automatically whenever the text is modified in the notepad.
- **Simple UI**: The application has a straightforward user interface with buttons for opening and saving files and a label displaying the current word count.

## How It Works

1. **File Operations**: 
   - The application uses a `JFileChooser` to open and save `.txt` files.
   - When a file is opened, its contents are read and displayed in the text area.
   - When saving, the current text in the text area is written to the selected file.

2. **Real-Time Word Count**:
   - The word count is updated using a `DocumentListener` that listens for changes in the `JTextArea`.
   - Whenever text is inserted, removed, or changed, the listener calculates the number of words and updates the display.


