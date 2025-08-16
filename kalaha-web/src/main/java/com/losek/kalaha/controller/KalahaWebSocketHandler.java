package com.losek.kalaha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class KalahaWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(KalahaWebSocketHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
        logger.info("New WebSocket connection established: {}", session.getId());
        sendInitialState(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("{} {}", session.getId(), message.getPayload());

        // Parse incoming move
        var move = objectMapper.readTree(message.getPayload());
        var pitIndex = move.get("pitIndex").asInt();

        // TODO: Handle game logic here

        // For now, just echo back the initial state
        sendInitialState(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
        logger.info("WebSocket connection closed: {}", session.getId());
    }

    private void sendInitialState(WebSocketSession session) {
        try {
            String initialState = """
                {
                    "player1Pits": [4,4,4,4,4,4],
                    "player2Pits": [4,4,4,4,4,4],
                    "player1Store": 0,
                    "player2Store": 0
                }
                """;
            session.sendMessage(new TextMessage(initialState));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
