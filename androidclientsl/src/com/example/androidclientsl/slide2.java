package com.example.androidclientsl;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;  



public class slide2  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.slide2);
             
        ImageView  but1 = (ImageView) findViewById(R.id.imageView222sp1);
        
        but1.setOnClickListener(new OnClickListener() {          
            @Override
            public void onClick(View v) {
          // 	 Log.e("bbbbbbbbbbbb","bbbbbbbbbbbbbbbbbbbbbbbb");
           //	Toast.makeText(MainActivity.this,"Verladung...",Toast.LENGTH_LONG).show();
           	 startActivity( new Intent(slide2.this,slide3.class));
        
                }   
            });
            
      
    }
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
      
}

