import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ChatbotApp {

    // ===== NLP PROCESSOR =====
    static class NLPProcessor {
        public static String process(String input) {
            if (input == null) return "";
            input = input.toLowerCase();
            input = input.replaceAll("[^a-zA-Z0-9 ]", "");
            return input.trim();
        }
    }

    // ===== CHATBOT LOGIC =====
    static class Chatbot {
        private Map<String, String> knowledgeBase;

        public Chatbot() {
            knowledgeBase = new HashMap<>();
            trainBot();
        }

        private void trainBot() {
            knowledgeBase.put("hello", "Hi! How can I help you?");
            knowledgeBase.put("hi", "Hello!");
            knowledgeBase.put("your name", "I am a Java AI Chatbot.");
            knowledgeBase.put("how are you", "I am working perfectly!");
            knowledgeBase.put("what is java", "Java is a programming language.");
            knowledgeBase.put("what is ai", "AI means Artificial Intelligence.");
            knowledgeBase.put("internship", "This chatbot is created for internship project.");
            knowledgeBase.put("bye", "Goodbye! Have a great day!");
        }

        public String getResponse(String input) {
            input = NLPProcessor.process(input);

            for (String key : knowledgeBase.keySet()) {
                if (input.contains(key)) {
                    return knowledgeBase.get(key);
                }
            }

            return "Sorry, I don't understand. Try asking something else!";
        }
    }

    // ===== GUI =====
    private Chatbot bot;

    public ChatbotApp() {
        bot = new Chatbot();

        JFrame frame = new JFrame("AI Chatbot");
        JTextArea chatArea = new JTextArea();
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.add(panel, BorderLayout.SOUTH);

        // Button action
        sendButton.addActionListener(e -> sendMessage(chatArea, inputField));

        // Enter key action
        inputField.addActionListener(e -> sendMessage(chatArea, inputField));

        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void sendMessage(JTextArea chatArea, JTextField inputField) {
        String userText = inputField.getText();

        if (userText.trim().isEmpty()) return;

        chatArea.append("You: " + userText + "\n");

        String response = bot.getResponse(userText);
        chatArea.append("Bot: " + response + "\n\n");

        inputField.setText("");
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {
        new ChatbotApp();
    }
}