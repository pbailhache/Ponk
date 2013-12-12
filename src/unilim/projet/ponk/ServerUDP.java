package unilim.projet.ponk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class ServerUDP extends Thread 
{
		private boolean mRun;
		
		private DatagramSocket serverSocket ;
        private byte[] receiveData;
        private byte[] sendData;
        private String state = "IDLE";
        private int ServerPort = 9000;
        
        ServerUDP()
        {
        	try {
        		if(serverSocket == null)
        		{
					serverSocket = new DatagramSocket(null);
					serverSocket.setReuseAddress(true);
					serverSocket.bind(new InetSocketAddress(ServerPort));
					receiveData = new byte[1024];
		            sendData = new byte[1024];
		            Log.v("socket","Init Server Done");
        		}
			} catch (SocketException e) {
				Log.v("socket","Failed to create serverSocket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        @Override
        public void run()
        {
        	try
        	{
            while (mRun) 
            {
            	Log.v("socket","mRun BOUCLE = "+mRun);
            	Log.v("socket","boucle server LEL");
            	sendData = "LOL".getBytes();
        		InetAddress local;
					local = InetAddress.getByName("10.0.2.2");
					Log.v("socket","Sent"+sendData);
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,local, 8088);
					serverSocket.send(sendPacket);
				
				
//				
//            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//				serverSocket.receive(receivePacket);
//					String receiveString = new String( receivePacket.getData());
//	            	Log.v("RECEIVED",receiveString);
				

//            	String result = "";
//            	InetAddress IPAddress = receivePacket.getAddress();
//            	int port = receivePacket.getPort();
            	
//            	if(state == "IDLE")
//            	{
//            		if (receiveString == "GAME?")
//            		{
//            			result = "OKGAME";
//            			state = "CONNECTING";
//            		}
//            	}
//            	if(state == "CONNECTING")
//            	{
//            		if (receiveString == "OKGAME")
//            		{
//            			result = Float.toString(GameModel.user_y);
//            			state = "CONNECTED";
//            		}
//            	}
//            	if(state == "CONNECTED")
//            	{
//            		if (receiveString == "END?")
//            		{
//            			result = "OKEND";
//            			state = "END";
//            		}
//            	}
//            	if(state == "END")
//            	{
//            		if (receiveString == "OKEND")
//            		{
//            			result = "";
//            			state = "IDLE";
//            		}
//            	}
            	
            	
//            	try {
//            		if(result != "")
//            		{
//	            		sendData = result.getBytes();
//	            		InetAddress local = InetAddress.getByName("10.0.2.2");
//	                	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,local, 8088);
//						serverSocket.send(sendPacket);
//            		}
//				} catch (IOException e) {
//					Log.v("socket","Failed to send serverSocket");
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
            }
        	}
        	catch (Exception e)
        	{
        		Log.v("Socket", "LOLOLOLo");
				// TODO Auto-generated catch block
				e.printStackTrace();
        	}
        }
        
        

        public void setRunning(boolean b) 
        {
        	Log.v("socket","mRun ="+b);
            mRun = b;
        }

}
