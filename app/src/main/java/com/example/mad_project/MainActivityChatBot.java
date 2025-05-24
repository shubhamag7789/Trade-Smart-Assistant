package com.example.mad_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityChatBot extends AppCompatActivity {
    private RecyclerView chatRecyclerView;
    private TextInputEditText messageInput;
    private MaterialButton sendButton;
    private ImageButton backButton;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages;
    private GeminiApiService geminiApiService;
    private static final String GEMINI_API_KEY = "AIzaSyDL8ZgETFNYm-4NqVZAv08PJNxoPKcO-sw"; // <-- Replace with your actual API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_bot);

        // Initialize Gemini API service
        geminiApiService = RetrofitClient.getGeminiApiService();

        // Initialize views
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        // Set up back button
        backButton.setOnClickListener(v -> {
            finish(); // Go back to previous activity (Dashboard)
        });

        // Initialize chat
        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(messages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        // Set up send button click listener
        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (message.isEmpty()) return;

        // Add user message to chat
        messages.add(new ChatMessage(message, true));
        chatAdapter.notifyItemInserted(messages.size() - 1);
        chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
        messageInput.setText("");

        // Prepare Gemini API request
        GeminiApiModels.GeminiRequest request = new GeminiApiModels.GeminiRequest(message);

        // Make the API call
        geminiApiService.generateContent(GEMINI_API_KEY, request)
                .enqueue(new Callback<GeminiApiModels.GeminiResponse>() {
                    @Override
                    public void onResponse(Call<GeminiApiModels.GeminiResponse> call, Response<GeminiApiModels.GeminiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String botResponse = response.body().getFirstResponseText();
                            messages.add(new ChatMessage(botResponse, false));
                            chatAdapter.notifyItemInserted(messages.size() - 1);
                            chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
                        } else {
                            String errorMsg = "Sorry, I couldn't process that request. Please try again later.";
                            if (response.errorBody() != null) {
                                try {
                                    errorMsg = response.errorBody().string();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            messages.add(new ChatMessage(errorMsg, false));
                            chatAdapter.notifyItemInserted(messages.size() - 1);
                            chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<GeminiApiModels.GeminiResponse> call, Throwable t) {
                        messages.add(new ChatMessage("Network error. Please check your internet connection and try again.", false));
                        chatAdapter.notifyItemInserted(messages.size() - 1);
                        chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
                    }
                });
    }
}