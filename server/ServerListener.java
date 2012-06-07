package server;

import chess.*;
import watcher.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerListener implements Runnable {
	
	public static final int PORT = 2010;
	
	private int port = PORT;
	private WatchedBoard b;
	private String server;
	private HashMap<String, WatcherProxy> proxList;

	public ServerListener(WatchedBoard b) {
		this.b = b;
		proxList = new HashMap<String, WatcherProxy>();
	}

	public void run() {
		try {
			Socket s = new Socket("www", 80);
			server = s.getLocalAddress().getHostAddress();
			ServerSocket so = new ServerSocket(port);

			System.out.println("getting started on " + server);

			while (true) {
				Socket sock = so.accept();
				String clientip = sock.getInetAddress().getHostAddress();

				handleCommand(sock.getInputStream(), sock.getOutputStream(), clientip);
				
			}
		}
		catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void handleCommand(InputStream istream, OutputStream ostream, String ip) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(istream));
		String cmd = "";
		char inr = (char)in.read();
		while (inr != ';') {
			cmd += inr;
			inr = (char)in.read();
		}

		String[] cms = cmd.split(":");

		if (cms[0].equals("attach")) {
			proxList.put(ip, new WatcherProxy(ip));
			b.attach(proxList.get(ip));
			//b.attach(new WatcherProxy(ip));
		}
		else if (cms[0].equals("remove")) {
			b.remove(proxList.get(ip));
			proxList.remove(ip);
		}
		else if (cms[0].equals("get")) {
			Piece p = 	b.get(new Position(
							Integer.parseInt(cms[1]),
							Integer.parseInt(cms[2])
						));

			String o = p == null ? "n" : Character.toString(p.getChar());
			o += ":";
			o += p == null ? "n" : Integer.toString(p.getLoc());
			o += ":";
			o += p == null ? "n" : p.getSide() ? "b" : "w";
			o += ";";

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ostream));
			out.write(o, 0, o.length());
			out.flush();
			out.close();
		}
		else if (cms[0].equals("move")) {
			Piece p = 	b.get(new Position(
							Integer.parseInt(cms[1]),
							Integer.parseInt(cms[2])
						));
			
			String o = "";

			if (p.getSide() != b.getTurn()) {
				o = "invalid;";
			}
			else {
				try {
					p.move(new Position(
						Integer.parseInt(cms[3]),
						Integer.parseInt(cms[4])
					));

					o = "valid;";
				}
				catch (InvalidMoveException ime) {
					o = "invalid;";
				}
			}

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ostream));
			out.write(o, 0, o.length());
			out.flush();
			out.close();
		}
		else if (cms[0].equals("check")) {
			boolean c = cms[1].equals("w") ? b.getW().inCheck() : b.getB().inCheck();
			String o = Boolean.toString(c);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ostream));
			out.write(o, 0, o.length());
			out.flush();
			out.close();
		}
		else if (cms[0].equals("moves")) {
			Piece p = 	b.get(new Position(
							Integer.parseInt(cms[1]),
							Integer.parseInt(cms[2])
						));
			String o = "";

			if (p == null) {
				o = "empty";
			}
			else {
				ArrayList<Position> m = p.getMoves();
				if (m.isEmpty()) {
					o = "empty";
				}
				else {
					for (Position e : m) {
						o += e.toString() + ":";
					}
				}
			}

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(ostream));
			out.write(o, 0, o.length());
			out.flush();
			out.close();
		}
	}
}