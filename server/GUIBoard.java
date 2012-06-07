package server;

import chess.*;
import watcher.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

import chess.*;

public class GUIBoard extends WatchedBoard implements ActionListener {
	
	private JFrame f;
	private JButton[][] bGrid;
	private Color blackp, whitep, space1, space2;
	private JLabel turnl;
	private JTextArea movesl;
	private ArrayList<Position> moves;
	private Piece tmp, sel;

	private ArrayList<Watcher> watchers;

	public GUIBoard() {
		super();
		f = new JFrame("Chess");
		f.setLayout(new BorderLayout(5, 5));

		watchers = new ArrayList<Watcher>();

		sel = null;
		moves = new ArrayList<Position>();


		// setup grid in BorderLayout.CENTER
		bGrid = new JButton[8][8];

		blackp = new Color(50, 50, 50);
		whitep = new Color(255, 255, 255);
		space1 = new Color(255, 206, 158);
		space2 = new Color(209, 139, 71);

		JPanel buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(8, 8, 0, 0));
		buttonP.setSize(500,500);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece tmp = get(new Position(j, i));
				bGrid[j][i] = new JButton(tmp == null ? " " : tmp.onBoard() ? Character.toString(tmp.getChar()) : " ");
				bGrid[j][i].addActionListener(this);
				if (tmp != null) {
					bGrid[j][i].setForeground(tmp.getSide() ? blackp : whitep);
				}
				bGrid[j][i].setBackground(
					i % 2 == 1 && j % 2 == 1 ?
						space1 :
					i % 2 == 1 && j % 2 == 0 ?
						space2 :
					i % 2 == 0 && j % 2 == 1 ?
						space2 :
						space1
				);
				buttonP.add(bGrid[j][i]);
			}
		}

		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridLayout(2, 1));
		turnl = new JLabel(getTurn() ? "Black's Turn" : "White's Turn");
		//turnl.setForeground(getTurn() ? blackp : whitep);
		sidebar.add(turnl);

		movesl = new JTextArea("", 10, 5);
		movesl.setEditable(false);
		sidebar.add(movesl);

		f.add(buttonP, BorderLayout.CENTER);
		f.add(sidebar, BorderLayout.EAST);

		f.setBounds(300, 300, 700, 500);
		//f.setSize(700, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void show() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece tmp = get(new Position(j, i));
				bGrid[j][i].setText(tmp == null ? " " : tmp.onBoard() ? Character.toString(tmp.getChar()) : " ");
				if (tmp != null) {
					bGrid[j][i].setForeground(tmp.getSide() ? blackp : whitep);
				}
			}
		}
	}

	public void makeGridChanges(Piece p, Position pos) {
		turnl.setText(getTurn() ? "Black's Turn" : "White's Turn");
		//turnl.setForeground(getTurn() ? blackp : whitep);
	}

	public void endOfTurn() {
		turnl.setText((getTurn() ? "Black's Turn" : "White's Turn") + "\n" + 
			(getTurnPlay().getOtherPlayer().inCheck() ? "In Check" : ""));
		show();
		notifyWatchers();
	}

	public void sortMoves() {
		for (int i = 0; i < moves.size(); i++) {
			boolean good = true;
			int j = i;
			Position tmp = moves.get(i);
			int pos = -1;
			while (good) {
				if (j == moves.size() - 1) {
					good = false;
				}
				else {
					if (moves.get(j).getX() < tmp.getX()) {
						tmp = moves.get(j);
						pos = j;
					}
					else if (moves.get(j).getX() == tmp.getX()) {
						if (moves.get(j).getY() < tmp.getY()) {
							tmp = moves.get(j);
							pos = j;
						}
					}
				}
				j++;
			}
			if (pos != -1) {
				moves.set(pos, moves.get(i));
				moves.set(i, tmp);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (bGrid[j][i] == e.getSource()) {
					
					// if position isn't null
					if (get(new Position(j, i)) != null) {
						// if it's the current player's piece
						if (get(new Position(j, i)).getSide() == getTurn()) {
							//select that piece
							sel = get(new Position(j, i));

							// get moves 
							moves = sel.getMoves();
							sortMoves();

							String t = "Selected " + sel.getChar() + "\n";
							for (Position p : moves) {
								t += p + "\n";
							}
							movesl.setText(t);
						}

						// if it's the other player's piece
						else {
							// if there is a selected piece
							if (sel != null && !moves.isEmpty()) {
								// if it's a possible move
								for (Position p : moves) {
									Position pos = new Position(j, i);
									if (pos.getX() == p.getX() && pos.getY() == p.getY()) {
										try {
											sel.move(p);
										}
										catch (InvalidMoveException ime) {
											ime.printStackTrace();
											sel = null;
											moves.clear();
											movesl.setText("");
										}
									}
								}
								sel = null;
								moves.clear();
								movesl.setText("");
							}
							else {
								sel = null;
								moves.clear();
								movesl.setText("");
							}
						}
					}
					// if there is a selected piece
					else if (sel != null && !moves.isEmpty()) {
						// if the position is a possible move
						for (Position p : moves) {
							Position pos = new Position(j, i);
							if (pos.getX() == p.getX() && pos.getY() == p.getY()) {
								try {
									sel.move(p);
								}
								catch (InvalidMoveException ime) {
									ime.printStackTrace();
								}
							}
						}
						sel = null;
						moves.clear();
						movesl.setText("");

					}

					else {
						sel = null;
						moves.clear();
						movesl.setText("");
					}
				}
			}
		}
	}

	public void remove(Watcher w) {
		watchers.remove(w);
	}
	public void attach(Watcher w) {
		watchers.add(w);
	}

	public void notifyWatchers() {
		for (Watcher w : watchers) {
			w.update();
		}
	}

}