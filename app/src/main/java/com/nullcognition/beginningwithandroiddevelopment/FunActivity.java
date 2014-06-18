package com.nullcognition.beginningwithandroiddevelopment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nullcognition.beginningwithandroiddevelopment.R;

import java.util.ArrayList;

public class FunActivity extends Activity {

	EditText et;
	ListView lv;
	Button   b;

	ArrayList<String>    todoList;
	ArrayAdapter<String> aa;

	String temp = "";


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fun);
		Bundle ex = getIntent().getExtras();
		int sbVal = ex.getInt("sbVal");

		TextView tv = (TextView) findViewById(R.id.hello);
		tv.setText(("Seekbar's value was: " + (CharSequence) Integer.toString(sbVal)));

		todoList = new ArrayList<String>();
		aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoList );

		et = (EditText) findViewById(R.id.enter);

		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(aa);

		b = (Button) findViewById(R.id.button);

		b.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				addItem(et.getText().toString());
			}
		});

		b.setOnLongClickListener(new View.OnLongClickListener(){
			@Override
			public boolean onLongClick(View v){
				int s = todoList.size();
				if(s > 0){
					temp = et.getText().toString();
					et.setText("");
					todoList.remove(s-1);}
				aa.notifyDataSetChanged();
				return false;
			}
		});

	}

	private void addItem(String item){
		if(!item.isEmpty()){
			todoList.add(item);
			aa.notifyDataSetChanged();
			et.setText("");

		}
		else et.setText(temp);
		temp = "";
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
	        case R.id.action_settings:{
		        return true;
	        }
	        case R.id.action_getit:{
		        return true;
	        }
	        case R.id.action_profile:{
		        return true;
	        }
	        case R.id.action_new:{
		        return true;
	        }

        }
        return super.onOptionsItemSelected(item);
    }
}
