package entity.item;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;;

public class Item extends Entity {
	private GamePanel gp;
	private BufferedImage image, image2, image3;
	private String description = "";
	private int price;
	// Weapon	

	public Item(GamePanel gp) {
		super(gp);
	}

	// SETTERS AND GETTERS
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage2() {
		return image2;
	}

	public void setImage2(BufferedImage image2) {
		this.image2 = image2;
	}

	public BufferedImage getImage3() {
		return image3;
	}

	public void setImage3(BufferedImage image3) {
		this.image3 = image3;
	}

	public GamePanel getGp() {
		return gp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public void interact() {}

}
