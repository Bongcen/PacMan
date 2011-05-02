package com.pacman.entity.maze;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.newdawn.slick.tiled.TiledMap;

import com.pacman.entity.maze.tile.TileFactory;

public class BoardFactoryTest {

	@Test
	public void shouldCreateBoard() throws Exception {
		String path = "path";
		MapFactory mapFactory = mock(MapFactory.class);
		TileFactory blockFactory = mock(TileFactory.class);
		TiledMap map = mock(TiledMap.class);

		when(mapFactory.from(eq(path))).thenReturn(map);

		BoardFactory boardFactory = new BoardFactory(mapFactory, blockFactory);
		boardFactory.from(path);

		verify(mapFactory).from(path);
		verify(blockFactory).from(eq(map), eq(0));
	}
}
