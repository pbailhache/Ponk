package unilim.projet.ponk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuHelp extends Activity {

	public int page  ;
	private Button quit,next ;
	private TextView titre1, titre2 ;
	private TextView text1, text2 ;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = 0 ;
		setContentView(R.layout.activity_menu_help);
		quit = (Button)findViewById(R.id.quit);
		next = (Button)findViewById(R.id.next);
		titre1 = (TextView)findViewById(R.id.textView1);
		titre2 = (TextView)findViewById(R.id.textView3);
		text1 = (TextView)findViewById(R.id.textView2);
		text2 = (TextView)findViewById(R.id.textView4);

	}

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_help, menu);
		return true;
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
	public void next(View v)
	{
		if ( page == 0 ){
			titre1.setText("But du Jeu");
			text1.setText("Pour jouer, rien de plus simple ! Il suffit de faire bouger la raquette avec son doigt et de rattrapper la balle ! Le but est bien évidement de ne laisser passer aucune balle, et d'essayer de marquer dans le camp adverse ! On peut observer le score à tout moment  dans la zone nord du jeu. De plus dans en haut à gauche de l'écran on peut observer le bouton Pause qui permet de mettre le jeu en pause.");
			titre2.setVisibility(View.INVISIBLE);
			text2.setVisibility(View.INVISIBLE);
			next.setVisibility(View.INVISIBLE);
			page = 1 ;
		}
	}
}
