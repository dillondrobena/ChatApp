package com.example.chatapp;


import java.io.*;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChatAppWriter extends Thread {

	private DataOutputStream toServer;
	private String userName;
	
	public ChatAppWriter(DataOutputStream toServer, String userName){
		this.toServer = toServer;
		this.userName = userName;
	}
	
	public void run() {
		try {
			toServer.writeUTF(userName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String text) {
		// TODO Auto-generated method stub
		try {
			toServer.writeUTF(text + "@protocolxxsy" + userName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
