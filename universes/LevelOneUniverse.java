import java.util.ArrayList;

public class LevelOneUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite ball = null;
	private DisplayableSprite pinkTile = null;
	private DisplayableSprite blueTile = null;
	private DisplayableSprite greenTile = null;
	private DisplayableSprite orangeTile = null;
//	private DisplayableSprite coinSprite = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private ArrayList<DisplayableSprite> spritesWithoutTile = new ArrayList<DisplayableSprite>();
	ArrayList<DisplayableSprite> lowerBarriers = new ArrayList<DisplayableSprite>();
	private Background background;
	private int lives = 5;
	private int score = 0;
	
	public final double TILE_START_POINT = 88;
	public final double TILE_STOP_POINT = 160;
	public final double TILE_WIDTH = 75;
	
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();
	
	public LevelOneUniverse () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new PaddleSprite(425,550);
		sprites.add(player1);
		spritesWithoutTile.add(player1);
		ball = new BallSprite(425, 530, 200, 200);
		sprites.add(ball);
	
		background = new AllLevelsBackground();
		ArrayList<DisplayableSprite> barriers = ((AllLevelsBackground)background).getBarriers();
		lowerBarriers = ((AllLevelsBackground)background).getLowerBarrier();
		sprites.addAll(barriers);
		spritesWithoutTile.addAll(barriers);
		backgrounds = new ArrayList<Background>();
		backgrounds.add(background);
			
//		coinSprite = new CoinSprite(TILE_START_POINT+(TILE_WIDTH*5)-37.5, 90);
//		sprites.add(coinSprite);
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+TILE_WIDTH) {
			pinkTile = new TileSprite(i, 100, "res/pinkTile.png" );
			sprites.add(pinkTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+TILE_WIDTH) {
			blueTile = new TileSprite(i, 130, "res/blueTile.png");
			sprites.add(blueTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+TILE_WIDTH) {
			orangeTile = new TileSprite(i, 160, "res/orangeTile.png");
			sprites.add(orangeTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+TILE_WIDTH) {
			greenTile = new TileSprite(i, 190, "res/greenTile.png");
			sprites.add(greenTile);
		}

	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 420;
	}

	public double getYCenter() {
		return 325;
	}

	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}

	public boolean isComplete() {
		return complete;
	}

	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setComplete(boolean complete) {
	}

	public ArrayList<DisplayableSprite> getLowerBarriers(){
		return lowerBarriers;
	}
	
	public ArrayList<Background> getBackgrounds() {
		return backgrounds;
	}	

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}

	public boolean centerOnPlayer() {
		return false;
	}		

	public boolean levelFinished() {
		
		boolean returnValue = true;
		
		for (DisplayableSprite tile : sprites) {
			if (tile instanceof TileSprite) {
				returnValue = false;
				break;
			}
		}
		
		return returnValue;
	}
	
	public ArrayList<DisplayableSprite> getSpritesWithoutTiles() {
		return spritesWithoutTile;
	}
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}

		if (lives == 0) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		if (ball.getDispose() == true) {
			
			if (lives != 0) {
				lives -=1;
			}
			
			if (lives-1 >= 0) {
				ball = new BallSprite(player1.getCenterX(), player1.getCenterY()-50, 200, 200);
				sprites.add(ball);
			}
		}
		
		
		if (ball.getDispose() == true && lives != 0) {
			
		}
		
		disposeSprites();	
//		System.out.println(this.levelFinished());
		
	}

	public String toString() {
		return "";
	}
	
    protected void disposeSprites() {
        
    	//collect a list of sprites to dispose
    	//this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
    		if (sprite.getDispose() == true) {
    			if (sprite instanceof TileSprite) {
    				score += 20;
    			}
    			disposalList.add(sprite);
    		}
    	}
		
		//go through the list of sprites to dispose
		//note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			sprites.remove(sprite);
    	}
		
		//clear disposal list if necessary
    	if (disposalList.size() > 0) {
    		disposalList.clear();
    	}
    }

}
