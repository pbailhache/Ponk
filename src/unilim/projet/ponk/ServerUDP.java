package unilim.projet.ponk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import android.util.Log;

public class ServerUDP extends Thread 
{
		private boolean mRun;
		
		private DatagramSocket serverSocket ;
        private byte[] receiveData;
        private byte[] sendData;
        private String state = "IDLE";
        
        ServerUDP()
        {
        	try {
				serverSocket = new DatagramSocket(9876);
				receiveData = new byte[1024];
	            sendData = new byte[1024];
	            Log.v("socket","Init Server Done");
			} catch (SocketException e) {
				Log.v("socket","Failed to create serverSocket");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        @Override
        public void run()
        {
            while (mRun) 
            {
            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            	try {
					serverSocket.receive(receivePacket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					Log.v("socket","Failed to receive serverSocket");
					e1.printStackTrace();
					break;
				}
            	String receiveString = new String( receivePacket.getData());
            	Log.v("RECEIVED",receiveString);
            	String result = "";
            	InetAddress IPAddress = receivePacket.getAddress();
            	int port = receivePacket.getPort();
            	
            	if(state == "IDLE")
            	{
            		if (receiveString == "GAME?")
            		{
            			result = "OKGAME";
            			state = "CONNECTING";
            		}
            	}
            	if(state == "CONNECTING")
            	{
            		if (receiveString == "OKGAME")
            		{
            			result = Float.toString(GameModel.user_y);
            			state = "CONNECTED";
            		}
            	}
            	if(state == "CONNECTED")
            	{
            		if (receiveString == "END?")
            		{
            			result = "OKEND";
            			state = "END";
            		}
            	}
            	if(state == "END")
            	{
            		if (receiveString == "OKEND")
            		{
            			result = "";
            			state = "IDLE";
            		}
            	}
            	
            	
            	try {
            		if(result != "")
            		{
	            		sendData = result.getBytes();
	                	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
						serverSocket.send(sendPacket);
            		}
				} catch (IOException e) {
					Log.v("socket","Failed to send serverSocket");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        

        public void setRunning(boolean b) {
            mRun = b;
        }

}
