package chesswatcher;

import java.net.*;
import java.io.*;
import javax.swing.*;

public class WatcherDriver {
	public static void main(String[] args) {
		String ip, server;
		ip = "";
		server = "";
		try {
			Socket s = new Socket("www", 80);
			ip = s.getLocalAddress().getHostAddress();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		System.out.println(ip);
		server = JOptionPane.showInputDialog("Server Address");
		//server = "10.16.113.174";
		//System.out.println(server + " " + ip);

		WatchedBoardProxy b = new WatchedBoardProxy(ip, server);
		GUIWatcher g = new GUIWatcher(b);

		SignalReceiver sn = new SignalReceiver(g);
		Thread t1 = new Thread(sn);
		t1.run();
	}
}