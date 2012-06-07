package chesswatcher;

import watcher.*;
import server.*;
import chess.*;

import java.util.*;
import java.io.*;
import java.net.*;

public class WatchedBoardProxy extends WatchedBoard {
	private String ip, server;

	public WatchedBoardProxy(String ip, String server) {
		System.out.println(server + " --- " + ip);

		this.ip = ip;
		this.server = server;

		System.out.println(this.server + " - " + this.ip);
	}

	// watcher stuff
	public void attach(Watcher w) {
		command("attach:" + ip + ";");
	}
	public void remove(Watcher w) {
		command("remove:" + ip + ";");
	}

	
	// board stuff
	public Piece getNet(Position p) {
		Piece tmp = null;

		String b = ask("get:" + p.getX() + ":" + p.getY() + ";");
		String[] cms = b.split(":");
		
		if (cms[0].equals("n")) {
			return tmp;
		}
		char[] chr = cms[0].toCharArray();
		char c = chr[0];
		Player s = new Player(cms[2].equals("b"), null);
		int l = Integer.parseInt(cms[1]);

		if (c == 'P')		tmp = new Pawn(p, l, s);
		else if (c == 'R')	tmp = new Rook(p, l, s);
		else if (c == 'K')	tmp = new Knight(p, l, s);
		else if (c == 'B')	tmp = new Bishop(p, l, s);
		else if (c == 'Q')	tmp = new Queen(p, l, s);
		else if (c == '!')	tmp = new King(p, l, s);
		
		return tmp;
	}

	public boolean wInCheck() {
		return Boolean.parseBoolean(ask("check:w;"));
	}
	public boolean bInCheck() {
		return Boolean.parseBoolean(ask("check:b;"));
	}

	public void move(Piece p, Position pos) {
		String s = ask("move:" + p.getPos().getX() + ":" + p.getPos().getY() + ":" + pos.getX() + ":" + pos.getY() + ";");
		System.out.println(s);
	}

	public ArrayList<Position> getMoves(Position p) {
		String e = ask("moves:" + p.getX() + ":" + p.getY() + ";");
		ArrayList<Position> m = new ArrayList<Position>();

		String[] o = e.split(":");
		for (int i = 0; i < o.length; i = i + 2) {
			m.add(new Position(
				Integer.parseInt(o[i]),
				Integer.parseInt(o[i + 1])
			));
		}

		return m;
	}

	
	// network-y stuff
	private void command(String cmd) {
		BufferedWriter out = null;
		try {
			Socket sock = new Socket(server, ServerListener.PORT);
			out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

			char[] oar = cmd.toCharArray();
			for (char c : oar) {
				out.write(c);
			}
			out.flush();
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

	private String ask(String cmd) {
		BufferedWriter out = null;
		BufferedReader in = null;
		Socket sock = null;
		String ret = "";
		try {
			System.out.println(server);
			sock = new Socket(server, ServerListener.PORT);
			out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			out.write(cmd, 0, cmd.length());
			out.flush();
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			int inr = in.read();
			while (inr != ';') {
				ret += Character.toString((char)inr);
				inr = in.read();
			}
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		finally {
			try {
				out.close();
				in.close();
				sock.close();
			}
			catch (IOException io) {
				io.printStackTrace();
			}
		}

		return ret;
	}
}