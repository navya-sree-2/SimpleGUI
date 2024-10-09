package FileSaver;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JButton openButton, saveButton;
    private JLabel wordCountLabel;

    public TextEditor() {
        super("Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.PAGE_START);

        openButton = new JButton("Open");
        openButton.addActionListener(e -> openFile());
        buttonPanel.add(openButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveFile());
        buttonPanel.add(saveButton);

        wordCountLabel = new JLabel("Words Count: 0");
        getContentPane().add(wordCountLabel, BorderLayout.PAGE_END);

        // Add document listener to update word count as text changes
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount();
            }
        });

        setVisible(true);
    }

    // Method to update word count
    private void updateWordCount() {
        String content = textArea.getText();
        int count = 0;
        if(!content.isEmpty()) {
            String[] words = content.trim().split("\\s+");
            count = words.length;
        }
        wordCountLabel.setText("Words Count: " + count);
    }

    // Method to open a file
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnVal = fileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader reader = new FileReader(fileChooser.getSelectedFile());
                BufferedReader br = new BufferedReader(reader);
                String line;
                StringBuilder sb = new StringBuilder();
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                textArea.setText(sb.toString());
                br.close();
                reader.close();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Method to save a file
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        int returnVal = fileChooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                FileWriter writer = new FileWriter(fileChooser.getSelectedFile());
                writer.write(textArea.getText());
                writer.close();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}
