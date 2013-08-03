package com.example.chatapp;

import java.net.Socket;
import java.io.*;


import android.widget.Button;
import android.widget.EditText;

public class ClientInit extends Thread {
	
	public static final int SERVER_PORT = 4444;
	public static final String SERVER_IP = "76.184.255.47";
	public Socket socket;
	public ChatAppReader chatAppReader;
	public ChatAppWriter chatAppWriter;
	public DataInputStream fromServer;
	public DataOutputStream toServer;
	public String username;
	public ClientSideSocket parent;
	
	public ClientInit(String username, ClientSideSocket parent) {
		
		this.username = username;
		this.parent = parent;
	}
	public void run() {
		try {
			manipUNameList("Connecting to server...");
			socket = new Socket(SERVER_IP, SERVER_PORT);
			manipUNameList("Connection successful!");
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
			chatAppReader = new ChatAppReader(socket, fromServer, this);
			Thread appReader = new Thread(chatAppReader);
			appReader.start();
			chatAppWriter = new ChatAppWriter(toServer, username);
			Thread appWriter = new Thread(chatAppWriter);
			appWriter.start();

		}
		catch (Exception ex) {
			ex.printStackTrace();
			manipUNameList("Unable to connect to host.");
		}
	}
	
	public void send(String str) {
		chatAppWriter.send(str);
	}
	public void manipUNameList(String str) {
		parent.updateTextView.obtainMessage(1, str).sendToTarget();
	}
}
