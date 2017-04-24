package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.scrollrpg.controller.MapController;
import com.scrollrpg.game.MainGame;
import com.scrollrpg.utils.AssetsUtils;
import com.scrollrpg.utils.SkinUtils;

public class GameScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	
	private AssetsUtils assets;
	private I18NBundle i18nstrings, i18nmenu;
	private MainGame g;
	
	private MapController map_controller;
	
	public GameScreen(MainGame g, I18NBundle i18nstrings, AssetsUtils assets){
		this.g = g;
		this.assets = assets;
		this.i18nstrings = i18nstrings;
		i18nmenu = assets.getManager().get("i18n/hud", I18NBundle.class);
		map_controller = new MapController();
		create();
	}
	
	private void create(){
		
		stage = new Stage(new ExtendViewport(640, 480));
		
		// Nunca olvidar esta linea, sin ella, el stage no funciona
		Gdx.input.setInputProcessor(stage);
		
		skin = new SkinUtils().CreateSkin();
		
		createStageActors();
	}
	
	private void createStageActors(){
		
		float percentageW = 0.2f;
		float percentageH = 0.1f;
		
		Vector2 screenProportion = new Vector2(Gdx.graphics.getWidth()*percentageW, Gdx.graphics.getHeight()*percentageH);
		
		// Create hud
		
		Table hud_layout = new Table();
		
		final TextButton menu_button = new TextButton(i18nmenu.get("hud.main.menu"), skin);
		
		menu_button.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// TODO Auto-generated method stub
				System.out.println(menu_button.getText().toString());
			}
			
		});
		
		hud_layout.add(menu_button).width(screenProportion.x).height(screenProportion.y);
		
		hud_layout.setFillParent(true);
		hud_layout.right().bottom();
		stage.addActor(hud_layout);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		stage.act(delta);
		stage.draw();
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
		stage.dispose();
	}

}
