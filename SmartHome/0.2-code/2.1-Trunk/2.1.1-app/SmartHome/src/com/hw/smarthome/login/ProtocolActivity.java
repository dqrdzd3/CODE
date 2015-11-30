package com.hw.smarthome.login;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class ProtocolActivity extends Activity {

	private BootstrapButton agree,disagree;
	private WebView proView;

	public static final int AGREE = 3;
	public static final int DISAGREE = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.protocol_layout);
		agree = (BootstrapButton)findViewById(R.id.agree);
		disagree = (BootstrapButton)findViewById(R.id.disagree);
		proView = (WebView)findViewById(R.id.webView1);
		agree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				   Intent i = new Intent(ProtocolActivity.this, RegActivity.class);
				   ProtocolActivity.this.setResult(AGREE, i); 
				   ProtocolActivity.this.finish();  
			}
		});
		disagree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ProtocolActivity.this, RegActivity.class);
				   ProtocolActivity.this.setResult(DISAGREE, i); 
				   ProtocolActivity.this.finish();  
			}
		});
		proView.loadUrl("file:///android_asset/protocol.html"); 

	}
}
