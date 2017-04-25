package com.scrollrpg.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.I18NBundle;

public class HUDUtils {
	
	private boolean isHudCreated = true;
	
	public HUDUtils(){
		
	}
	
	public Stage createHUD(Stage stage, Skin skin, Vector2 screenProportion, I18NBundle i18nmenu){
		Table hud_layout = new Table();
		
		final Window secondLevelMenu = new Window("", skin);
		secondLevelMenu.setMovable(false);
		
		final TextButton menu_button = new TextButton(i18nmenu.get("hud.main.menu"), skin);
		final TextButton stats_button = new TextButton(i18nmenu.get("hud.side.menu.stats"), skin);
		final TextButton inventory_button = new TextButton(i18nmenu.get("hud.side.menu.inventory"), skin);
		final TextButton options_button = new TextButton(i18nmenu.get("hud.side.menu.options"), skin);
		
		// Listeners
		final ChangeListener stats_button_listener = new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(stats_button.getText().toString());
			}
			
		};
		
		final ChangeListener inventory_button_listener = new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(inventory_button.getText().toString());
			}
			
		};
		
		final ChangeListener options_button_listener = new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(options_button.getText().toString());
			}
			
		};
		
		secondLevelMenu.addAction(Actions.sequence(
				Actions.alpha(0)
				));
		
		menu_button.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(menu_button.getText().toString());
				if(isHudCreated){
					secondLevelMenu.addAction(Actions.sequence(
							Actions.fadeIn(0.5f)
							));
					stats_button.addListener(stats_button_listener);
					inventory_button.addListener(inventory_button_listener);
					options_button.addListener(options_button_listener);
					isHudCreated = false;
				}else{
					stats_button.removeListener(stats_button_listener);
					inventory_button.removeListener(inventory_button_listener);
					options_button.removeListener(options_button_listener);
					secondLevelMenu.addAction(Actions.sequence(
							Actions.fadeOut(0.5f)
							));
					isHudCreated = true;
				}
			}
			
		});
		
		secondLevelMenu.add(stats_button).width(screenProportion.x).height(screenProportion.y);
		secondLevelMenu.row();
		secondLevelMenu.add(inventory_button).width(screenProportion.x).height(screenProportion.y);
		secondLevelMenu.row();
		secondLevelMenu.add(options_button).width(screenProportion.x).height(screenProportion.y);
		secondLevelMenu.row();
		
		hud_layout.add(secondLevelMenu);
		
		hud_layout.add().width(screenProportion.x).height(screenProportion.y);
		
		hud_layout.row();
		
		hud_layout.add().width(screenProportion.x).height(screenProportion.y);
		
		hud_layout.add(menu_button).width(screenProportion.x).height(screenProportion.y);
		
		hud_layout.setFillParent(true);
		hud_layout.right().bottom();
		stage.addActor(hud_layout);
		return stage;
	}
}
