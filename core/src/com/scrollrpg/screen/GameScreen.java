package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.scrollrpg.builder.item.Map;
import com.scrollrpg.constants.Constants;
import com.scrollrpg.controller.MapController;
import com.scrollrpg.game.MainGame;
import com.scrollrpg.game.item.Player;
import com.scrollrpg.input.PlayerInput;
import com.scrollrpg.listener.CustomContactListener;
import com.scrollrpg.utils.AssetsUtils;
import com.scrollrpg.utils.HUDUtils;
import com.scrollrpg.utils.SkinUtils;

public class GameScreen implements Screen{
	
	private Stage hudStage;
	private Skin skin;
	
	@SuppressWarnings("unused")
	private AssetsUtils assets;
	private HUDUtils hud;
	@SuppressWarnings("unused")
	private I18NBundle i18nstrings, i18nmenu;
	@SuppressWarnings("unused")
	private MainGame g;
	
	private MapController map_controller;
	
	private Player mainPlayer;
	
	private Map currentMap;
	
	private World world;
	
	private SpriteBatch batch;
	
	Box2DDebugRenderer debugRenderer;
	
	private OrthographicCamera camera;
	
	private PlayerInput playerInput;
	
	private FPSLogger fpslogger;
	
	public GameScreen(MainGame g, I18NBundle i18nstrings, AssetsUtils assets){
		this.g = g;
		this.assets = assets;
		this.i18nstrings = i18nstrings;
		i18nmenu = assets.getManager().get("i18n/hud", I18NBundle.class);
		hud = new HUDUtils();
		
		// Elementos normales de LibGDX
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/Constants.PIXELS_TO_METERS,Gdx.graphics.getHeight()/Constants.PIXELS_TO_METERS);
		
		debugRenderer = new Box2DDebugRenderer();
		
		world = new World(new Vector2(0, -10f), true);
		world.setContactListener(new CustomContactListener());
		mainPlayer = new Player(assets.getManager().get("textures/bricks.png", Texture.class), world, "Player");
		map_controller = new MapController(assets, world, mainPlayer.getState());
		
		fpslogger = new FPSLogger();
		create();
	}
	
	private void create(){
		
		hudStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		playerInput = new PlayerInput(mainPlayer.getState());
		
		playerInput.getInput().addProcessor(hudStage);
		
		// Nunca olvidar esta linea, sin ella, el stage no funciona
		Gdx.input.setInputProcessor(playerInput.getInput());
		
		skin = new SkinUtils().CreateSkin();
		
		createStageActors();
	}
	
	private void createStageActors(){
		
		float percentageW = 0.2f;
		float percentageH = 0.1f;
		
		Vector2 screenProportion = new Vector2(Gdx.graphics.getWidth()*percentageW, Gdx.graphics.getHeight()*percentageH);
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
		world.step(1f/60f, 6, 2);
		//camera.update();
		mainPlayer.update(camera);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		batch.begin();
		currentMap.draw(batch, delta);
		mainPlayer.draw(batch, delta);
		batch.end();
		batch.setProjectionMatrix(camera.combined);
		hudStage.act(delta);
		hudStage.draw();
		debugRenderer.render(world, batch.getProjectionMatrix());
		//fpslogger.log();
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
		
	}

}
