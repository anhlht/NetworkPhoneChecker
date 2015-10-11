package com.tumblr.pioggia.networkphonechecker;

import java.util.Currency;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	Button btnChonDanhDa,btnChonNhaMang;
	EditText etSDT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etSDT = (EditText)findViewById(R.id.et_sdt);
		
		btnChonDanhDa = (Button)findViewById(R.id.btnchondanhba);
		btnChonDanhDa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
				startActivityForResult(intent,1);
			}
		});
		
		btnChonNhaMang = (Button)findViewById(R.id.btnnhamang);
		btnChonNhaMang.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this,ProviderActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch(requestCode)
		{
		case 1: 
			if(resultCode == RESULT_OK)
			{
				Uri contactData = data.getData();
				Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
				if (cursor.moveToFirst())
                {
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                       // Log.d("mainActivityResult", cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) + "");
                        etSDT.setText(number);
                }
				cursor.close();
			}
			break;
		}
	}
}
