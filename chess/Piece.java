package chess;

import java.util.*;

public class Piece {
	
	public static final char CHAR = ' ';

	private Position pos;
	private int loc, numMoves;
	private Player player;
	private boolean onBoard;

	public Piece(Position pos, int loc, Player p) {
		this.pos = pos;
		this.loc = loc;
		this.player = p;
		this.onBoard = true;
		this.numMoves = 0;
	}

	// character representation of piece, used to make it easier to see on grid?
	public char getChar() {
		return CHAR;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position p) {
		pos = p;
	}

	public int getLoc() {
		return loc;
	}

	public Player getPlayer() {
		return player;
	}
	public Board getBoard() {
		return getPlayer().getBoard();
	}

	public boolean getSide() {
		return getPlayer().getSide();
	}

	public int numMoves() {
		return numMoves;
	}

	public boolean onBoard() {
		return onBoard;
	}
	public void rmFromBoard() {
		onBoard = false;
	}
	public void putOnBoard() {
		onBoard = true;
	}
	public void setOnBoard(boolean b) {
		onBoard = b;
	}

	// will be used in rest of pieces to show where that piece can move to
	public ArrayList<Position> getMoves() {
		return new ArrayList<Position>();
	}

	// make it go somewhere
	public void move(Position newPos) throws InvalidMoveException {
		boolean good = false;
		if (getMoves().size() > 0) {		
			for (Position p : getMoves()) {
				if (newPos.toString().equals(p.toString())) {
					good = true;
				}
			}
		}
		if (good) {
			try {
				getBoard().move(this, newPos);
				numMoves++;
			}
			catch (StillInCheckException ime) {
				ime.printStackTrace();
				//throw new InvalidMoveException();
			}
		}
		else {
			System.out.println(newPos);
			throw new InvalidMoveException();
		}
	}

	public Position getPos(int dx, int dy) {
		return new Position(getPos().getX() + dx, getPos().getY() + dy);
	}


}