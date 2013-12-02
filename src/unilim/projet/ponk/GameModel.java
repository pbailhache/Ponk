package unilim.projet.ponk;

import java.util.ArrayList;

import unilim.projet.ponk.Entity.*;
import android.graphics.Canvas;
import android.graphics.Rect;

public class GameModel 
{
	private ArrayList<Entity> entities;
		
	private Player user;
	
	public float user_y;
	
	public float enemy_y;
	
	public float screen_width;
	public float screen_height;
	
	public GameModel(Rect screen, Player p1,Player p2,Ball b)
	{
		screen_width = screen.width();
		screen_height = screen.height();
		entities = new ArrayList<Entity>();
		entities.add(p1);
		entities.add(p2);
		user = p1;
		entities.add(b);
		user_y = screen_height/2; // On poisitionne le player au milieu de l'écran
		enemy_y = screen_height/2; // On poisitionne le player au milieu de l'écran
		this.screenModified(screen);
	}
	
	public void draw(Canvas canvas)
	{
		for(Entity e:entities)
		{
			e.draw(canvas);
		}
	}
	
	public void update()
	{
		for(Entity e:entities)
		{
			if(e instanceof Player)
			{
				if (e == user)
					e.update(user_y);
				else
					e.update(enemy_y);
			}
			else
			{
				e.update();
			}
		}
	}
	
	public void screenModified(Rect screen)
	{
		screen_width = screen.width();
		screen_height = screen.height();
		
		for(Entity e:entities)
		{
			e.setScreen(screen_width,screen_height);
		}
	}
}
