package chess;

import java.util.*;

public class Knight extends Piece {
	
	public static final char CHAR = 'K';

	public Knight(Position p, int l, Player pl) {
		super(p, l, pl);
	}

	public char getChar() {
		return CHAR;
	}

	public ArrayList<Position> getMoves() {
		ArrayList<Position> tmp = new ArrayList<Position>();
		Piece p;

		int j = 0;
		int k = 0;

		for (int i = 0; i < 4; i++) {
			if (i == 0)			j = -2;
			else if (i == 1)	j =  2;
			else if (i == 2)	j = -1;
			else				j =  1;

			for (int e = 0; e < 2; e++) {
				if (e == 0 && (i == 0 || i == 1))		k =  1;
				else if (e == 1 && (i == 0 || i == 1)) 	k = -1;
				else if (e == 0 && (i == 2 || i == 3))	k =  2;
				else if (e == 1 && (i == 2 || i == 3))	k = -2;

				if (getPos(j, k).isOnBoard()) {
					if (getBoard().getPos(getPos(j, k)) == null || getBoard().getPos(getPos(j, k)).getSide() != getSide()) {
						tmp.add(getPos(j, k));
					}
				}
			}
		}

		return tmp;
	}

}