package chess;

import java.util.*;
import java.io.*;

public class Player implements Serializable {
	
	// because you're either one or the other, maybe it could be white/red...
	public static final boolean BLACK = true;
	public static final boolean WHITE = false;

	// positions in ArrayList for pawns (p1-p8)
	public static final int P1 = 0;
	public static final int P2 = 1;
	public static final int P3 = 2;
	public static final int P4 = 3;
	public static final int P5 = 4;
	public static final int P6 = 5;
	public static final int P7 = 6;
	public static final int P8 = 7;

	// positions in ArrayList for rooks
	public static final int ROOK1 = 8;
	public static final int ROOK2 = 15;

	// positions in ArrayList for knights
	public static final int KNIGHT1 = 9;
	public static final int KNIGHT2 = 14;

	// positions in ArrayList for bishops
	public static final int BISHOP1 = 10;
	public static final int BISHOP2 = 13;

	// positions in ArrayList for the royalty
	public static final int QUEEN = 11;
	public static final int KING = 12;

	// keep out, this is private
	private boolean side;
	private ArrayList<Piece> pieces;
	private Board board;

	public Player(boolean bw, Board b) {
		side = bw;
		board = b;

	}

	public void setup() {
		pieces = new ArrayList<Piece>();
		
		// create pieces for player
		int inity = side ? 1 : 6;
		int i;

		// create eight pawns
		for (i = 0; i < 8; i++) {
			pieces.add(new Pawn(new Position(i, inity), i, this));	// 0-7
		}

		inity = side ? 0 : 7;

		// now the rest of the pieces (back row)
		pieces.add(new Rook(new Position(i % 8, inity), i, this));		// 8
		i++;
		pieces.add(new Knight(new Position(i % 8, inity), i, this));	// 9
		i++;
		pieces.add(new Bishop(new Position(i % 8, inity), i, this));	// 10
		i++;
		pieces.add(new Queen(new Position(i % 8, inity), i, this));		// 11
		i++;
		pieces.add(new King(new Position(i % 8, inity), i, this));		// 12
		i++;
		pieces.add(new Bishop(new Position(i % 8, inity), i, this));	// 13
		i++;
		pieces.add(new Knight(new Position(i % 8, inity), i, this));	// 14
		i++;
		pieces.add(new Rook(new Position(i % 8, inity), i, this));		// 15
		

		// place the pieces on the board
		for(Piece p : pieces) {
			if (p.onBoard())
				board.place(p, p.getPos());
		}

	}

	public Board getBoard() {
		return board;
	}

	public Piece getPiece(int loc) {
		return pieces.get(loc);
	}

	public ArrayList<Position> getMoves() {
		ArrayList<Position> moves = new ArrayList<Position>();
		//moves.add(new Position(-1, -1));

		for (Piece p : pieces) {
			if (p.getMoves().isEmpty() || p.getMoves() == null || !p.onBoard()) {

			}
			else {
				for(Position pos : p.getMoves()) {
					moves.add(pos);
				}
			}
		}

		return moves;
	}

	public boolean getSide() {
		return side;
	}

	public String figSide(boolean b) {
		if (b == BLACK) 	return "Black";
							return "White";
	}

	public Player getOtherPlayer() {
		return getSide() ? getBoard().getW() : getBoard().getB();
	}

	public boolean inCheck() {
		return inCheck(getPiece(KING).getPos());
	}

	public boolean inCheck(Position p) {
		//ArrayList<Position> tmp = getOtherPlayer().getMoves();

		for (Piece e : getOtherPlayer().pieces) {
			if (e.onBoard()) {
				for (Position f : e.getMoves()) {
					if (f.toString().equals(p.toString())) {
						return true;
					}
				}
			}
		}

		return false;

	}

	public boolean inCheckMate() {
		boolean cm = false;
		if (inCheck()) {
			cm = true;
			if (!getPiece(KING).getMoves().isEmpty()) {
				for (Position p : getPiece(KING).getMoves()) {
					if (!inCheck(p))	cm = false;
				}
			}
		}
		
		//getBoard().gameOver();
		return cm;
	}

}