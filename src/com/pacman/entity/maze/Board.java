package com.pacman.entity.maze;

import static com.pacman.game.properties.LayerProperties.*;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.Collidable;
import com.pacman.entity.maze.tile.Tile;
import com.pacman.geometry.Point;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.renderer.Renderable;

public class Board implements Renderable, Collidable {

	private final TiledMap map;
	private List<Tile> walls;

	protected Board(TiledMap map, List<Tile> walls) {
		this.map = map;
		this.walls = walls;
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0; i < map.getLayerCount(); i++) {
			if (isLayerVisible(i)) {
				map.render(0, 0, i);
			}
		}
		for (Tile tile : walls) {
			g.draw(tile.getPolygon());
		}
	}

	@Override
	public Point getPosition() {
		return new Point(0f, 0f);
	}

	@Override
	public boolean isCollidingWith(CollisionPolygon collidable) {
		for (Tile tile : walls) {
			if (tile.isCollidingWith(collidable)) {
				return true;
			}
		}
		return false;
	}

	private Boolean isLayerVisible(int layerIndex) {
		return Boolean.valueOf(map.getLayerProperty(layerIndex, VISIBLE
				.property(), VISIBLE.defaultValue()));
	}

}
