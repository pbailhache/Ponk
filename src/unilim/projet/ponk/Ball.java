package unilim.projet.ponk;

import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Ball extends Entity
{
	public static float maxSpeedRatio = 1.5f;
	public static float speedRatio = -1.5f;
	public static float dyAngleRatio = 2f;
	
	public Ball(int w, int h, float x, float y, float dx, float dy,
			Paint paint) {
		super(w, h, x, y, dx, dy, paint);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Return dy from impact with paddle
	 */
	private float calculDy(float impact_y, float p_y, float p_height)
	{
		float impact_ratio = ((impact_y-p_y) / p_height) * 100;
		
		impact_ratio= Math.max(impact_ratio-50f,impact_ratio);
		
		return Math.min(this.dy*(dyAngleRatio - impact_ratio/100*dyAngleRatio),this.width*maxSpeedRatio);
	}
	
	public boolean checkCollision(Entity e) //Collison en axis X
	{
		//Log.v("Ball coord = ", "|X="+this.pos_x+"|Y="+this.pos_y+"|W="+this.width+"|H="+this.height+"|DX="+this.dx+"|DY="+this.dy+"|SCREEN W="+this.screen_width+"|SCREEN H="+this.screen_height);
		 if( e.pos_x < this.screen_width/2) // GAUCHE DE l'écran
		 {
			 if(this.pos_x < e.pos_x+e.width && this.pos_y < e.pos_y+e.height && this.pos_y + this.height > e.pos_y)
			 {
				 this.pos_x = e.pos_x+e.width + ((int)this.width*0.1f);
				 
				 this.dy = calculDy(this.pos_y,e.pos_y,e.height);
			        
				 this.dx = Math.min(speedRatio*this.dx, this.width*maxSpeedRatio);
				 return true;
			 }
		 }
		 else
		 {
			 if(this.pos_x + this.width > e.pos_x && this.pos_y  < e.pos_y+e.height && this.pos_y + this.height > e.pos_y)
			 {
				 this.pos_x = e.pos_x-this.width - ((int)this.width*0.1f);
				 
				 this.dy = calculDy(this.pos_y,e.pos_y,e.height);
				 
				 this.dx = Math.min(speedRatio*this.dx, this.width*maxSpeedRatio);
				 return true;
			 }
		 }
		 
		 return false;
	}
	
	public boolean checkVerticalCollision(Entity e) //Collison en axis X
	{
		if((this.pos_x < e.pos_x && e.pos_x < this.pos_x+this.width) || (this.pos_x  < e.pos_x+e.width && e.pos_x+e.width < this.pos_x+this.width ))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkHorizontalCollision(Entity e) //Collison en axis Y
	{
		if ((this.pos_y < e.pos_y && e.pos_y < this.pos_y+this.height) || (this.pos_y  < e.pos_y+e.height && e.pos_y+e.height < this.pos_y+this.height ))
		{
			return true;
		}
		return false;
	}

	@Override
	public void update(float user_y) {
		// TODO Auto-generated method stub
		this.update();
	}
}
