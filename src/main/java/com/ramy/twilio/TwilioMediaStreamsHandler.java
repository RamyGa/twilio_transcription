package com.ramy.twilio;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TwilioMediaStreamsHandler extends AbstractWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(TwilioMediaStreamsHandler.class);

    private Map<WebSocketSession, GoogleTextToSpeechService> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session, new GoogleTextToSpeechService(
                transcription -> {
                    System.out.println("Transcription: " + transcription);
                }
        ));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        sessions.get(session).send(textMessage.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.get(session).close();
        sessions.remove(session);
    }
}
