package chess;

public class Position {
	
	private int x, y;

	public Position(int x, int y) {
		setPos(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] getPos() {
		int[] tmp = new int[2];
		tmp[0] = x;
		tmp[1] = y;

		return tmp;
	}

	public String toString() {
		return getX() + ":" + getY();
	}

	public boolean isOnBoard() {
		return getX() >= 0 && getX() <= 7 && getY() >= 0 && getY() <= 7;
	}
}