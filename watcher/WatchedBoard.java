package watcher;

import chess.*;
import java.util.*;

public abstract class WatchedBoard extends Board {
	private ArrayList<Watcher> watchers;

	public WatchedBoard() {
		//watchers = new ArrayList<Watcher>();
	}

	public abstract void attach(Watcher w);
	public abstract void remove(Watcher w);

	public void notifyWatchers() {
		for (Watcher w : watchers) {
			w.update();
		}
	}

	public void endOfTurn() {
		notifyWatchers();
	}

	public Piece getNet(Position p) {
		return get(p);
	}

	public ArrayList<Position> getMoves(Position pos) {
		Piece p = 	get(pos);
		
		if (p == null) {
				return null;
		}
		else {
			return p.getMoves();
		}
	}
}