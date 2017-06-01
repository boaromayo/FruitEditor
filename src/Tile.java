package FruitEditor;

import java.awt.image.*;

public class Tile {
	// IMAGE.
	private BufferedImage img;
	
	// IMAGE DIMENSIONS.
	private int imgWidth;
	private int imgHeight;
	
	// ID AND NAME.
	private int id;
	private String name;
	
	// TILE CONDITIONS.
	private boolean solid;
	private boolean transparent;
	private boolean danger;
	
	public Tile() {
		this.img = null/*FruitImgBank.get().
				loadBufferedImage("blank.gif")*/;
		this.id = -1;
		this.name = "None";
		this.solid = false;
		this.transparent = false;
		this.danger = false;
		
		if (img == null) return;
		
		imgWidth = img.getWidth();
		imgHeight = img.getHeight();
	}
	
	public Tile(int id, BufferedImage img, String name) {
		this();
		try {
			this.img = img;
			this.imgWidth = img.getWidth();
			this.imgHeight = img.getHeight();
		} catch (RuntimeException e) {
			System.err.println(
					"ERROR: Could not find image " + name);
		}
		this.id = id;
		this.name = name;
	}
	
	public Tile(int id, BufferedImage img, String name, boolean solid) {
		this(id, img, name);
		this.solid = solid;
	}
	
	public Tile(int id, BufferedImage img, String name, boolean solid, boolean transparent) {
		this(id, img, name, solid);
		this.transparent = transparent;
	}
	
	public void setTile(Tile t) {
		if (t == null) return;
		
		img = t.getImage();
		id = t.getID();
		name = (t.getName() == null) ? "" : t.getName();
		this.setSolid(t.isSolid());
		this.setDanger(t.isDangerous());
		this.setTransparent(t.isTransparent());
	}
	
	public void replace(Tile t1, Tile t2) {
		if (t1 == null || t2 == null) return;
		
		if (!t1.equals(t2)) {
			t1.setTile(t2);
		}
	}
	
	public void setSolid(boolean s) { solid = s; }
	
	public void setTransparent(boolean t) { transparent = t; }
	
	public void setDanger(boolean d) { danger = d; }
	
	public boolean equals(Tile t) {
		if (t == null) return false;
		
		if (this.id == t.id) {
			return true;
		}
		
		return false;
	}
	
	public static boolean compareTo(Tile t1, Tile t2) {
		if (t1 == null && t2 == null) return false;
		
		return t1.equals(t2);
	}
	
	public Tile getTile() { return this; }
	
	public BufferedImage getImage() { return img; }
	
	public int getID() { return id; }
	
	public String getName() { return name; }
	
	public int getWidth() { return imgWidth; }
	
	public int getHeight() { return imgHeight; }
	
	public boolean isSolid() { return solid; }
	
	public boolean isTransparent() { return transparent; }
	
	public boolean isDangerous() { return danger; }
	
}