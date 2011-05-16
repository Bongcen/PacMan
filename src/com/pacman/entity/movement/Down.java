package com.pacman.entity.movement;

import org.newdawn.slick.SlickException;

import com.pacman.geometry.CollisionPolygon;
import com.pacman.graphics.AnimationFactory;

public class Down extends Movement {

	public Down(AnimationFactory animationFactory) throws SlickException {
		super(animationFactory);
	}

	@Override
	public CollisionPolygon move(CollisionPolygon p, Float delta) {
		return p.translate(0, delta);
	}

	@Override
	public String toString() {
		return "down";
	}
}