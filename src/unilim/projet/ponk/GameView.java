/**
 * 
 */

package unilim.projet.ponk;

import java.util.Random;

import unilim.projet.ponk.Entity.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Callback{

	private GameThread thread;
	private Canvas canvas;
	private GameModel model;
	
	private OnTouchListener touchListener;
	
	public static int playerColor = Color.WHITE;
	public static int ballColor = Color.WHITE;
	public static int backgroundColor = Color.BLACK;
	
	private Typeface font ;
	private float fontSize = 72;
	private Paint fontPaint ;
	public static int fontColor = Color.WHITE;
	
	public static int scorePlayerOne= 0;
	public static int scorePlayerTwo = 0;
	public static int maxScore = 5 ;
	
	public static Paint playerP = new Paint();
	public static Paint ballP = new Paint();
	
	public GameView(Context context) {
		super(context);
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	}

	
	/**
	 * Create and add the surface Holder for GameThread
	 */
	public GameView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    font = Typeface.createFromAsset(getContext().getAssets(), "fonts/type_writer.ttf");
	    fontPaint = new Paint();
	    fontPaint.setColor(fontColor);
	    fontPaint.setTextSize(fontSize);
	    fontPaint.setTypeface(font);
	    scorePlayerOne= 0;
		scorePlayerTwo = 0;
	
	    
	    SurfaceHolder holder = getHolder();
		holder.addCallback(this);
	    thread = new GameThread(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		model.screenModified(holder.getSurfaceFrame());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		 try {
			 GameView.playerP.setColor(GameView.playerColor);
			 GameView.ballP.setColor(GameView.ballColor);
			 model = GameModel.createModel(holder.getSurfaceFrame());
			 
			 model.screenModified(holder.getSurfaceFrame());
			  // Une fois qu'on a cree le model on peut ajouter le listener pour retrouver les coordonnées du player
			 touchListener = new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) 
					{
						//Log.v("Y = ",""+event.getY());
						model.user_y = event.getY();
						return true;
					}
				};
				
			this.setOnTouchListener(touchListener);
			
			 thread.setRunning(true);
		     thread.start();
         } catch (IllegalThreadStateException e) {
         }
	}
	
	

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
		
	}

	/**
	 * Thread class which do all the game loop
	 */
	class GameThread extends Thread {
        private  boolean mRun;       
        private SurfaceHolder mSurfaceHolder;        

        public GameThread(SurfaceHolder surfaceHolder) {
            mSurfaceHolder = surfaceHolder;
        }

        @Override
        public void run() {
            while (mRun) {
            	canvas = null;
                	model.update();
                	canvas = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {    
                        doDraw(canvas);
                        
                        try {
							sleep(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
        
        /**
         * Draw Game from model (see model in GameView)
         */
        private void doDraw(Canvas canvas) 
        {
            //clear the canvas
        	if (canvas != null)
        	{
    
	            canvas.drawColor(backgroundColor);
<<<<<<< HEAD
	            canvas.drawText(""+scorePlayerTwo, canvas.getWidth()/2-3*fontSize, 72, fontPaint);
	            canvas.drawText(""+scorePlayerOne, canvas.getWidth()/2+2*fontSize, 72, fontPaint);
	            int value = 0 ;
	            while (  value < canvas.getHeight()){
	            	canvas.drawRect(canvas.getWidth()/2-1, value, canvas.getWidth()/2+1, (value + 10), fontPaint);
	            	value += 20 ;
	            }
	            
=======
	            canvas.drawText(""+scorePlayerOne, canvas.getWidth()/2-2*fontSize, 72, fontPaint);
	            canvas.drawText(""+scorePlayerTwo, canvas.getWidth()/2+2*fontSize, 72, fontPaint);
>>>>>>> f54c557ef4eb5dbbd0f6d151954d4b1b30ca6250
	            model.draw(canvas);
        	}
            
        }
        
        public void setRunning(boolean b) {
            mRun = b;
        }
    }
}
