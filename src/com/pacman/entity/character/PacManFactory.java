package com.pacman.entity.character;

import static com.pacman.entity.direction.Direction.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import com.pacman.entity.direction.Direction;
import com.pacman.geometry.SquarePolygon;

public class PacManFactory {

	private final AnimationFactory animationFactory;

	public PacManFactory(AnimationFactory animationFactory) {
		this.animationFactory = animationFactory;
	}

	public PacMan create() throws SlickException {
		Map<Direction, Animation> animationMap = new HashMap<Direction, Animation>();
		animationMap.put(DOWN, animationFactory.createFromPath(70,
				createPathsFrom(DOWN)));
		animationMap.put(UP, animationFactory.createFromPath(70,
				createPathsFrom(UP)));
		animationMap.put(LEFT, animationFactory.createFromPath(70,
				createPathsFrom(LEFT)));
		animationMap.put(RIGHT, animationFactory.createFromPath(70,
				createPathsFrom(RIGHT)));
		return new PacMan(new SquarePolygon(24.5f, 24.5f, 26f), animationMap, RIGHT);
	}

	private String[] createPathsFrom(Direction direction) {
		String lowerDirection = direction.toString().toLowerCase();
		String prefix = "data" + File.separator + "pacman" + File.separator
				+ lowerDirection + File.separator + "Pacman_" + lowerDirection
				+ "-";
		String extension = ".png";

		String[] paths = new String[4];
		for (int i = 0; i < paths.length; i++) {
			paths[i] = prefix + i + extension;
		}
		return paths;
	}

}
