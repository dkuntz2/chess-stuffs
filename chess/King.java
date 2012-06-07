package chess;

import java.util.*;

public class King extends Piece {
	
	public static final char CHAR = '!';

	public King(Position p, int l, Player pl) {
		super(p, l, pl);
	}

	public char getChar() {
		return CHAR;
	}

	public ArrayList<Position> getMoves() {
		ArrayList<Position> tmp = new ArrayList<Position>();

		Piece tmpp;
		// north moves
		if (getPos().getY() > 0) {
			// 0:-1
			tmpp = getBoard().getPos(getPos(0, -1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(0, -1));
			}

			// -1:-1
			tmpp = getBoard().getPos(getPos(-1, -1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(-1, -1));
			}

			// 1:-1
			tmpp = getBoard().getPos(getPos(1, -1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(1, -1));
			}
		}

		// south moves
		if (getPos().getY() < 7) {
			// 0:1
			tmpp = getBoard().getPos(getPos(0, 1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(0, 1));
			}

			// -1:1
			tmpp = getBoard().getPos(getPos(-1, 1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(-1, 1));
			}

			// 1:1
			tmpp = getBoard().getPos(getPos(1, 1));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(1, 1));
			}
		}

		// side moves
		if (getPos().getX() > 0) {
			tmpp = getBoard().getPos(getPos(-1, 0));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(-1, 0));
			}
		}

		if (getPos().getX() < 7) {
			tmpp = getBoard().getPos(getPos(1, 0));
			if (tmpp == null || tmpp.getSide() != getSide()) {
				tmp.add(getPos(1, 0));
			}
		}

		return tmp;
	}

	public void rmFromBoard() {
		setOnBoard(false);

		getBoard().gameOver();
	}

}