package unilim.projet.ponk;

import android.graphics.Paint;

public class Ball extends Entity
{
	public Ball(int w, int h, float x, float y, float dx, float dy,
			Paint paint) {
		super(w, h, x, y, dx, dy, paint);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkVerticalCollision(Entity e) //Collison en axis X
	{
		if((this.pos_x < e.pos_x && e.pos_x < this.pos_x+this.width) || (this.pos_x  < e.pos_x+e.width && e.pos_x+e.width < this.pos_x+this.width ))
		{
			dy *= -1.5;
			return true;
		}
		return false;
	}
	
	public boolean checkHorizontalCollision(Entity e) //Collison en axis Y
	{
		if ((this.pos_y < e.pos_y && e.pos_y < this.pos_y+this.height) || (this.pos_y  < e.pos_y+e.height && e.pos_y+e.height < this.pos_y+this.height ))
		{
			dx *= -1.5;
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
