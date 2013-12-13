package unilim.projet.ponk;

import java.util.ArrayList;
import java.util.Random;

import unilim.projet.ponk.Entity.*;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Model of the game, manage all game entities and game related functions
 * @author Nivtitif
 */
public class GameModel 
{
	public static float playerWidthRatio = 0.04f;
	public static float playerHeightRatio = 0.15f;
	public static float ballSizeRatio = 0.15f;
	public static float initialBallSpeedRatio = 0.2f;
	
	public static boolean isIA = false;
	
	private ArrayList<Entity> entities;
		
	private Player user;
	
	public static float user_y;
	
	public static float enemy_y;
	
	public static float ball_x;
	public static float ball_y;
	
	public static float ball_dx;
	public static float ball_dy ;
	
	public float screen_width;
	public float screen_height;
	
	
	/**
	 * @param screen
	 * @param p1
	 * @param p2
	 * @param b
	 */
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
	
	/**
	 * @param canvas
	 */
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
				{
					e.update(enemy_y);
				}
			}
			else
			{
				if(!isIA && !ServerUDP.server)
				{
					e.update();
					e.dx = ball_dx;
					e.dy = ball_dy;
					e.pos_x = ball_x ;
					e.pos_y = ball_y ;
					
					
				}
				
				if(isIA)
				{
					Random rnd = new Random();
					enemy_y = e.pos_y + 100 - rnd.nextInt(200);
					e.update();
				}
				
				
				if(!isIA && ServerUDP.server)
				{
					e.update();
					ball_x = e.pos_x ;
					ball_y = e.pos_y ;
					ball_dx = e.dx;
					ball_dy = e.dy;
				}
				
				
			}
		}
	}
	
	
	/**
	 * Re-create a model from constants. Use it when changing these constants or creating the model
	 * @param screen
	 * @return
	 */
	public static GameModel createModel(Rect screen)
	{
		int playerWidth = (int) (screen.width()*GameModel.playerWidthRatio); 
		int playerHeight = (int) (screen.height()*GameModel.playerHeightRatio); 
		int ballSize = (int) (playerHeight*GameModel.ballSizeRatio); 
		int ballDelta = (int) (ballSize*GameModel.initialBallSpeedRatio);
		
		ball_x = (screen.width()/2)-ballSize;
		ball_y = (screen.height()/2)-ballSize;
		ball_dx = ballDelta;
		ball_dy = ball_dx;
		
		return (new GameModel(screen, 
					new Player(playerWidth,playerHeight, screen.width()-2*playerWidth*1.5f, (screen.height()/2) -playerHeight, 0, 0, GameView.playerP), 
					new Player(playerWidth,playerHeight, playerWidth*1.5f, (screen.height()/2)-playerHeight, 0, 0, GameView.playerP), 
					new Ball(ballSize, ballSize, (screen.width()/2)-ballSize, (screen.height()/2)-ballSize, ballDelta, ballDelta,GameView.ballP)));
	}
	
	/**
	 * Update each entity with the proper screen size (conserving ratios)
	 * @param screen
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
					TMPentities.add(new Player(playerWidth,playerHeight, playerWidth, (screen_height/2)-playerHeight, 0, 0, GameView.playerP));
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
