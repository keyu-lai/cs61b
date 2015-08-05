class Board {
	// Two players
	private static boolean FIRE = true;
	private static boolean WATER = false;

	private Piece[][] pieces;
	public boolean player;
	private Piece selected;
	public boolean moved;
	private int fireNum, waterNum;
	private int N;

	public void drawBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					StdDrawPlus.picture(i + .5, j + .5, pieces[i][j].img, 1, 1);
				}
			}
		}
		if(selected != null && selected == pieces[selected.x][selected.y]) {
			StdDrawPlus.filledSquare(selected.x + .5, selected.y + .5, .5);
			StdDrawPlus.picture(selected.x + .5, selected.y + .5, selected.img, 1, 1);
		}
	}

	public static void main(String[] args) {
		Board board = new Board(false);
		while(true) {
			String tmp = board.winner();
			if(tmp == null) {
				board.drawBoard();
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int)StdDrawPlus.mouseX();
	                int y = (int)StdDrawPlus.mouseY();
	                if(board.canSelect(x, y)) 
	                	board.select(x, y);
	            }   
	            if(StdDrawPlus.isSpacePressed()) {
	            	if(board.canEndTurn())
	            		board.endTurn();
	            }   
            } else {
            	board.drawBoard();
            	System.out.println(tmp);
            	break;
            }  
            StdDrawPlus.show(100);
		}
	}
	
	public Board(boolean shouldBeEmpty) {
		N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		pieces = new Piece[N][N];
		player = FIRE;
		selected = null;
		moved = false;
		fireNum = 12;
		waterNum = 12;
		if (!shouldBeEmpty) {
			for(int i = 0; i < N; ++i) {
				if(i % 2 == 0) {
					pieces[i][0] = new Piece(FIRE, this, i, 0, "pawn");
					pieces[i][2] = new Piece(FIRE, this, i, 2, "bomb");
					pieces[i][6] = new Piece(WATER, this, i, 6, "shield");
				} else {
					pieces[i][1] = new Piece(FIRE, this, i, 1, "shield");
					pieces[i][5] = new Piece(WATER, this, i, 5, "bomb");
					pieces[i][7] = new Piece(WATER, this, i, 7, "pawn");
				}
			}
		} 
	}

	public Piece pieceAt(int x, int y) {
		if(x > 8 || y > 8 || x < 0 || y < 0) 
			return null;
		else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if(x>=0 || y >=0 || x<=7 || y<=7) {
			if(pieces[x][y] != null) {
				if(pieces[x][y].isFire() == player) {
					if(selected == null)
						return true;
					else {
						if(!moved) {
							return true;
						}
					}
				}
			} else {
				if(selected != null) {
					return isValidMove(x, y);
				}
			}
		}
		return false;
	}

	private boolean isValidMove(int xf, int yf) {
		if (selected.isKing()) {
			if(!moved) {
				if(Math.abs(selected.x-xf) == 1 && Math.abs(selected.y-yf) == 1)
					return true;
			}
			if(!moved || selected.hasCapture()) {
				if(Math.abs(selected.x-xf) == 2 && Math.abs(selected.y-yf) == 2 //
					&& pieces[(selected.x+xf)/2][(selected.y+yf)/2] != null //
					&& pieces[(selected.x+xf)/2][(selected.y+yf)/2].isFire() != player)
					return true;	
			}		
		} else {
			if(player == FIRE) {
				if(!moved) {
					if(Math.abs(selected.x-xf) == 1 && (yf-selected.y) == 1)
						return true;
				}
				if(!moved || selected.hasCapture()) {
					if(Math.abs(selected.x-xf) == 2 && (yf-selected.y) == 2 //
						&& pieces[(selected.x+xf)/2][(selected.y+yf)/2] != null //
						&& pieces[(selected.x+xf)/2][(selected.y+yf)/2].isFire() != player)
						return true;	
				}	
			} else {
				if(!moved) {
					if(Math.abs(selected.x-xf) == 1 && (selected.y-yf) == 1)
						return true;
				}
				if(!moved || selected.hasCapture()) {
					if(Math.abs(selected.x-xf) == 2 && (selected.y-yf) == 2 //
						&& pieces[(selected.x+xf)/2][(selected.y+yf)/2] != null //
						&& pieces[(selected.x+xf)/2][(selected.y+yf)/2].isFire() != player)
						return true;	
				}							
			}
		}	
		return false;
	}

	public void select(int x, int y) {
    	if(pieces[x][y] == null && selected != null) 
    		selected.move(x, y);
    	else
    		selected = pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if(x>=0 || x <=7 || y>=0 || y<=7) {
			pieces[p.x][p.y] = null;
			pieces[x][y] = p;
			p.x = x;
			p.y = y;
		}
	}

	public Piece remove(int x, int y) {
		if(x>=0 || x <=7 || y>=0 || y<=7) {
			if(pieces[x][y] == null) {
				System.out.println("No piece.");
				return null;
			} else {
				Piece tmp = pieces[x][y];
				pieces[x][y] = null;
				if(tmp.isFire())
					--fireNum;
				else 
					--waterNum;
				return tmp;
			}
		} else {
			System.out.println("Out of Bound.");
			return null;
		}
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		player = (player == FIRE? WATER: FIRE);
		selected.doneCaputring();
		selected = null;
		moved = false;
	}

	public String winner() {
		if(fireNum == 0 && waterNum == 0)
			return "No one";
		else if(fireNum == 0)
			return "Water";
		else if(waterNum == 0)
			return "Fire";
		else
			return null;
	}
}


