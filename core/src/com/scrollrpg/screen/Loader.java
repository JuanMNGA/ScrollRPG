package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.scrollrpg.game.MainGame;
import com.scrollrpg.utils.AssetsUtils;

public class Loader implements Screen{
	
	I18NBundle i18nstrings;
	SpriteBatch batch;
	
	private MainGame g;
	private AssetsUtils assets;
	
	public Loader(MainGame g){
		this.g = g;
		assets = new AssetsUtils();
		assets.load();
		batch = new SpriteBatch();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(assets.update()){
			g.setScreen(new MainTitleScreen(g, assets.getManager().get("i18n/messages", I18NBundle.class), assets));
		}else{
			batch.begin();
			if(assets.isLoaded("arial.ttf")){
				i18nstrings = assets.getManager().get("i18n/messages", I18NBundle.class);
				assets.getManager().get("arial.ttf", BitmapFont.class).draw(batch, i18nstrings.format("maintitle.label.loading"), 100, 100);
			}
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
