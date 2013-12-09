package unilim.projet.ponk;

import java.util.ArrayList;
import java.util.Random;

import unilim.projet.ponk.Entity.*;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
			if(e.getClass() == Ball.class)
			{
				for(Entity d:entities)
				{
					if (d != e)
						if(((Ball) e).checkCollision(d))
						{
							((Player) d).randomDecalageIA = new Random().nextFloat()*d.height/2 - d.height/2 ;
						}
				}
			}
		}
		
		for(Entity e:entities)
		{
			if(e instanceof Player)
			{
				if (e == user)
					e.update(enemy_y+((Player)e).randomDecalageIA);
				else
					e.update(enemy_y+((Player)e).randomDecalageIA);
			}
			else
			{
				
				enemy_y = e.pos_y;
				e.update();
			}
		}
	}
	
	public void screenModified(Rect screen)
	{
		screen_width = screen.width();
		screen_height = screen.height();
		
		ArrayList<Entity> TMPentities = new ArrayList<Entity>();
		
		int playerWidth = (int) (screen_width*0.05f);
		int playerHeight = (int) (screen_height*0.15f);
		
		
		for(Entity e:entities)
		{
			if(e instanceof Player)
			{
				if (e == user)
				{
					user = new Player(playerWidth,playerHeight, screen_width-2*playerWidth, (screen_height/2) -playerHeight, 0, 0, new Paint(Color.WHITE));
					TMPentities.add(user);
				}
				else
				{
					TMPentities.add(new Player(playerWidth,playerHeight, playerWidth, (screen_height/2)-playerHeight, 0, 0, new Paint(Color.WHITE)));
				}
			}
			else
			{
				TMPentities.add(e);
			}
		}
		
		entities = TMPentities;
		
		for(Entity e:entities)
		{
			e.setScreen(screen_width,screen_height);
		}
	}
}
