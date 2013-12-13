package unilim.projet.ponk;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Entity class which is the super-class of all game objects.
 * @author Nivtitif
 */
public abstract class Entity 
{
	public int width;
	public int height;
	
	public float pos_x; 
	public float pos_y;
	
	public float dx;
	public float dy;
	
	public float screen_width;
	public float screen_height;
	
	public Paint paint;
	
	/**
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param paint
	 */
	public Entity(int w, int h, float x, float y, float dx, float dy,Paint paint)
	{
		this.width = w;
		this.height = h;
		this.pos_x = x;
		this.pos_y = y;
		this.dx = dx;
		this.dy = dy;
		this.paint = paint;
	}
	
	/**
	 * @param user_y
	 */
	public abstract void update(float user_y);
	
	/**
	 * Update and move the entity
	 */
	public void update()
	{
		checkInBoundaries();
		pos_x+=dx;
		pos_y+=dy;
	}
	
	/**
	 * Check if entity is on screen and keep it inside.
	 */
	public void checkInBoundaries()
	{
		
		if(this.pos_x <= 0)
		{
			this.pos_x = 1;
			dx *= -1;
		}
		if (this.pos_x+this.width >= this.screen_width)
		{
			this.pos_x = this.screen_width - this.width-1;
			dx *= -1;
		}
		if(this.pos_y <= 0)
		{
			dy *= -1;
			this.pos_y = 1;
		}
		if (this.pos_y+this.height >= this.screen_height)
		{
			dy *= -1;
			this.pos_y = this.screen_height - this.height -1;
		}
	}
	
	/**
	 * @param canvas
	 */
	public void draw(Canvas canvas) 
	{
		// TODO Auto-generated method stub
		canvas.drawRect(this.pos_x,this.pos_y,this.pos_x+this.width,this.pos_y+this.height,this.paint);
	}

	/**
	 * Change the screen value (used for checkBoundaries)
	 * @param screen_width
	 * @param screen_height
	 */
	public void setScreen(float screen_width, float screen_height) 
	{
		this.screen_width = screen_width;
		this.screen_height = screen_height;
	}
}
