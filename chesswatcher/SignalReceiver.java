package chesswatcher;

import watcher.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class SignalReceiver implements Runnable {
	public static final int PORT = 2011;

	private int port = PORT;
	private String server;
	private Watcher b;

	public SignalReceiver(Watcher b) {
		this.b = b;
	}

	public void run() {
		try {
			Socket s = new Socket("www", 80);
			InetAddress addr = s.getLocalAddress();
			server = addr.getHostAddress();
			
			ServerSocket so = new ServerSocket(port);

			while (true) {
				Socket sock = so.accept();
				String clientip = sock.getInetAddress().getHostAddress();

				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				int inr = in.read();
				String tmp = "";
				while (inr != -1 && inr != ';') {
					tmp += Character.toString((char)inr);
					inr = in.read();
				}
				if (tmp.equals("update")) {
					b.update();
					System.out.println("told board to update");
				}
			}
		}
		catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}
}