package unilim.projet.ponk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity 
{

	private Button start, quit,settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		start = (Button)findViewById(R.id.start);
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
