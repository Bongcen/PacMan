package com.pacman.entity.movement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.Down;
import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.MovementAnimationFactory;

public class DownTest {

	@Test
	public void shouldMovePolygonToTheLeft() throws Exception {
		Float delta = 1f;
		MovementAnimationFactory animationFactory = mock(MovementAnimationFactory.class);
		CollisionPolygon polygon = mock(CollisionPolygon.class);
		CollisionPolygon response = mock(CollisionPolygon.class);

		when(polygon.translate(0, delta)).thenReturn(response);

		Movement movement = new Down(animationFactory);

		assertSame(response, movement.move(polygon, delta));

		verify(polygon).translate(0, delta);
	}
}
