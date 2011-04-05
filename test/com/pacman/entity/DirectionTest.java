package com.pacman.entity;

import org.junit.Test;
import org.newdawn.slick.Input;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.newdawn.slick.Input.*;
import static com.pacman.entity.Direction.*;

public class DirectionTest {

	@Test
	public void shouldReturnDirectionFromGivenInput() throws Exception {
		validateFromInput(KEY_DOWN, DOWN, UP);
		validateFromInput(KEY_UP, UP, UP);
		validateFromInput(KEY_RIGHT, RIGHT, UP);
		validateFromInput(KEY_LEFT, LEFT, UP);
	}

	@Test
	public void shouldReturnSameDirectionFromInvalidInput() throws Exception {
		validateFromInput(KEY_0, DOWN, DOWN);
		validateFromInput(KEY_A, DOWN, DOWN);
		validateFromInput(KEY_APOSTROPHE, DOWN, DOWN);
	}

	private void validateFromInput(int key, Direction expectedDirection,
			Direction currentDirection) {
		Input input = mock(Input.class);
		when(input.isKeyDown(key)).thenReturn(true);
		assertEquals(expectedDirection, currentDirection.fromInput(input));
	}

}
