package com.ramy.twilio;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Slf4j
public class TwilioMediaStreamsHandler extends AbstractWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(TwilioMediaStreamsHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //when the Websocket connects this method will be called
        logger.info("New connection has been established");
        System.out.println("New connection has been established");


    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //this method will be called repeatedly every time there is a new message
        logger.info("Message received, length is "+ message.getPayloadLength());
        System.out.println("Message received, length is "+ message.getPayloadLength());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //this method will be called when the connection is closed
        System.out.println("Connection closed");
    }
}
