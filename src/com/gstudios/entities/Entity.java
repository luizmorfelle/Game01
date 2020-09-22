package com.gstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gstudios.main.Game;
import com.gstudios.world.Camera;

public class Entity {

	private BufferedImage sprite;
	public static BufferedImage AMMO_EN = Game.spriteSheet.getSprite(6 * 16, 1 * 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spriteSheet.getSprite(7 * 16, 1 * 16, 16, 16);
	public static BufferedImage LIFE_EN = Game.spriteSheet.getSprite(6 * 16, 0 * 16, 16, 16);
	public static BufferedImage GUN_EN = Game.spriteSheet.getSprite(7 * 16, 0 * 16, 16, 16);

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public void tick() {

	}

}
