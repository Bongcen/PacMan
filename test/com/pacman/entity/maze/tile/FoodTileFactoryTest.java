package com.pacman.entity.maze.tile;

import static java.io.File.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.collision.PolygonFactory;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.ImageFactory;


public class FoodTileFactoryTest {

	@Test
	public void shouldCreateFoodTile() throws Exception {
		PolygonFactory polygonFactory = mock(PolygonFactory.class);
		CollisionPolygon collisionPolygon = mock(CollisionPolygon.class);
		TiledMap map = mock(TiledMap.class);
		ImageFactory imageFactory = mock(ImageFactory.class);
		
		TileFactory factory = new FoodTileFactory(null, polygonFactory, imageFactory);
		
		when(map.getTileWidth()).thenReturn(25);
		when(polygonFactory.from(35f, 35f, 6f)).thenReturn(collisionPolygon);
		
		factory.createTile(1, 1, map);
		
		verify(map).getTileWidth();
		verify(polygonFactory).from(35f, 35f, 6f);
		verify(imageFactory).from("data" + separator + "maze" + separator + "food.png");
	}
	
}
