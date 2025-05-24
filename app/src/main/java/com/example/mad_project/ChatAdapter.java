package com.example.mad_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {
    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        holder.messageText.setText(message.getMessage());
    
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.messageText.getLayoutParams();
    
        if (message.isUser()) {
            // User: right, purple
            params.gravity = Gravity.END;
            holder.messageText.setBackgroundResource(R.drawable.bg_message_user);
            holder.messageText.setTextColor(Color.WHITE);
        } else {
            // Bot: left, gray
            params.gravity = Gravity.START;
            holder.messageText.setBackgroundResource(R.drawable.bg_message_bot);
            holder.messageText.setTextColor(Color.BLACK);
        }
        holder.messageText.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        MessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }
    }
}