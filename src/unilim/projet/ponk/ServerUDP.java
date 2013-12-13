package unilim.projet.ponk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

/**
 * UDP Server with protocol for connection
 * @author Nivtitif
 */
public class ServerUDP extends Thread 
{
		private boolean mRun;
		
		private DatagramSocket serverSocket;
        private byte[] receiveData;
        private byte[] sendData;
        private String state = "IDLE";
        private String ip ="";
        private int ServerPort = 9000;
        public static boolean server = false;
        
        ServerUDP(String ip)
        {
        	try {
        			this.ip = ip ;
					serverSocket = new DatagramSocket(null);
					serverSocket.setReuseAddress(true);
					serverSocket.bind(new InetSocketAddress(ServerPort));
					receiveData = new byte[1024];
		            sendData = new byte[1024];
		            Log.v("socket","Init Server Done");
			} catch (SocketException e) {
				Log.v("socket","Failed to create serverSocket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        /**
         * Run the server until interrupted
         * @see java.lang.Thread#run()
         */
        @Override
        public void run()
        {
        	try
        	{
            while (mRun) 
            {		
            	Log.v("STATE = ",state);
            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				Log.v("TIMEOUT",""+serverSocket.getSoTimeout());
				receiveData = receivePacket.getData();
				String receiveString = new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
	            Log.v("RECEIVED",receiveString);
				

            	String result = "";
            	InetAddress IPAddress = receivePacket.getAddress();
            	
            	if(state ==  "IDLE")
            	{
	            	Log.v("RECEIVED",""+receiveString.getBytes());
	            	Log.v("INSIDE",""+"GAME".getBytes());
            		if (receiveString.equals("GAME"))
            		{
            			Log.v("RECEIVED IN",receiveString);
    	            	Log.v("RECEIVED IN","GAME");
            			result = "OKGAME";
            			state = "CONNECTING";
            		}
            		else if(receiveString.equals("OKGAME"))
            		{
            			server =true;
            			state = "CONNECTED";
            		}
            	}
            	else if(state == "CONNECTING")
            	{
            		if (receiveString.equals("OKGAME"))
            		{
            			result = Float.toString(GameModel.user_y)+"/"+Float.toString(GameModel.enemy_y)+"/"+Float.toString(GameModel.ball_x)+"/"+Float.toString(GameModel.ball_y)+"/"+Float.toString(GameModel.ball_dx)+"/"+Float.toString(GameModel.ball_dy);
            			state = "CONNECTED";
            		}
            	}
            	else if(state == "CONNECTED")
            	{
            		if (receiveString.equals("END?"))
            		{
            			result = "OKEND";
            			state = "END";
            		}
            		else
            		{
            			//On envoie la pos du joueur et on recoit celle de l'adversaire.
            			Log.v("RECEIVED IN ",receiveString);
            			String[] parts = receiveString.split("/");
            			if(!server)
            			{
                			result = Float.toString(GameModel.user_y)+"/"+Float.toString(GameModel.enemy_y)+"/"+Float.toString(GameModel.ball_x)+"/"+Float.toString(GameModel.ball_y)+"/"+Float.toString(GameModel.ball_dx)+"/"+Float.toString(GameModel.ball_dy);
                			GameModel.ball_dx = -Float.parseFloat(parts[4]);
                			GameModel.ball_dy = Float.parseFloat(parts[5]);

            			}
            			else
            			{
            				result = Float.toString(GameModel.user_y)+"/"+Float.toString(GameModel.enemy_y)+"/"+Float.toString(GameModel.ball_x)+"/"+Float.toString(GameModel.ball_y)+"/"+Float.toString(GameModel.ball_dx)+"/"+Float.toString(GameModel.ball_dy);
            			}
            			
            			GameModel.enemy_y = Float.parseFloat(parts[1]);
            			GameModel.ball_x = Float.parseFloat(parts[2]);
            			GameModel.ball_y = Float.parseFloat( parts[3]);
            		}
            	}
            	else if(state == "END")
            	{
            		if (receiveString.equals("OKEND"))
            		{
            			result = "";
            			state = "IDLE";
            		}
            	}
            	
            	
            	try {
            		if(result != "")
            		{
	            		sendData = result.getBytes();
	            		InetAddress local = InetAddress.getByName(ip);
	                	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,local, 8088);
						serverSocket.send(sendPacket);
            		}
				} catch (IOException e) {
					Log.v("socket","Failed to send serverSocket");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        	}
        	catch (Exception e)
        	{
        		Log.v("Socket", "LOLOLOLo");
				// TODO Auto-generated catch block
				e.printStackTrace();
        	}
        }
        
        
        /**
         * Used to connect to server without using the main thread
         * @author Nivtitif
         *
         */
        private class ConnectTask extends AsyncTask<String, Integer, Long> {

            protected void onProgressUpdate(Integer... progress) {
            }

            protected void onPostExecute(Long result) {
            }

			@Override
			protected Long doInBackground(String... params) {
				try {
	        		sendData = "GAME".getBytes();
	            	InetAddress local = InetAddress.getByName(ip);
	                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,local, 8088);
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					Log.v("socket","Failed to send game request");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				return null;
			}
        }
        
        /**
         * Send a GAME request to ip.
         */
        public void tryToConnect()
        {
        	new ConnectTask().execute("");
        }
        
       
        
        

        /**
         * @param b
         */
        public void setRunning(boolean b) 
        {
        	Log.v("socket","mRun ="+b);
            mRun = b;
        }

}
