import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BallSprite implements DisplayableSprite {

	private Image ball;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 40;
	private double height = 40;
	private boolean dispose = false;	

	private final double VELOCITY = 200;

	public BallSprite(double centerX, double centerY) {
		
		this.centerX = centerX;
		this.centerY = centerY;
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

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
	}

}
