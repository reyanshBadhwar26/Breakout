import java.util.ArrayList;

public class ShellUniverse implements Universe {

	private boolean complete = false;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite ball = null;
	private DisplayableSprite ballTwo = null;
	private DisplayableSprite orangeTile = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private ArrayList<Background> backgrounds = new ArrayList<Background>();
	private Background background;
	
	public ShellUniverse () {

		this.setXCenter(0);
		this.setYCenter(0);
		player1 = new PaddleSprite(425,550);
		sprites.add(player1);
		ball = new BallSprite(425, 530, 200, 200);
		sprites.add(ball);
		ballTwo = new BallSprite(425, 530, 200, 200);
		sprites.add(ballTwo);
//		for (int ) {
//			
//		}
		orangeTile = new TileSprite(425, 325);
		sprites.add(orangeTile);

		background = new LevelOneBg();
		ArrayList<DisplayableSprite> barriers = ((LevelOneBg)background).getBarriers();
		sprites.addAll(barriers);
		backgrounds =new ArrayList<Background>();
		backgrounds.add(background);
			
	}

	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 425;
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

	public void setComplete(boolean complete) {
		complete = true;
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

	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		
		
	}

	public String toString() {
		return "";
	}

}
