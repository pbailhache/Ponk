package unilim.projet.ponk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Acitivity which contains all the settings available to the user.
 * @author Barraud
 */
public class SettingsMenu extends Activity {

	 public SeekBar mySeekBarBall ;
	 public SeekBar mySeekBarSpeedBall;
	 public SeekBar mySeekBarPlayer;
	 public Spinner mySpinnerBallColor;
	 public Spinner mySpinnerPlayerColor;
	 public Spinner mySpinnerBackgroundColor;
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_menu);
		
		  mySeekBarBall = (SeekBar) findViewById(R.id.seekBar2);
		  mySeekBarSpeedBall = (SeekBar) findViewById(R.id.seekBar3);
		  mySeekBarPlayer = (SeekBar) findViewById(R.id.seekBar1);
		  mySpinnerBallColor = (Spinner) findViewById(R.id.spinner1);
		  mySpinnerPlayerColor = (Spinner) findViewById(R.id.spinner2);
		  mySpinnerBackgroundColor = (Spinner) findViewById(R.id.spinner3);

		// Init mySeekBarBall
		mySeekBarBall.setProgress((int)(GameModel.ballSizeRatio*100));
		mySeekBarBall.setMax(30);
		
		// Init mySeekBarSpeedBall
		mySeekBarSpeedBall.setProgress((int)(GameModel.initialBallSpeedRatio*100));
		mySeekBarBall.setMax(100);

		
		// Init mySeekBarPlayer
		mySeekBarPlayer.setProgress((int)(GameModel.playerHeightRatio*100));
		mySeekBarBall.setMax(50);
		
		// Init Spinner
		mySpinnerBallColor.setSelection(this.selectColorBall());
		mySpinnerBackgroundColor.setSelection(this.selectColorBackground());
		mySpinnerPlayerColor.setSelection(this.selectColorPlayer());
		
		// Bakground with a color
//		mySpinnerBallColor.setBackgroundColor(this.selectColorBackground());
//		mySpinnerBallColor.setBackgroundColor(this.selectColorBall());
//		mySpinnerPlayerColor.setBackgroundColor(this.selectColorPlayer()) ;
	

	}	

	/**
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings_menu, menu);
		return true;
	}
	
	/**
	 * Return integer wich indicate a position of a color
	 * into arrayColor
	 */
	public int selectColorBall(){
		if ( GameView.ballColor==Color.BLACK ){
			return 0 ; 
		}
		else if ( GameView.ballColor==Color.GREEN ){
			return 1;
		}
		else if ( GameView.ballColor==Color.BLUE ){
			return 2;
		}
		else if ( GameView.ballColor==Color.RED ){
			return 3;
		}
		else if ( GameView.ballColor==Color.WHITE ){
			return 4;
		}
		else {
			return 5;
		}		
	}
	
	/**
	 * Return integer wich indicate a position of a color
	 * into arrayColor
	 */
	public int selectColorPlayer(){
		if ( GameView.playerColor==Color.BLACK ){
			return 0 ; 
		}
		else if ( GameView.playerColor==Color.GREEN ){
			return 1;
		}
		else if ( GameView.playerColor==Color.BLUE ){
			return 2;
		}
		else if ( GameView.playerColor==Color.RED ){
			return 3;
		}
		else if ( GameView.playerColor==Color.WHITE ){
			return 4;
		}
		else {
			return 5;
		}		
	}
	
	/**
	 * Return integer wich indicate a position of a color
	 * into arrayColor
	 */
	public int selectColorBackground(){
		if ( GameView.backgroundColor==Color.BLACK ){
			return 0 ; 
		}
		else if ( GameView.backgroundColor==Color.GREEN ){
			return 1;
		}
		else if ( GameView.backgroundColor==Color.BLUE ){
			return 2;
		}
		else if ( GameView.backgroundColor==Color.RED ){
			return 3;
		}
		else if ( GameView.backgroundColor==Color.WHITE ){
			return 4;
		}
		else {
			return 5;
		}		
	}
	
	/**
	 * @param color
	 * @return
	 */
	public int returnColor(String color){
		Log.v("Color return",color);
		if ( color.equals("Noir")){
			return Color.BLACK ;
		}
		else if ( color.equals("Vert")){
			return Color.GREEN ;
		}
		else if ( color.equals("Bleu")){
			return Color.BLUE ;
		}
		else if ( color.equals("Rouge")){
			return Color.RED ;
		}
		else if ( color.equals("Blanc")){
			return Color.WHITE ;
		}
		else {
			
			return Color.YELLOW ;
		}
		
	}

	/**
	 * @param v
	 */
	public void onClickSave(View v){
		
		String colorPlayer = String.valueOf(mySpinnerPlayerColor.getSelectedItem());
		String colorBall = String.valueOf(mySpinnerBallColor.getSelectedItem());
		String colorBackground = String.valueOf(mySpinnerBackgroundColor.getSelectedItem());
		
		GameView.playerColor=this.returnColor(colorPlayer) ;
		GameView.ballColor=this.returnColor(colorBall);
		GameView.backgroundColor=this.returnColor(colorBackground);
		
		GameModel.ballSizeRatio=((float)mySeekBarBall.getProgress()/100);
		GameModel.initialBallSpeedRatio=((float)mySeekBarSpeedBall.getProgress()/100);
		GameModel.playerHeightRatio=((float)mySeekBarPlayer.getProgress()/100);
		finish();
	}

	/**
	 * @param v
	 */
	public void onClickQuit(View v){
		finish();
	}

}
