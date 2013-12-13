package unilim.projet.ponk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity which host the GameView and the UDP Server
 * @author Nivtitif
 *
 */
public class GameActivity extends Activity {

	public static boolean bpause = false ;
	public static boolean bfini = false ;
	public ServerUDP t;
	private boolean multi = false;
	
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game);
		Intent i = getIntent();
		
		if (i.getExtras().getString("IP") != null)
		{
			this.multi = true;
			t = new ServerUDP(i.getExtras().getString("IP"));
			t.setRunning(true);
	        t.start();
	        t.tryToConnect();
		}
		
		GameModel.isIA = i.getExtras().getBoolean("IA");
	}
	
	/**
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.v("activity","Game Activity on Pause");
		if(multi)
		{
	        t.setRunning(false);
	        t.interrupt();
		}
	}
	
	
	public void pause(View w){
		bpause = !bpause ; 
		Log.v("pause", ""+bpause);
	}
	
	/**
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if(multi)
		{
			t.setRunning(false);
		    t.interrupt();
		}

	}
	
	/**
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume()
	{
		super.onResume();
	}
}
