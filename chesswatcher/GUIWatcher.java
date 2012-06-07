package chesswatcher;

import chess.*;
import watcher.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUIWatcher extends JFrame implements Watcher {
	
	private WatchedBoard b;
	private Color blackp, space1, space2, whitep;
	private JButton[][] grid;
	private JLabel turn;

	public GUIWatcher(WatchedBoard b) {
		super("Chess Board Observer");

		this.b = b;
		b.attach(this);

		this.setLayout(new BorderLayout(5, 5));


		grid = new JButton[8][8];
		blackp = new Color(50, 50, 50);
		whitep = new Color(255, 255, 255);
		space1 = new Color(255, 206, 158);
		space2 = new Color(209, 139, 71);

		JPanel buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(8,8,0,0));
		buttonP.setSize(500,500);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece tmp = b.getNet(new Position(j, i));
				grid[j][i] = new JButton(tmp == null ? " " : tmp.onBoard() ? Character.toString(tmp.getChar()) : " ");
				//grid[j][i].setEnabled(false);
				if (tmp != null) {
					grid[j][i].setForeground(tmp.getSide() ? blackp : whitep);
				}
				grid[j][i].setBackground(
					i % 2 == 1 && j % 2 == 1 ?
						space1 :
					i % 2 == 1 && j % 2 == 0 ?
						space2 :
					i % 2 == 0 && j % 2 == 1 ?
						space2 :
						space1
				);
				buttonP.add(grid[j][i]);

			}
		}

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		turn = new JLabel(b.getTurnStr());
		top.add(turn);

		this.add(buttonP, BorderLayout.CENTER);
		this.add(top, BorderLayout.NORTH);

		this.setBounds(300, 300, 500, 520);
		//f.setSize(700, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JButton[][] getGrid() {
		return grid;
	}

	public WatchedBoard getBoard() {
		return b;
	}
	public WatchedBoard b() {
		return b;
	}

	public JButton get(int x, int y) {
		return grid[x][y];
	}

	public void update() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece tmp = b.getNet(new Position(j, i));
				grid[j][i].setText(tmp == null ? " " : tmp.onBoard() ? Character.toString(tmp.getChar()) : " ");
				if (tmp != null) {
					grid[j][i].setForeground(tmp.getSide() ? blackp : whitep);
				}
			}
		}

		turn.setText(b.getTurn() ? "Black" : "White");
	}
}