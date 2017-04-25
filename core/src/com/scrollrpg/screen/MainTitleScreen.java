package com.scrollrpg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.scrollrpg.game.MainGame;
import com.scrollrpg.utils.AssetsUtils;
import com.scrollrpg.utils.SkinUtils;

public class MainTitleScreen implements Screen{
	
	private Stage stage;
	private Skin skin;
	
	private AssetsUtils assets;
	private I18NBundle i18nstrings;
	private MainGame g;
	
	public MainTitleScreen(MainGame g, I18NBundle i18nstrings, AssetsUtils assets){
		this.g = g;
		this.i18nstrings = i18nstrings;
		this.assets = assets;
		create();
	}
	
	private void create(){
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		
		// Nunca olvidar esta linea, sin ella, el stage no funciona
		Gdx.input.setInputProcessor(stage);
		
		skin = new SkinUtils().CreateSkin();
		createStageActors();
	}
	
	private void createStageActors(){
		
		float percentageW = 0.3f;
		float percentageH = 0.1f;
		
		Vector2 screenProportion = new Vector2(Gdx.graphics.getWidth()*percentageW, Gdx.graphics.getHeight()*percentageH);
		
		Table layout = new Table();
		
		final Image image = new Image(new Texture(Gdx.files.internal("badlogic.jpg")));
		final TextButton start = new TextButton(i18nstrings.get("maintitle.button.start"), skin);
		final TextButton exit = new TextButton(i18nstrings.get("maintitle.button.exit"), skin);
		
		image.addAction(Actions.sequence(
				Actions.alpha(0),
				Actions.fadeIn(1f)
				));
		
		start.addAction(Actions.sequence(
				Actions.alpha(0),
				Actions.delay(0.8f),
				Actions.fadeIn(0.7f)
				));
		
		exit.addAction(Actions.sequence(
				Actions.alpha(0),
				Actions.delay(1.3f),
				Actions.fadeIn(0.7f)
				));
		
		start.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start.addAction(Actions.sequence(
						Actions.fadeOut(1f),
						Actions.run(new Runnable(){

							@Override
							public void run() {
								g.setScreen(new GameScreen(g, i18nstrings, assets));
							}
							
						})
						));
				// Esta parte del codigo remueve los listeners de los botones del menu principal, aunque ahora no es necesario, ya que pasaremos de ventana.
				/*
				start.removeListener(this);
				exit.addAction(Actions.sequence(
						Actions.fadeOut(1f),
						Actions.run(new Runnable(){

							@Override
							public void run() {
								exit.remove();
							}
							
						})
						));
				*/
				exit.addAction(Actions.sequence(
						Actions.delay(0.4f),
						Actions.fadeOut(1f)
						));
			}
			
		});
		
		exit.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start.addAction(Actions.sequence(
						Actions.fadeOut(1f)
						));
				exit.addAction(Actions.sequence(
						Actions.delay(0.4f),
						Actions.fadeOut(1f),
						Actions.run(new Runnable(){

							@Override
							public void run() {
								dispose();
								Gdx.app.exit();
							}
							
						})
						));
			}
			
		});
		
		layout.add(image).width(screenProportion.x).height(Gdx.graphics.getHeight()*0.4f);
		layout.row();
		layout.add(start).width(screenProportion.x).height(screenProportion.y);
		layout.row();
		layout.add(exit).width(screenProportion.x).height(screenProportion.y);
		
		layout.setFillParent(true);
		
		stage.addActor(layout);
	}
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		stage.act(delta);
		stage.draw();
		
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
