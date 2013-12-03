package unilim.projet.ponk;

import android.graphics.Paint;

public class Player extends Entity
{
	public Player(int w, int h, float x, float y, float dx, float dy,
			Paint paint) {
		super(w, h, x, y, dx, dy, paint);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(float input_y) // On déplace le player avec son centre sur l'endroit ou le jouer à appuyé, pas de dx et dy
	{
		this.pos_y = input_y - this.height/2;
		
		checkInBoundaries();
	}
	
}
