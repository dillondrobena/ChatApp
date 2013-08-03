package com.example.chatapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ClientSideSocket extends Activity {
	
	public ClientInit init;
	public EditText editText, userNameList;
	public String[] tempAddress;
	public ArrayList<String> uNL = new ArrayList<String>();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_side_socket);
		editText = (EditText) findViewById(R.id.maintext_area);
		userNameList = (EditText) findViewById(R.id.username_list);
		Intent intent = getIntent();
		String username = intent.getStringExtra(MainActivity.CLIENT_USERNAME);
		init = new ClientInit(username, this);
		init.start();
	}
	
	public void _onClick(View v) {
		EditText input = (EditText) findViewById(R.id.editText2);
		String text = input.getText().toString();
		input.setText("");
		init.send(text);
	}
	
	public final Handler updateTextView = new Handler(){
		   @Override
		   public void handleMessage(Message msg)
		   {
		      if(msg.what == 1){
		          updateUI((String)msg.obj);
		      }
		   }
		};
	
	public void updateUI(String input) {
		if (input.contains("@protocolInitxxsz")) {
			tempAddress = input.split("@");
			if (!userNameList.getText().toString().contains(tempAddress[0])) {
				userNameList.append(tempAddress[0] + "\n");
				uNL.add(tempAddress[0]);
			}
		}
		else if (input.contains("@protocolInitxxsx")) {
			tempAddress = input.split("@");
			uNL.remove(tempAddress[0]);
			userNameList.setText("");
			for (String i : uNL) {
				userNameList.append(i + "\n");
			}
		}
		else {
		editText.append(input + "\n");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_side_socket, menu);
		return true;
	}

}
