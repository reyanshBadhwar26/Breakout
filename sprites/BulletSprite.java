import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BulletSprite implements DisplayableSprite{

	private Image coin;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 0;
	private double height = 0;
	private boolean dispose = false;	
	
	public BulletSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (coin == null) {
			try {
				coin = ImageIO.read(new File("res/bullet.png"));
				this.height = 30;
				this.width = 30;
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}	
	}
	@Override
	public Image getImage() {
		return coin;
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

	private void checkCollisionWithLowerBarrier(ArrayList<DisplayableSprite> sprites) {

		for (DisplayableSprite sprite : sprites) {
				if (CollisionDetection.overlaps(this.getMinX(), this.getMinY(), 
						this.getMaxX(), this.getMaxY(), 
						sprite.getMinX(),sprite.getMinY(), 
						sprite.getMaxX(), sprite.getMaxY())) {
					this.setDispose(true);
					break;					
				}
			}	
	}
	
	
	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		double velocityY = 0;
		velocityY = velocityY + 600 * 0.02 * actual_delta_time;
		this.centerY += actual_delta_time * 0.001 * velocityY;
		
		checkCollisionWithLowerBarrier(universe.getSpritesWithoutTiles());
	}

}
