package unilim.projet.ponk;

import android.graphics.Paint;

/**
 * The paddle class
 * @author Nivtitif
 */
public class Player extends Entity
{
	public float randomDecalageIA = 0;
	
	/**
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param paint
	 */
	public Player(int w, int h, float x, float y, float dx, float dy,
			Paint paint) {
		super(w, h, x, y, dx, dy, paint);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see unilim.projet.ponk.Entity#update(float)
	 */
	@Override
	public void update(float input_y) // On déplace le player avec son centre sur l'endroit ou le jouer à appuyé, pas de dx et dy
	{
		this.pos_y = input_y - this.height/2;
		
		checkInBoundaries();
	}
	
}
