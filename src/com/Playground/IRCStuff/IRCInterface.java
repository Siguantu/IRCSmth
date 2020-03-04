package com.Playground.IRCStuff;

import java.io.IOException;

public interface IRCInterface {
    public int IRCDefaultPort = 6667;
    void IRCJoinChannel(String channel) throws IOException;
    void IRCSendMessage(String message) throws IOException;
    void IRCSendChannelMessage(String message, String channel) throws IOException;
    void IRCDisconnect() throws IOException;
    void IRCChangeServer(String server, int port) throws IOException;
}
