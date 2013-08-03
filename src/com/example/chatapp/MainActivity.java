package com.example.chatapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

	public ClientSideSocket clientSideSocket;
	public final static String CLIENT_USERNAME = "com.example.ChatApp.client_username";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClick(View view) {
    	Intent intent = new Intent(this, ClientSideSocket.class);
    	EditText editText = (EditText) findViewById(R.id.maintext_area);
    	String userName = editText.getText().toString();
    	intent.putExtra(CLIENT_USERNAME, userName);
    	startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
