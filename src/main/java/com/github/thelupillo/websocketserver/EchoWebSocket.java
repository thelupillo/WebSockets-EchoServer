package com.github.thelupillo.websocketserver;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@WebSocket
public class EchoWebSocket {

    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
    //private static final Set<Session> sessions = new HashSet<>();

    @OnWebSocketConnect
    public void onConnect(Session session) throws IOException {
        sessions.add(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) throws IOException {
        sessions.remove(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException {
        System.err.println("Got Message: " + message);
        //session.getRemote().sendString("Hi: " + message);
        for(Session s : sessions) {
        	s.getRemote().sendString(session.getRemoteAddress().getAddress().toString() + ": " + message);
        }
    }

}
