/*
 * 
 */

package unilim.projet.ponk;

import java.util.Random;

import unilim.projet.ponk.Entity.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	
	/*
	 * 
	 */
	private GameModel createModel(SurfaceHolder holder)
	{
		
		int playerWidth = (int) (holder.getSurfaceFrame().width()*0.05f);
		int playerHeight = (int) (holder.getSurfaceFrame().height()*0.15f);
		int ballSize = (int) (playerHeight*0.15f);
		int ballDelta = (int) (ballSize*0.2f);

		return (new GameModel(holder.getSurfaceFrame(), 
					new Player(playerWidth,playerHeight, holder.getSurfaceFrame().width()-2*playerWidth*1.5f, (holder.getSurfaceFrame().height()/2) -playerHeight, 0, 0, new Paint(Color.BLACK)), 
					new Player(playerWidth,playerHeight, playerWidth*1.5f, (holder.getSurfaceFrame().height()/2)-playerHeight, 0, 0, new Paint(Color.BLACK)), 
					new Ball(ballSize, ballSize, (holder.getSurfaceFrame().width()/2)-ballSize, (holder.getSurfaceFrame().height()/2)-ballSize, ballDelta, ballDelta, new Paint(Color.BLACK))));
		
	}
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);	    
	    thread = new GameThread(holder);
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    // TODO Auto-generated constructor stub
	    

	    SurfaceHolder holder = getHolder();
		holder.addCallback(this);
	    thread = new GameThread(holder);
	}

	public GameView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    // TODO Auto-generated constructor stub   

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
			 model = createModel(holder);
			 
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

	class GameThread extends Thread {
        private boolean mRun;       
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

        private void doDraw(Canvas canvas) 
        {
            //clear the canvas
        	if (canvas != null)
        	{
	            canvas.drawColor(Color.WHITE);                        
	            model.draw(canvas);
        	}
            
        }
        public void setRunning(boolean b) {
            mRun = b;
        }
    }
}
