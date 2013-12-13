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


/**
 * First Activity displayed to user. Contains all options for the users.
 * @author Nivtitif
 */
public class MainMenu extends Activity 
{

	private Button start, quit,settings, startIA;
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		start = (Button)findViewById(R.id.startPalyer);
		startIA = (Button)findViewById(R.id.startIA);
		quit = (Button)findViewById(R.id.quit);
		settings = (Button)findViewById(R.id.settings);
		
	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	/**
	 * @param v
	 */
	public void start(View v)
	{
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("IA", false);
		intent.putExtra("IP", ((TextView) findViewById(R.id.ipText)).getText().toString());
		startActivity(intent);
	}
	
	/**
	 * @param v
	 */
	public void startIA(View v){
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("IA", true);
		startActivity(intent);
	}
	
	
	/**
	 * @param v
	 */
	public void quit(View v)
	{
		finish();          
	}
	
	/**
	 * @param v
	 */
	public void settings(View v)
	{
		Intent intent = new Intent(this, SettingsMenu.class);
		startActivity(intent);
	}
	
	/**
	 * @param v
	 */
	public void help(View v)
	{
		Intent intent = new Intent(this, MenuHelp.class);
		startActivity(intent);
	}
	
}
