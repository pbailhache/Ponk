package unilim.projet.ponk;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends Activity 
{

	private Button start, quit,settings, startIA;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		start = (Button)findViewById(R.id.startPalyer);
		startIA = (Button)findViewById(R.id.startIA);
		quit = (Button)findViewById(R.id.quit);
		settings = (Button)findViewById(R.id.settings);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void start(View v)
	{
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("IA", false);
		intent.putExtra("IP", ((TextView) findViewById(R.id.ipText)).getText().toString());
		startActivity(intent);
	}
	
	public void startIA(View v){
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("IA", true);
		startActivity(intent);
	}
	
	
	public void quit(View v)
	{
		finish();          
	}
	
	public void settings(View v)
	{
		Intent intent = new Intent(this, SettingsMenu.class);
		startActivity(intent);
	}

}
