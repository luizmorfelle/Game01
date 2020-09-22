package com.gstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gstudios.entities.*;

import com.gstudios.main.Game;

public class World {

	public static int TILE_SIZE = 16;
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);

					switch (pixelAtual) {
					case 0xFFFFFFFF:
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
						break;

					case 0xFF0000FF: // Coloca player na posição da cor
						Game.player.setX(xx * 16);
						Game.player.setY(yy * 16);
						break;

					case 0xFF4CFF00:
						Game.entities.add(new Gun(xx * 16, yy * 16, 16, 16, Entity.GUN_EN));
						// gun
						break;
					case 0xFFFF0000:
						Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
						
						// enemy;
						break;
					case 0xFFFFD800:
						Game.entities.add(new Ammo(xx * 16, yy * 16, 16, 16, Entity.AMMO_EN));
						// ammo
						break;
					case 0xFF4800FF:
						Game.entities.add(new Life(xx * 16, yy * 16, 16, 16, Entity.LIFE_EN));
						// life
						break;
					}

				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext + TILE_SIZE -2) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE -2) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE -2) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE -2) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);

		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}

	}
}
