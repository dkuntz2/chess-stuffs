package server;

import watcher.*;
import chesswatcher.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class WatcherProxy implements Watcher {
	private String ip;

	public WatcherProxy(String ip) {
		this.ip = ip;
	}

	public void update() {
		BufferedWriter out = null;
		try {
			Socket sock = new Socket(ip, SignalReceiver.PORT);
			out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

			out.write("update;", 0, "update;".length());
			out.flush();

			System.out.println("told " + ip + " to update");
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				out.close();
			}
			catch (IOException io) {
				io.printStackTrace();
			}
		}
	}
}