package com.example.stusocket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Chat Room Not Found Exception")
public class ChatRoomNotFoundException extends RuntimeException {
}
