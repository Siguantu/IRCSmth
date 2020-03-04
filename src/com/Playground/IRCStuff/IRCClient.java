package com.Playground.IRCStuff;

import java.io.*;

public class IRCClient extends IRCCore {
    private String nick;
    private String password;
    private BufferedWriter Writer;
    private BufferedReader Reader;

    public IRCClient(String server, int port) throws IOException {
        super(server, port);
    }
    public void IRCLogin(String nick, String user, String password) throws IOException {
        this.nick = nick;
        this.password = password;
        this.Writer = super.getIRCWriter();
        this.Reader = super.getIRCReader();
        Writer.write("PASS " + password + "\r\n");
        Writer.write("NICK " + nick + "\r\n");
        Writer.write("USER" + user + "\r\n");
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
