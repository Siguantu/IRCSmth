package com.Playground.test;

import com.Playground.IRCStuff.IRCInterface;
import com.Playground.IRCStuff.IRCTwitch;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        IRCTwitch client = new IRCTwitch("irc.chat.twitch.tv", IRCInterface.IRCDefaultPort);
        client.IRCLogin("Name", "oauth:YourOAuth");
        client.IRCJoinChannel("#channelname");
        client.IRCSendChannelMessage("Test1", "#channelname");
        client.IRCSendChannelMessage("Test2", "#channelname");
        client.IRCDisconnect();
    }
}