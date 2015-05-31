package com.example.androidclientsl;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class slide3 extends Activity {


// EditText editTextAddress, editTextPort;

 
 EditText welcomeMsg;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  requestWindowFeature(Window.FEATURE_NO_TITLE);
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
  setContentView(R.layout.slide3);

//  editTextAddress = (EditText) findViewById(R.id.address);
 // editTextPort = (EditText) findViewById(R.id.port);
 // ImageView  buttonConnect = (ImageView) findViewById(R.id.imageView33);
//  buttonClear = (Button) findViewById(R.id.clear);
//  textResponse = (TextView) findViewById(R.id.response);
  
  welcomeMsg = (EditText)findViewById(R.id.msgtext2);
  
 ImageView buttonClear = (ImageView) findViewById(R.id.imageView3);

 buttonClear.setOnClickListener(buttonConnectOnClickListener);
/*
  buttonClear.setOnClickListener(new OnClickListener() {

   @Override
   public void onClick(View v) {
    textResponse.setText("");
   }
  });*/
 }

 OnClickListener buttonConnectOnClickListener = new OnClickListener() {

  @Override
  public void onClick(View arg0) {
   
   String tMsg = welcomeMsg.getText().toString();
   if(tMsg.equals("")){
    tMsg = null;
    Toast.makeText(slide3.this, "No Welcome Msg sent", Toast.LENGTH_SHORT).show();
   }
   
   MyClientTask myClientTask = new MyClientTask("192.168.0.102", 8080, tMsg);
   myClientTask.execute();
  }
 };

 public class MyClientTask extends AsyncTask<Void, Void, Void> {

  String dstAddress;
  int dstPort;
  String response = "";
  String msgToServer;

  MyClientTask(String addr, int port, String msgTo) {
   dstAddress = addr;
   dstPort = port;
   msgToServer = msgTo;
  }

  @Override
  protected Void doInBackground(Void... arg0) {

   Socket socket = null;
   DataOutputStream dataOutputStream = null;
   DataInputStream dataInputStream = null;

   try {
    socket = new Socket(dstAddress, dstPort);
    dataOutputStream = new DataOutputStream(
      socket.getOutputStream());
    dataInputStream = new DataInputStream(socket.getInputStream());
    
    if(msgToServer != null){
     dataOutputStream.writeUTF(msgToServer);
    }
    
    response = dataInputStream.readUTF();

   } catch (UnknownHostException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    response = "UnknownHostException: " + e.toString();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    response = "IOException: " + e.toString();
   } finally {
    if (socket != null) {
     try {
      socket.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }

    if (dataOutputStream != null) {
     try {
      dataOutputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }

    if (dataInputStream != null) {
     try {
      dataInputStream.close();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
   }
   return null;
  }

  @Override
  protected void onPostExecute(Void result) {
 //  textResponse.setText(response);
   super.onPostExecute(result);
  }

 }

}

