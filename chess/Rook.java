package chess;

import java.util.*;

public class Rook extends Piece {
	
	public static final char CHAR = 'R';

	public Rook(Position p, int l, Player pl) {
		super(p, l, pl);
	}

	public char getChar() {
		return CHAR;
	}

	public ArrayList<Position> getMoves() {
		//if (getPosition)
		ArrayList<Position> tmp = new ArrayList<Position>();

		// going right?
		boolean good = getPos().getX() >= 0 && getPos().getX() != 7;
		int i = 1;
		while (good) {
			if (getPos(i, 0).getX() <= 7) {
				if (getBoard().getPos(getPos(i, 0)) == null) {
					tmp.add(getPos(i, 0));
					i++;
				}
				else {
					if (getBoard().getPos(getPos(i, 0)).getSide() != getSide()) {
						tmp.add(getPos(i, 0));
					}
					good = false;
				}
			}
			else {
				good = false;
			}
		}

		// going left
		good = getPos().getX() <= 7 && getPos().getX() != 0;
		i = -1;
		while (good) {
			if (getPos(i, 0).getX() >= 0) {
				if (getBoard().getPos(getPos(i, 0)) == null) {
					tmp.add(getPos(i, 0));
					i--;
				}
				else {
					if (getBoard().getPos(getPos(i, 0)).getSide() != getSide()) {
						tmp.add(getPos(i, 0));
					}
					good = false;
				}
			}
			else {
				good = false;
			}
		}

		// going north
		good = getPos().getY() >= 0 && getPos().getY() != 7;
		i = 1;
		while (good) {
			if (getPos(0, i).getY() <= 7) {
				if (getBoard().getPos(getPos(0, i)) == null) {
					tmp.add(getPos(0, i));
					i++;
				}
				else {
					if (getBoard().getPos(getPos(0, i)).getSide() != getSide()) {
						tmp.add(getPos(0, i));
					}
					good = false;
				}
			}
			else {
				good = false;
			}
		}

		// going south
		good = getPos().getY() <= 7 && getPos().getY() != 0;
		i = -1;
		while (good) {
			if (getPos(0, i).getY() >= 0) {
				if (getBoard().getPos(getPos(0, i)) == null) {
					tmp.add(getPos(0, i));
					i--;
				}
				else {
					if (getBoard().getPos(getPos(0, i)).getSide() != getSide()) {
						tmp.add(getPos(0, i));
					}
					good = false;
				}
			}
			else {
				good = false;
			}
		}


		return tmp;
	}

}