package com.scrollrpg.game;

import com.badlogic.gdx.Game;
import com.scrollrpg.screen.Loader;

public class MainGame extends Game {
	
	Loader load;
	
	@Override
	public void create () {
		load = new Loader(this);
		setScreen(load);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		//assets.dispose();
		super.dispose();
	}
}
