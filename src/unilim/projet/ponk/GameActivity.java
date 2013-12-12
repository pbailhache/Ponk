package unilim.projet.ponk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Activity which host the GameView and the UDP Server
 */
public class GameActivity extends Activity {

	public ServerUDP t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_game);
		t = new ServerUDP();
		t.setRunning(true);
		try
		{
        t.start();
		}catch(Exception e)
		{
			Log.v("laucnh","FSDFGSF");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.v("activity","Game Activity on Pause");
        t.setRunning(false);
        t.interrupt();
	}
	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		t.setRunning(false);
	    t.interrupt();

	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
//		t = new ServerUDP();
//		t.setRunning(true);
//        t.start();
	}
}
