import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private boolean blocked;
	
	public Tile(BufferedImage image, boolean blocked) {
		this.image = image;
		this.blocked = blocked;
		
		System.out.println("==============BLOCK!!!!!!!!!================");
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public boolean isBlocked() {
		return blocked;
	}
		

}
