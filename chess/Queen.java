package chess;

import java.util.*;

public class Queen extends Piece {
	
	public static final char CHAR = 'Q';

	public Queen(Position p, int l, Player pl) {
		super(p, l, pl);
	}

	public char getChar() {
		return CHAR;
	}

	public ArrayList<Position> getMoves() {
		//if (getPosition)
		ArrayList<Position> tmp = new ArrayList<Position>();

		boolean good;
		int i;
		int j;

		//east-south +,+
		good = true;

		i = 1;

		while (good) {
			// if the position is on the board
			if (getPos(i, i).getX() <= 7 && getPos(i, i).getY() <= 7 && getPos(i, i).getX() >= 0 && getPos(i, i).getY() >= 0) {
				// if it's null
				if (getBoard().get(getPos(i, i)) == null) {
					tmp.add(getPos(i, i));
				}
				// enemy
				else if (getBoard().get(getPos(i, i)).getSide() != getSide()) {
					tmp.add(getPos(i, i));
					good = false;
				}
				// own piece
				else {
					good = false;
				}
			}
			else {
				good = false;
			}
			i++;
		}

		//east-north +,-
		good = true;

		i = 1;
		j = -1;

		while (good) {
			// if the position is on the board
			if (getPos(i, j).getX() <= 7 && getPos(i, j).getY() <= 7 && getPos(i, j).getX() >= 0 && getPos(i, j).getY() >= 0) {
				// if it's null
				if (getBoard().get(getPos(i, j)) == null) {
					tmp.add(getPos(i, j));
				}
				// enemy
				else if (getBoard().get(getPos(i, j)).getSide() != getSide()) {
					tmp.add(getPos(i, j));
					good = false;
				}
				// own piece
				else {
					good = false;
				}
			}
			else {
				good = false;
			}
			i++;
			j--;
		}

		//west-north -,-
		good = true;

		i = -1;

		while (good) {
			// if the position is on the board
			if (getPos(i, i).getX() <= 7 && getPos(i, i).getY() <= 7 && getPos(i, i).getX() >= 0 && getPos(i, i).getY() >= 0) {
				// if it's null
				if (getBoard().get(getPos(i, i)) == null) {
					tmp.add(getPos(i, i));
				}
				// enemy
				else if (getBoard().get(getPos(i, i)).getSide() != getSide()) {
					tmp.add(getPos(i, i));
					good = false;
				}
				// own piece
				else {
					good = false;
				}
			}
			else {
				good = false;
			}
			i--;
		}

		//west-south -,+
		good = true;

		i = -1;
		j = 1;

		while (good) {
			// if the position is on the board
			if (getPos(i, j).getX() <= 7 && getPos(i, j).getY() <= 7 && getPos(i, j).getX() >= 0 && getPos(i, j).getY() >= 0) {
				// if it's null
				if (getBoard().get(getPos(i, j)) == null) {
					tmp.add(getPos(i, j));
				}
				// enemy
				else if (getBoard().get(getPos(i, j)).getSide() != getSide()) {
					tmp.add(getPos(i, j));
					good = false;
				}
				// own piece
				else {
					good = false;
				}
			}
			else {
				good = false;
			}
			i--;
			j++;
		}

		// FROM ROOK
		good = getPos().getX() >= 0 && getPos().getX() != 7;
		i = 1;
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