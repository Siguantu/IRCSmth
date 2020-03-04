package com.Playground.IRCStuff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IRCTwitch extends IRCClient {
    private String nick;
    private String OAuth;
    private BufferedWriter Writer;
    private BufferedReader Reader;

    public IRCTwitch(String server, int port) throws IOException {
        super(server, port);
    }
    public void IRCLogin(String nick, String OAuth) throws IOException {
        this.nick = nick;
        this.OAuth = OAuth;
        this.Writer = super.getIRCWriter();
        this.Reader = super.getIRCReader();
        Writer.write("PASS " + OAuth + "\r\n");
        Writer.write("NICK " + nick + "\r\n");
        Writer.flush();
        String line;
        System.out.println("Connecting\r\n");
        while ((line = Reader.readLine()) != null) {
            System.out.println(line);
            if (line.contains("004")) {
                System.out.println("Successful connection\r\n");
                break;
            }
            else if (line.contains("433")) {
                System.out.println("Nickname is already in use.\r\n");
                return;
            }
        }
    }
}