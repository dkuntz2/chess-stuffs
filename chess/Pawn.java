package chess;

import java.util.*;

public class Pawn extends Piece {
	
	public static final char CHAR = 'P';

	private int numMoves;

	public Pawn(Position pos, int loc, Player p) {
		super(pos, loc, p);
		numMoves = 0;
	}

	public char getChar() {
		return CHAR;
	}

	private int getNumMoves() {
		return numMoves;
	}

	public ArrayList<Position> getMoves() {
		//if (getPosition)
		ArrayList<Position> tmp = new ArrayList<Position>();
		
		// if black - top - plus
		if (getPlayer().getSide()) {
			// can it go forward? - make sure it's a space
			if (getPos(0, 1).isOnBoard()) {
				// if it's null
				if (getBoard().get(getPos(0, 1)) == null) {
					tmp.add(getPos(0, 1));

					// if first move and on board
					if (numMoves() == 0 && getPos(0, 2).isOnBoard()) {
						// if it's null
						if (getBoard().get(getPos(0, 2)) == null) {
							tmp.add(getPos(0, 2));
						}
					}
				}
			}

			// attack - if there's a piece to the left
			if (getPos(-1, 1).isOnBoard()) {
				// if there's a piece
				if (getBoard().get(getPos(-1, 1)) != null) {
					// if it's the other team
					if (getBoard().get(getPos(-1, 1)).getSide() != getSide()) {
						tmp.add(getPos(-1, 1));
					}
				}
			}

			// attack - if there's a piece to the right
			if (getPos(1, 1).isOnBoard()) {
				// if it's a pice
				if (getBoard().get(getPos(1, 1)) != null) {
					// if it's the other team
					if (getBoard().get(getPos(1, 1)).getSide() != getSide()) {
						tmp.add(getPos(1, 1));
					}
				}
			}
		}

		// if white - bottom - minus
		else {
			// can it go forward? - make sure it's a space
			if (getPos(0, -1).isOnBoard()) {
				// if it's null
				if (getBoard().get(getPos(0, -1)) == null) {
					tmp.add(getPos(0, -1));

					// if first move and on board
					if (numMoves() == 0 && getPos(0, -2).isOnBoard()) {
						// if it's null
						if (getBoard().get(getPos(0, -2)) == null) {
							tmp.add(getPos(0, -2));
						}
					}
				}
			}

			// attack - if there's a piece to the left
			if (getPos(-1, -1).isOnBoard()) {
				// if there's a piece
				if (getBoard().get(getPos(-1, -1)) != null) {
					// if it's the other team
					if (getBoard().get(getPos(-1, -1)).getSide() != getSide()) {
						tmp.add(getPos(-1, -1));
					}
				}
			}

			// attack - if there's a piece to the right
			if (getPos(1, -1).isOnBoard()) {
				// if it's a pice
				if (getBoard().get(getPos(1, -1)) != null) {
					// if it's the other team
					if (getBoard().get(getPos(1, -1)).getSide() != getSide()) {
						tmp.add(getPos(1, -1));
					}
				}
			}
		}

		return tmp;
	}
}