package server;

import watcher.*;

public class ServerDriver {
	public static void main(String[] args) {
		WatchedBoard b = new GUIBoard();
		ServerListener sl = new ServerListener(b);
		Thread t1 = new Thread(sl);
		t1.run();
	}
}