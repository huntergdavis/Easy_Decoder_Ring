package com.hunterdavis.easydecoderring;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class EasyDecoderRing extends Activity {
	
	   public static final int SALT_LENGTH = 20;
	    public static final int PBE_ITERATION_COUNT = 1000;

	    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
	    private static final String PBE_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";
	    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		// encode button listener
		Button encodeButton = (Button) findViewById(R.id.generatebutton);
		encodeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Boolean Success = encodeText(v.getContext(),true);
			}

		});
		
		// decode button listener
		Button decodeButton = (Button) findViewById(R.id.decodebutton);
		decodeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Boolean Success = encodeText(v.getContext(),false);
			}

		});
        
		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());
    }
    
    public Boolean encodeText(Context context, Boolean encode) {
    	EditText inputText = (EditText) findViewById(R.id.inputText);
    	EditText outputText = (EditText) findViewById(R.id.outputText);
    	EditText cypherText = (EditText) findViewById(R.id.cypherText);
    	
    	String inputString = inputText.getText().toString();
    	String cypherString = cypherText.getText().toString();
    	if((inputString.length()<1)||(cypherString.length()<1))
    	{
    		return false;
    	}
    	
    	if(encode == true)
    	{
    		try {
				String crypto = SimpleCrypto.encrypt(cypherString, inputString);
				outputText.setText(crypto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else
    	{
    		try {
				String clearText = SimpleCrypto.decrypt(cypherString,inputString);
				outputText.setText(clearText);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	return true;
    }   
}