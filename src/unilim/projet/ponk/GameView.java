package unilim.projet.ponk;

import java.util.Random;

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

public class GameView extends SurfaceView implements Callback{

	private GameThread thread;
	private Paint paint;
	private Canvas canvas;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Log.v("lel","ertttt");
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		thread = new GameThread(holder);
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    // TODO Auto-generated constructor stub
		Log.v("lel","ffffff");

	    SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		thread = new GameThread(holder);
	}

	public GameView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    // TODO Auto-generated constructor stub
		Log.v("lel","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	    SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		thread = new GameThread(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.v("lel","sdfsdfsdfsdf");
		thread.setRunning(true);
        thread.start();
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
    		Log.v("lel","gameThread created");
            mSurfaceHolder = surfaceHolder;
            paint = new Paint(Color.RED);
        }

        @Override
        public void run() {
            while (mRun) {
            	canvas = null;
                try {
                	canvas = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {                 
                        doDraw(canvas);
                        sleep(60);
                    }
                } catch (Exception e) {                 
                    e.printStackTrace();
                }finally {
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        private void doDraw(Canvas canvas) {
            //clear the canvas
            canvas.drawColor(Color.WHITE);                        
        	
        	

        	Random random = new Random();
            int w = canvas.getWidth();
            int h = canvas.getHeight();
            int x = random.nextInt(w-50); 
            int y = random.nextInt(h-50);
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            int size = 20;
            paint.setARGB(255,r,g,b);
            canvas.drawCircle(x,y,size,paint);    
        
             x = random.nextInt(w-50); 
             y = random.nextInt(h-50);
             r = random.nextInt(255);
             g = random.nextInt(255);
             b = random.nextInt(255);
            paint.setARGB(255,r,g,b);
            canvas.drawCircle(x,y,size,paint);      
        }
        public void setRunning(boolean b) {
            mRun = b;
        }
    }
}
