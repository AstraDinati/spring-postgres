package com.pet.study.constants;

public class ErrorMessages {

    public static final String ACCESS_DENIED = "Access denied. You do not have permission to perform this action.";
    public static final String NOT_ENOUGH_FUNDS = "Not enough funds on user balance.";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String CHAT_NOT_FOUND = "Chat not found";
    public static final String EMAIL_ALREADY_TAKEN = "Email already taken!";
    public static final String EMAIL_NOT_FOUND = "Email not found";
    public static final String MESSAGE_NOT_FOUND = "Message not found";
    public static final String SENDER_NOT_FOUND = "Sender not found";
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String SUBCATEGORY_NOT_FOUND = "Subcategory not found";
    public static final String INVALID_GOOGLE_ACCESS_TOKEN = "Invalid Google access token.";
    public static final String GOOGLE_USER_INFO_RETRIEVAL_FAILED = "Failed to retrieve user information from Google.";
    public static final String REFRESH_TOKEN_NOT_IN_DB = "Refresh token is not in database.";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access attempt to get all chats.";
    public static final String UNEXPECTED_ERROR = "Unexpected error occurred.";
    public static final String QDRANT_SAVE_MESSAGE_ERROR = "Failed to save message to Qdrant.";
    public static final String QDRANT_SAVE_EXPERT_MESSAGE_ERROR = "Failed to save expert message to Qdrant.";

    private ErrorMessages() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}