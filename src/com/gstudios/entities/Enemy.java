package com.gstudios.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.gstudios.main.Game;
import com.gstudios.world.World;

public class Enemy extends Entity {

	public static double speed = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void tick() {
		if (x < Game.player.getX() && World.isFree((int) (x + speed), getY())
				&& !isColidding((int) (x + speed), getY())) {
			x += speed;
		} else if (x > Game.player.getX() && World.isFree((int) (x - speed), getY())
				&& !isColidding((int) (x - speed), getY())) {
			x -= speed;
		}
		if (y < Game.player.getY() && World.isFree(getX(), (int) (y + speed))
				&& !isColidding(getX(), (int) (y + speed))) {
			y += speed;
		} else if (y > Game.player.getY() && World.isFree(getX(), (int) (y - speed))
				&& !isColidding(getX(), (int) (y - speed))) {
			y -= speed;
		}

	}

	public boolean isColidding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e == this)
				continue;

			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}

		}
		return false;
	}
	
	
	
}
