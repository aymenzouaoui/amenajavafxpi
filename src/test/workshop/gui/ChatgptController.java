package test.workshop.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChatgptController {

    private static final String API_URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
    private static final String API_KEY = "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"; // Replace with your API key

    @FXML
    private TextField inputQuestion;

    @FXML
    private Text outputAnswer;

    @FXML
    private Button btnSend;

    @FXML
    void onSend() {
        String question = inputQuestion.getText();
        if (!question.isEmpty()) {
            try {
                String answer = getAnswerFromAPI(question);
                outputAnswer.setText(answer);
            } catch (IOException e) {
                e.printStackTrace();
                outputAnswer.setText("Error making API request: " + e.getMessage());
            }
        }
    }

    private String getAnswerFromAPI(String question) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);

        String postData = "{\"prompt\":\"" + question + "\",\"temperature\":0.7,\"max_tokens\":3000,\"top_p\":1,\"frequency_penalty\":0.5,\"presence_penalty\":0}";
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.getBytes());
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP error " + responseCode);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.toString());
        String answer = rootNode.get("choices").get(0).get("text").asText();
        return answer;
    }
}