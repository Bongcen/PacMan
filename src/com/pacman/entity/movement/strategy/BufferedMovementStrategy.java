package com.pacman.entity.movement.strategy;

import com.pacman.entity.maze.Board;
import com.pacman.entity.movement.Movement;
import com.pacman.entity.movement.MovementBuilder;
import com.pacman.entity.movement.NullMovement;
import com.pacman.geometry.CollisionPolygon;

public class BufferedMovementStrategy implements MovementStrategy {

	private final Board board;
	protected Movement currentMovement;
	protected Movement bufferedMovement;
	private final MovementBuilder movementBuilder;

	public BufferedMovementStrategy(Board board, MovementBuilder movementBuilder, Movement bufferedMovement) {
		this.board = board;
		this.movementBuilder = movementBuilder;
		this.currentMovement = movementBuilder.defaultMovement();
		this.bufferedMovement = bufferedMovement;
	}

	@Override
	public Movement currentMovement() {
		return currentMovement;
	}

	@Override
	public Movement next(Movement movement,
			CollisionPolygon collisionPolygon, Float speed) {
		if (movement.canMove(collisionPolygon, speed, board)) {
			currentMovement = movement;
			bufferedMovement = new NullMovement();
		} else if (bufferedMovement.canMove(collisionPolygon, speed, board)) {
			currentMovement = bufferedMovement;
			bufferedMovement = new NullMovement();
		} else if (currentMovement.canMove(collisionPolygon, speed, board)) {
			bufferedMovement = movement;
		} else {
			//currentMovement.stop();
		}
		
		return currentMovement;
	}
}
