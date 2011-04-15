package com.pacman.game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pacman.entity.Board;
import com.pacman.entity.character.PacMan;
import com.pacman.entity.character.PacManFactory;
import com.pacman.renderer.Renderer;

public class PacManGame extends BasicGame {

	private PacMan pacMan;
	private final PacManFactory pacManFactory;
	private final Renderer renderer;
	private final Board board;

	public PacManGame(String title, PacManFactory pacManFactory,
			Board board, Renderer renderer) {
		super(title);
		this.pacManFactory = pacManFactory;
		this.board = board;
		this.renderer = renderer;
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		pacMan = pacManFactory.create();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		pacMan.updateDirectionIfRequested(gc.getInput());
		pacMan.move(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		renderer.render(board, g);
		renderer.render(pacMan, g, PacMan.SPEED);
	}

}
