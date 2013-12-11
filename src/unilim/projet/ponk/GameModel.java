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
	public static float playerWidthRatio = 0.05f;
	public static float playerHeightRatio = 0.15f;
	public static float ballSizeRatio = 0.15f;
	public static float initialBallSpeedRatio = 0.2f;
	
	private ArrayList<Entity> entities;
		
	private Player user;
	
	public static float user_y;
	
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
		user_y = screen_height/2; // On positionne le player au milieu de l'écran
		enemy_y = screen_height/2; // On positionne le player au milieu de l'écran
		this.screenModified(screen);
	}
	
	public void draw(Canvas canvas)
	{
		for(Entity e:entities)
		{
			e.draw(canvas);
		}
	}
	
	/**
	 * Update position and check for collision through entities
	 */
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
					e.update(user_y);
				else
					e.update(user_y);
			}
			else
			{
				
				enemy_y = e.pos_y;
				e.update();
			}
		}
	}
	
	
	/**
	 * Re-create a model from constants. Use it when changing these constants or creating the model
	 */
	public static GameModel createModel(Rect screen)
	{
		int playerWidth = (int) (screen.width()*GameModel.playerWidthRatio); 
		int playerHeight = (int) (screen.height()*GameModel.playerHeightRatio); 
		int ballSize = (int) (playerHeight*GameModel.ballSizeRatio); 
		int ballDelta = (int) (ballSize*GameModel.initialBallSpeedRatio);
		
		return (new GameModel(screen, 
					new Player(playerWidth,playerHeight, screen.width()-2*playerWidth*1.5f, (screen.height()/2) -playerHeight, 0, 0, GameView.playerP), 
					new Player(playerWidth,playerHeight, playerWidth*1.5f, (screen.height()/2)-playerHeight, 0, 0, GameView.playerP), 
					new Ball(ballSize, ballSize, (screen.width()/2)-ballSize, (screen.height()/2)-ballSize, ballDelta, ballDelta,GameView.ballP)));
	}
	
	/**
	 * Update each entity with the proper screen size (conserving ratios)
	 */
	public void screenModified(Rect screen)
	{
		screen_width = screen.width();
		screen_height = screen.height();
		
		ArrayList<Entity> TMPentities = new ArrayList<Entity>();
		
		int playerWidth = (int) (screen_width*GameModel.playerWidthRatio);
		int playerHeight = (int) (screen_height*GameModel.playerHeightRatio);
		
		
		
		for(Entity e:entities)
		{
			if(e instanceof Player)
			{
				if (e == user)
				{
					user = new Player(playerWidth,playerHeight, screen_width-2*playerWidth, (screen_height/2) -playerHeight, 0, 0, GameView.playerP);
					TMPentities.add(user);
				}
				else
				{
					TMPentities.add(new Player(playerWidth,playerHeight, playerWidth, (screen_height/2)-playerHeight, 0, 0, GameView.ballP));
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
