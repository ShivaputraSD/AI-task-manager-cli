import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AIService {

    private static final String API_KEY = System.getenv("GROQ_API_KEY");
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";

    public String generateSubTasks(String mainGoal) {
        String prompt = "You are an Agile Project Manager. Breakdown this project goal into 3 concrete technical sub-tasks: '" 
                + mainGoal + "'. "
                + "Return ONLY 3 lines. Each line MUST follow this exact format with pipe separators: "
                + "Task Title | Short Description | Priority (HIGH, MEDIUM, or LOW). "
                + "Do not include numbers, bullet points, or intro/outro text.";

        String jsonPayload = "{"
                + "\"model\": \"llama-3.3-70b-versatile\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"" + escapeJson(prompt) + "\"}]"
                + "}";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return extractContentFromJson(response.body());
            } else {
                System.err.println(" AI API Error Code: " + response.statusCode());
                System.err.println("Response: " + response.body());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractContentFromJson(String json) {
        int contentIndex = json.indexOf("\"content\":\"");
        if (contentIndex == -1) return "";
        
        int start = contentIndex + 11;
        int end = json.indexOf("\"}", start);
        if (end == -1) end = json.indexOf("\",\"role\"", start);

        String text = json.substring(start, end);
        return text.replace("\\n", "\n").replace("\\\"", "\"");
    }

    private String escapeJson(String input) {
        return input.replace("\"", "\\\"");
    }
}