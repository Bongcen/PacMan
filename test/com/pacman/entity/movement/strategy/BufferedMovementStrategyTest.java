package com.pacman.entity.movement.strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.pacman.entity.character.PacMan;
import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.entity.movement.NullMovement;
import com.pacman.entity.movement.Stopped;
import com.pacman.geometry.CollisionPolygon;


public class BufferedMovementStrategyTest {

	private Board board;
	private Float speed;
	private CollisionPolygon collisionPolygon;
	private BufferedMovementStrategy movementStrategy;
	private Movement nextMovement;
	private Movement bufferedMovement;
	private Movement currentMovement;
	private MovementBuilder movementBuilder;

	@Before
	public void setUp() {
		board = mock(Board.class);
		nextMovement = mock(Movement.class);
		bufferedMovement = mock(Movement.class);
		currentMovement = mock(Movement.class);
		speed = PacMan.SPEED;
		collisionPolygon = mock(CollisionPolygon.class);
		movementBuilder = mock(MovementBuilder.class);
		
		when(movementBuilder.defaultMovement()).thenReturn(currentMovement);
		
		movementStrategy = new BufferedMovementStrategy(board, movementBuilder, bufferedMovement);
		
		verify(movementBuilder).defaultMovement();
	}
	
	@Test
	public void shouldBeAMovementStrategy() throws Exception {
		assertTrue(movementStrategy instanceof MovementStrategy);
	}
	
	@Test
	public void shouldReturnInitialMovement() throws Exception {
		assertSame(currentMovement, movementStrategy.currentMovement());
	}
	
	@Test
	public void shouldUpdateCurrentDirectionToNext() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		movementStrategy.update(nextMovement, collisionPolygon, speed);
		
		assertSame(nextMovement, movementStrategy.currentMovement());
		assertSame(bufferedMovement, movementStrategy.bufferedMovement);
	}
	
	@Test
	public void shouldBufferDirectionWhenThereIsCollisionToNext() throws Exception {
		when(nextMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		
		movementStrategy.update(nextMovement, collisionPolygon, speed);
		
		assertSame(currentMovement, movementStrategy.currentMovement());
		assertSame(nextMovement, movementStrategy.bufferedMovement);
	}
	
	@Test
	public void shouldReturnBufferedMovementIfCanMoveThere() throws Exception {
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		assertSame(bufferedMovement, result);
	}
	
	@Test
	public void shouldReturnCurrentDirectionIfCanMoveThere() throws Exception {
		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(currentMovement.canMove(collisionPolygon, speed, board)).thenReturn(true);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		assertSame(currentMovement, result);
	}
	
	@Test
	public void shouldReturnStoppedCorrespondenceIfCantMoveAnywhere() throws Exception {
		Stopped stopped = mock(Stopped.class);

		when(bufferedMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(currentMovement.canMove(collisionPolygon, speed, board)).thenReturn(false);
		when(movementBuilder.stoppedFrom(currentMovement)).thenReturn(stopped);
		
		Movement result = movementStrategy.availableMovement(collisionPolygon, speed);
		
		assertSame(stopped, result);
		assertTrue(movementStrategy.bufferedMovement instanceof NullMovement);
	}
	
}
