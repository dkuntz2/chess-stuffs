package chess;

import java.util.*;

public class Board {

	private Player black, white;
	private Piece[][] grid;
	private boolean turn, stillGoing;

	public Board() {
		grid = new Piece[8][8];
		blankGrid();

		black = new Player(true, this);
		white = new Player(false, this);
		black.setup();
		white.setup();
		turn = true;
		stillGoing = true;
	}


	public void blankGrid() {
		for (Piece[] e : grid) {
			for (Piece g : e) {
				g = null;
			}
		}
	}

	public boolean getTurn() {
		return turn;
	}

	public String getTurnStr() {
		return turn ? "Black" : "White";
	}

	public Player getTurnPlay() {
		return turn ? getB() : getW();
	}

	// stuff for placing and getting pieces
	public Piece get(Position p) {
		return grid[p.getX()][p.getY()];
	}
	private void set(Position pos, Piece p) {
		grid[pos.getX()][pos.getY()] = p;
	}
	public void move(Piece p, Position pos) throws StillInCheckException {
		if (stillGoing) {
			if (getTurnPlay().inCheck()) {

				Position e = p.getPos();
				Piece t = get(pos);
				if (t != null) {
					t.rmFromBoard();
				}

				set(e, null);
				set(pos, p);
				p.setPos(pos);

				if (getTurnPlay().inCheck()) {
					set(e, p);
					p.setPos(e);
					t.putOnBoard();
					set(pos, t);

					throw new StillInCheckException();
				}
				else {
					set(e, null);
					p.setPos(pos);
					turn = !turn;
					endOfTurn();
				}

			}
			else {
				place(p, pos);
				turn = !turn;
				endOfTurn();
			}
		}
	}
	public void place(Piece p, Position pos) {
		if (get(pos) != null) {
			get(pos).rmFromBoard();
		}
		set(p.getPos(), null);
		set(pos, p);
		p.setPos(pos);
	}
	public Piece getPos(Position p) { // REUNDANCY!
		return get(p);
	}

	public Player getB() {
		return black;
	}
	public Player getW() {
		return white;
	}

	public void gameOver() {
		stillGoing = false;
	}


	// for extenders only...
	public void show() {}
	public void makeGridChanges(Piece p, Position pos) {}
	public void endOfTurn() {}
	
}