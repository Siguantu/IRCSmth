package com.Playground.IRCStuff;

import java.io.*;
import java.net.Socket;

public class IRCCore implements IRCInterface {
    private String server;
    private String channel;
    private int port;
    private Socket clientSocket;
    private BufferedWriter IRCWriter;
    private BufferedReader IRCReader;

    IRCCore(String server, int port) throws IOException {
        this.server = server;
        this.port = port;
        init(server, port);
    }
    private void init(String server, int port) throws IOException {
        this.clientSocket = new Socket(server, port);
        this.IRCWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.IRCReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    @Override
    public void IRCJoinChannel(String channel) throws IOException {
        if(!channel.startsWith("#")) {
            System.out.println("Channel name has to start with #\r\n");
            return;
        }
        this.channel = channel;
        IRCWriter.write("JOIN " + channel + "\r\n");
        IRCWriter.flush();
    }

    @Override
    public void IRCSendMessage(String message) throws IOException {
        IRCWriter.write(message + "\r\n");
        IRCWriter.flush();
    }

    @Override
    public void IRCSendChannelMessage(String message, String channel) throws IOException {
        if (!channel.startsWith("#")) {
            System.out.println("Channel name has to start with #\r\n");
            return;
        }
        IRCWriter.write("PRIVMSG " + channel + " :" + message + "\r\n");
        IRCWriter.flush();
    }

    @Override
    public void IRCDisconnect() throws IOException {
        IRCSendMessage("PART " + channel);
    }

    @Override
    public void IRCChangeServer(String server, int port) throws IOException {
        IRCSendMessage("PART " + channel);
        init(server, port);
    }
    public String getServer() {
        return this.server;
    }
    public String getChannel() {
        return this.channel;
    }
    public BufferedReader getIRCReader() {
        return IRCReader;
    }
    public BufferedWriter getIRCWriter() {
        return IRCWriter;
    }
}

