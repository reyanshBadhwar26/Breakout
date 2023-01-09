import java.util.ArrayList;

public class ShellUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite ball = null;
	private DisplayableSprite pinkTile = null;
	private DisplayableSprite blueTile = null;
	private DisplayableSprite greenTile = null;
	private DisplayableSprite orangeTile = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private ArrayList<DisplayableSprite> spritesWithoutTile = new ArrayList<DisplayableSprite>();
	ArrayList<DisplayableSprite> lowerBarriers = new ArrayList<DisplayableSprite>();
	private Background background;
	private int lives = 5;
	private int score = 0;
	
	public final double TILE_START_POINT = 88;
	public final double TILE_STOP_POINT = 775;
	
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();
	
	public ShellUniverse () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new PaddleSprite(425,550);
		sprites.add(player1);
		spritesWithoutTile.add(player1);
		ball = new BallSprite(425, 530, 200, 200);
		sprites.add(ball);
		spritesWithoutTile.add(ball);
	
		background = new AllLevelsBackground();
		ArrayList<DisplayableSprite> barriers = ((AllLevelsBackground)background).getBarriers();
		lowerBarriers = ((AllLevelsBackground)background).getLowerBarrier();
		sprites.addAll(barriers);
		spritesWithoutTile.addAll(barriers);
		backgrounds = new ArrayList<Background>();
		backgrounds.add(background);
			
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+75) {
			pinkTile = new TileSprite(i, 100, "res/pinkTile.png" );
			sprites.add(pinkTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+75) {
			blueTile = new TileSprite(i, 130, "res/blueTile.png");
			sprites.add(blueTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+75) {
			orangeTile = new TileSprite(i, 160, "res/orangeTile.png");
			sprites.add(orangeTile);
		}
		
		for (double i = TILE_START_POINT; i <= TILE_STOP_POINT; i = i+75) {
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
		complete = true;
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

	public ArrayList<DisplayableSprite> getSpritesWithoutTiles() {
		return spritesWithoutTile;
	}
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
		
		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
//		if (ball.getDispose() == true) {
//			if (lives > 0) {
//				lives -= 1;
//				ball = new BallSprite(425, 530, 200, 200);
//				sprites.add(ball);
//				spritesWithoutTile.add(ball);
//			}
//		}
		
		disposeSprites();	
		
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
