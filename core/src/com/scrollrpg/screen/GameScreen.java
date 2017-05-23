package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.scrollrpg.builder.item.Map;
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
	
	private Map currentMap;
	
	private IntSet downKeys;
	
	private InputMultiplexer inputAdapter;
	
	public GameScreen(MainGame g, I18NBundle i18nstrings, AssetsUtils assets){
		this.g = g;
		this.assets = assets;
		this.i18nstrings = i18nstrings;
		i18nmenu = assets.getManager().get("i18n/hud", I18NBundle.class);
		map_controller = new MapController(assets);
		hud = new HUDUtils();
		mainPlayer = new Player(new Texture(Gdx.files.internal("badlogic.jpg")));
		
		create();
	}
	
	private void onMultipleKeysDown (int mostRecentKeycode){
	    //Keys that are currently down are in the IntSet. Do whatever you like, for example:
		if (downKeys.contains(Input.Keys.W) && downKeys.contains(Input.Keys.A)){
			System.out.println("SALTO_DIAGONAL");
		}
		if (downKeys.contains(Input.Keys.W) && downKeys.contains(Input.Keys.D)){
			System.out.println("SALTO_DIAGONAL");
		}
	    //Alt-F4 to quit:
	    if (downKeys.contains(Input.Keys.ALT_LEFT) || downKeys.contains(Input.Keys.ALT_RIGHT)){
	        if (downKeys.size == 2 && mostRecentKeycode == Input.Keys.F4){
	            Gdx.app.exit();
	        }
	    }
	}
	
	private void create(){
		
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		hudStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		downKeys = new IntSet(20);
		
		inputAdapter = new InputMultiplexer();
		inputAdapter.addProcessor(new InputAdapter(){
			public boolean keyDown (int keycode) {
		        downKeys.add(keycode);
		        if (downKeys.size >= 2){
		            onMultipleKeysDown(keycode);
		        }
		        return true;
		    }
			
			public boolean keyUp (int keycode) {
		        downKeys.remove(keycode);
		        return true;
		    }
		});
		
		inputAdapter.addProcessor(hudStage);
		
		// Nunca olvidar esta linea, sin ella, el stage no funciona
		Gdx.input.setInputProcessor(inputAdapter);
		
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
		
		loadMap();
	}
	
	private void loadMap(){
		currentMap = map_controller.getFirstMap();
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		//System.out.println(mainPlayer.getX() + " - " + mainPlayer.getY() + " -- " + mainPlayer.getWidth() + " - " + mainPlayer.getHeight());
		//System.out.println(stage.getViewport().getCamera().position.toString());
		mainPlayer.update(currentMap, stage);
		mainPlayer.move(stage);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		stage.act(delta);
		hudStage.act(delta);
		stage.draw();
		currentMap.draw(stage.getBatch(), delta);
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
