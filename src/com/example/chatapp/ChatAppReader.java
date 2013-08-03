package com.example.chatapp;

import java.net.*;
import java.io.*;

import android.widget.EditText;


public class ChatAppReader extends Thread {
	public String input;
	private Socket socket;
	private DataInputStream fromServer;
	private ClientInit parent;
	
	public ChatAppReader(Socket socket, DataInputStream fromServer, ClientInit parent){
		this.fromServer = fromServer;
		this.parent = parent;
		this.socket = socket;
	}
	public void run() {
		while (socket.isConnected()){
			try {
				input = fromServer.readUTF();
				parent.manipUNameList(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
