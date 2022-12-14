import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PaddleSprite implements DisplayableSprite {

	private Image paddle;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 160;
	private double height = 90;
	private boolean dispose = false;	

	
	public PaddleSprite(double centerX, double centerY) {
		
		this.centerX = centerX;
		this.centerY = centerY;
		if (paddle == null) {
			try {
				paddle = ImageIO.read(new File("res/paddle.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}	
		
	}
	
	@Override
	public Image getImage() {
		return paddle;
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
