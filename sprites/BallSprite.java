import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BallSprite implements DisplayableSprite {

	private Image ball;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 40;
	private double height = 40;
	private boolean dispose = false;	

	//PIXELS PER SECOND PER SECOND
	private double accelerationX = 0;
	private double accelerationY = 0;		
	private double velocityX = 300;
	private double velocityY = 300;
	
	//required for advanced collision detection
	private CollisionDetection collisionDetection;
	private VirtualSprite virtual = new VirtualSprite();

	public BallSprite(double centerX, double centerY, double velocityX, double velocityY) {
		
		this.centerX = centerX;
		this.centerY = centerY;
		
		collisionDetection = new CollisionDetection();

		this.velocityX = velocityX;
		this.velocityY = velocityY;

		if (ball == null) {
			try {
				ball = ImageIO.read(new File("res/ball.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}	
		
	}
	
	@Override
	public Image getImage() {
		return ball;
	}

	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public double getMinX() {
		return centerX - (width / 2);
	}

	@Override
	public double getMaxX() {
		return centerX + (width / 2);
	}

	@Override
	public double getMinY() {
		return centerY - (height / 2);
	}

	@Override
	public double getMaxY() {
		return centerY + (height / 2);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getCenterX() {
		return centerX;
	}

	@Override
	public double getCenterY() {
		return centerY;
	}

	@Override
	public boolean getDispose() {
		return dispose;
	}

	@Override
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}
	//Allow other objects to get / set velocity and acceleration
	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}

	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	private void checkCollisionWithTile(ArrayList<DisplayableSprite> sprites) {

		for (DisplayableSprite sprite : sprites) {
			if (sprite instanceof TileSprite) {
				if (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), 
						this.getMaxX(), this.getMaxY(), 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {					
					sprite.setDispose(true);
					
				}
			}
		}			
	}
	
	private boolean checkCollisionWithLowerBarrier(ArrayList<DisplayableSprite> sprites) {

		//deltaX and deltaY represent the potential change in position
		boolean colliding = false;

		for (DisplayableSprite sprite : sprites) {
				if (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), 
						this.getMaxX(), this.getMaxY(), 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					colliding = true;
					break;					
				}
			}	
		return colliding;		
	}
	
	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		checkCollisionWithTile(universe.getSprites());
		
		if (checkCollisionWithLowerBarrier(universe.getLowerBarriers()) == true) {
			this.setDispose(true);
		}
		
		collisionDetection.calculate2DBounce(virtual, this, universe.getSpritesWithoutTiles(), velocityX, velocityY, actual_delta_time);
		this.centerX = virtual.getCenterX();
		this.centerY = virtual.getCenterY();
		this.velocityX = virtual.getVelocityX();
		this.velocityY = virtual.getVelocityY();			

		this.velocityX = this.velocityX + accelerationX * 0.001 * actual_delta_time;
		this.velocityY = this.velocityY + accelerationY * 0.001 * actual_delta_time;
	}

}
