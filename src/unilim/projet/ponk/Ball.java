package unilim.projet.ponk;

import java.util.Random;

import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Ball extends Entity
{
	public static float maxSpeedRatio = 1f;
	public static float speedRatio = -1.0000001f;
	public static float dyAngleRatio = 1.5f;
	public static float rePopRatio = 0.5f;
	
	public Ball(int w, int h, float x, float y, float dx, float dy,
			Paint paint) {
		super(w, h, x, y, dx, dy, paint);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Return dy from impact with paddle
	 */
	private float calculDy(float impact_y, float bot, float p_height)
	{
		float top = bot +p_height;
		float mid = (top+bot)/2 + bot;
		
		
		float impact_ratio ;
		if (impact_y > mid)
		{
			impact_ratio = 1 - (top-impact_y)/(top-mid);
		}
		else
		{
			impact_ratio = 1 - (impact_y-bot)/(mid - bot); 
		}
		
		Log.v("RATIO",""+impact_ratio);
		if (dy <= 0)
		{
			return (this.dy*(impact_ratio*dyAngleRatio));
		}
		else
		{
			return (this.dy*(impact_ratio*dyAngleRatio));
		}
	}
	
	/**
	 * CHeck collision with entity e and adjust dx and dy with proper value if it's the case
	 */
	public boolean checkCollision(Entity e) //Collison en axis X
	{
		 if( e.pos_x < this.screen_width/2) // GAUCHE DE l'écran
		 {
			 if(this.pos_x < e.pos_x && (this.pos_y > e.pos_y+e.height || this.pos_y + this.height < e.pos_y))
			 {
				 GameView.scorePlayerOne++;
				 
				 int ballDelta = (int) (this.width*GameModel.initialBallSpeedRatio);
				 
				 this.pos_x = (this.screen_width/2)-this.width;
				 this.pos_y = (this.screen_height/2)-this.width;
				 Random rnd = new Random();
				 
				 if (rnd.nextBoolean())
				 {
					 this.dx = -ballDelta;
				 }
				 else
				 {
					 this.dx = ballDelta;
				 }
				 

				 if (rnd.nextBoolean())
				 {
					 this.dy = -ballDelta;
				 }
				 else
				 {
					 this.dy = ballDelta;
				 }
				 
				 return true;
			 }
			 
			 if(this.pos_x < e.pos_x+e.width && this.pos_y < e.pos_y+e.height && this.pos_y + this.height > e.pos_y)
			 {
				 this.pos_x = e.pos_x+this.width + ((int)this.width*rePopRatio);
				 
				 this.dy = calculDy(this.pos_y,e.pos_y,e.height);
			        
				 this.dx = Math.min(speedRatio*this.dx, this.width*maxSpeedRatio);
				 return true;
			 }
		 }
		 else
		 {
			 
			 if(this.pos_x > e.pos_x + e.width && (this.pos_y > e.pos_y+e.height || this.pos_y + this.height < e.pos_y))
			 {
				 GameView.scorePlayerTwo++;
				 
				 int ballDelta = (int) (this.width*GameModel.initialBallSpeedRatio);
				 
				 this.pos_x = (this.screen_width/2)-this.width;
				 this.pos_y = (this.screen_height/2)-this.width;
				 Random rnd = new Random();
				 
				 if (rnd.nextBoolean())
				 {
					 this.dx = -ballDelta;
				 }
				 else
				 {
					 this.dx = ballDelta;
				 }
				 

				 if (rnd.nextBoolean())
				 {
					 this.dy = -ballDelta;
				 }
				 else
				 {
					 this.dy = ballDelta;
				 }
				 
				 return true;
			 }
			 
			 if(this.pos_x + this.width > e.pos_x && this.pos_y  < e.pos_y+e.height && this.pos_y + this.height > e.pos_y)
			 {
				 this.pos_x = e.pos_x-this.width - ((int)this.width*rePopRatio);
				 
				 this.dy = calculDy(this.pos_y,e.pos_y,e.height);
				 
				 this.dx = Math.min(speedRatio*this.dx, this.width*maxSpeedRatio);
				 return true;
			 }
		 }
		 
		 return false;
	}
	
	@Override
	public void update(float user_y) {
		// TODO Auto-generated method stub
		this.update();
	}
}
