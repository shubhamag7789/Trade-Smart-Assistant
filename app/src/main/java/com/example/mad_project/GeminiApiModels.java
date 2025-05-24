package com.example.mad_project;

import java.util.Collections;
import java.util.List;

public class GeminiApiModels {
    public static class GeminiRequest {
        public List<Content> contents;
        public GeminiRequest(String prompt) {
            this.contents = Collections.singletonList(new Content(prompt));
        }
        public static class Content {
            public List<Part> parts;
            public Content(String text) {
                this.parts = Collections.singletonList(new Part(text));
            }
        }
        public static class Part {
            public String text;
            public Part(String text) { this.text = text; }
        }
    }

    public static class GeminiResponse {
        public List<Candidate> candidates;
        public static class Candidate {
            public Content content;
        }
        public static class Content {
            public List<Part> parts;
        }
        public static class Part {
            public String text;
        }

        // Helper to get the first response text
        public String getFirstResponseText() {
            if (candidates != null && !candidates.isEmpty()) {
                Candidate candidate = candidates.get(0);
                if (candidate.content != null && candidate.content.parts != null && !candidate.content.parts.isEmpty()) {
                    return candidate.content.parts.get(0).text;
                }
            }
            return "No response from Gemini.";
        }
    }
}