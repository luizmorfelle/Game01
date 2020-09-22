package com.gstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gstudios.main.Game;
import com.gstudios.world.Camera;
import com.gstudios.world.World;

public class Player extends Entity {

	public boolean right, down, left, up;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double speed = 1.4;
	private boolean moved = false;

	public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[maxIndex + 1];
		leftPlayer = new BufferedImage[maxIndex + 1];

		for (int i = 0; i <= maxIndex; i++) {
			rightPlayer[i] = Game.spriteSheet.getSprite(32 + (16 * i), 0, 16, 16);
			leftPlayer[i] = Game.spriteSheet.getSprite(32 + (16 * i), 16, 16, 16);
		}
	}

	public void tick() {
		moved = false;
		if (right && World.isFree((int)(x+speed), this.getY())) {
			moved = true;
			dir = right_dir;
			x += speed;

		} else if (left && World.isFree((int)(x-speed), this.getY())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		}
		if (up && World.isFree(this.getX(), (int)(y - speed))) {
			moved = true;
			y -= speed;

		} else if (down && World.isFree(this.getX(), (int)(y + speed))) {
			moved = true;
			y += speed;
		}

		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}
		Camera.x =Camera.clamp(World.WIDTH*16 - Game.WIDTH, this.getX() -(Game.WIDTH/2), 0) ;
		Camera.y =Camera.clamp(World.HEIGHT*16 - Game.HEIGHT, this.getY() -(Game.HEIGHT/2), 0) ;
	}

	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
		} else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], getX() - Camera.x, getY() - Camera.y, null);
		}
	}
	

}
