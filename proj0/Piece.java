class Piece {
	private boolean isFire;
	private boolean crowned;
	private Board b;
	public int x, y;
	private String type;
	public String img;
	private boolean capture;

	private boolean isCapture(int x, int y) {
		return (Math.abs(x-this.x) + Math.abs(y-this.y)) == 4;
	}

	private void bomb() {
		for(int i = x-1; i <= x+1; ++i) {
			for(int j = y-1; j <= y+1; ++j) {
				if(i>=0 && i <=7 && j>=0 && j<=7 && b.pieceAt(i, j) != null) {
					if(b.pieceAt(i, j).isFire() == b.player) {
						b.remove(i, j);
					} else {
						if(!b.pieceAt(i, j).isShield())
							b.remove(i, j);
					}
				}
			}
		}
	}

	public Piece(boolean isFire, Board b, int x, int y, String type) {
		this.crowned = false;
		this.isFire = isFire;
		this.capture = false;
		this.b = b;
		this.x = x;
		this.y = y;
		this.type = type;
		img = "img/" + type + "-" + (isFire()? "fire": "water") + ".png";
	}

	public boolean isFire() {
		return isFire;
	}

	public int side() {
		if(isFire()) 
			return 0;
		else 
			return 1;
	}

	public boolean isKing() {
		return crowned;
	}

	public boolean isBomb() {
		return type.equals("bomb");
	}

	public boolean isShield() {
		return type.equals("shield");
	}

	public void move(int x, int y) {
		if(isCapture(x, y)) {
			// eleminte the piece captured
		    b.remove((x+this.x)/2, (y+this.y)/2); 
			b.place(this, x, y);
			this.capture = true;
			if(isBomb()) 
				bomb();
		} else 
			b.place(this, x, y);
		b.moved = true;	
		if((isFire() && this.y == 7) || (!isFire() && this.y == 0)) {
			crowned = true;
			img = "img/" + type + "-" + (isFire()? "fire": "water") + "-crowned.png";
		}
	}

	public boolean hasCapture() {
		return this.capture;
	}
	public void doneCaputring() {
		this.capture = false;
	}
}