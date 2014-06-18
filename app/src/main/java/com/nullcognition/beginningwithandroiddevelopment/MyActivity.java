package com.nullcognition.beginningwithandroiddevelopment;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;


public class MyActivity extends Activity{

	SeekBar sb;
	int sbVal = 0;
	ToggleButton tb;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		ll = (LinearLayout) findViewById(R.id.linLay);
		sb = (SeekBar) findViewById(R.id.seekBar);
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar){

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar){
				sbVal = seekBar.getProgress();
			}
		});

		tb = (ToggleButton)findViewById(R.id.toggleButton);
		tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
				if(isChecked) bigNotification();
				else updateNotification();
			}
		});
	}

	boolean isBlue = true;
	LinearLayout ll;

	public void changeBack(View v){
		if(isBlue){
			ll.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
			isBlue = false;
		}
		else{
			isBlue = true;
			ll.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
		}

		if(v == findViewById(R.id.button)){
			Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
			startActivity(i);
		}
		else if(v == findViewById(R.id.switch1)){
			sb = (SeekBar) findViewById(R.id.seekBar);
			Intent i = new Intent(this,FunActivity.class);
			i.putExtra("sbVal",sbVal);
			startActivity(i);
		}
	}

	int mId = 1; // if for notification

	NotificationCompat.Builder mBuilder;
	NotificationManager mNotificationManager;

	public void bigNotification(){
		mBuilder = new NotificationCompat.Builder(this).setSmallIcon(android.R.drawable.btn_dialog).setContentTitle("My notification").setContentText("Hello World!").setTicker("Notification").setAutoCancel(true);
		Intent resultIntent = new Intent(this,MyActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

		stackBuilder.addParentStack(MyActivity.class);

		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.notify(mId,mBuilder.build());

	}

	public void updateNotification(){
		mNotificationManager =
				(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated
		int notifyID = 1;
		mBuilder = new NotificationCompat.Builder(this)
				.setContentTitle("New Message")
				.setContentText("You've received new messages.")
				.setSmallIcon(android.R.drawable.btn_star_big_on);
		int numMessages = 0;
// Start of a loop that processes data and then notifies the user
		mBuilder.setContentText("More infor")
		              .setNumber(++numMessages);
		// Because the ID remains unchanged, the existing notification is
		// updated.
		mNotificationManager.notify(
				notifyID,
				mBuilder.build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
