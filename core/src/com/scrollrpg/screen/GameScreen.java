package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.scrollrpg.controller.MapController;
import com.scrollrpg.game.MainGame;
import com.scrollrpg.game.item.Player;
import com.scrollrpg.utils.AssetsUtils;
import com.scrollrpg.utils.HUDUtils;
import com.scrollrpg.utils.SkinUtils;

public class GameScreen implements Screen{
	
	private Stage stage, hudStage;
	private Skin skin;
	
	private AssetsUtils assets;
	private HUDUtils hud;
	private I18NBundle i18nstrings, i18nmenu;
	private MainGame g;
	
	private MapController map_controller;
	
	private Player mainPlayer;
	
	public GameScreen(MainGame g, I18NBundle i18nstrings, AssetsUtils assets){
		this.g = g;
		this.assets = assets;
		this.i18nstrings = i18nstrings;
		i18nmenu = assets.getManager().get("i18n/hud", I18NBundle.class);
		map_controller = new MapController();
		hud = new HUDUtils();
		mainPlayer = new Player(new Texture(Gdx.files.internal("badlogic.jpg")));
		create();
	}
	
	private void create(){
		
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		hudStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		// Nunca olvidar esta linea, sin ella, el stage no funciona
		Gdx.input.setInputProcessor(hudStage);
		
		skin = new SkinUtils().CreateSkin();
		
		createStageActors();
	}
	
	private void createStageActors(){
		
		float percentageW = 0.2f;
		float percentageH = 0.1f;
		
		Vector2 screenProportion = new Vector2(Gdx.graphics.getWidth()*percentageW, Gdx.graphics.getHeight()*percentageH);
		
		final Image imagen = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
		imagen.setFillParent(true);
		
		mainPlayer.setBounds(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 100, 100);
		
		stage.addActor(imagen);
		
		stage.addActor(mainPlayer);
		
		// Create hud
		// Ultima linea de todas, para que el HUD siempre quede arriba
		hudStage = hud.createHUD(hudStage, skin, screenProportion, i18nmenu);
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		mainPlayer.move(stage);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		stage.act(delta);
		hudStage.act(delta);
		stage.draw();
		hudStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
