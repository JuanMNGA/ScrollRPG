package com.scrollrpg.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.scrollrpg.game.entity.Entity;

public class Player extends Entity{

	public Player(Texture texture) {
		super(texture);
	}
	
	public void move(Stage stage){
		if(Gdx.input.isKeyPressed(Keys.A)){
			this.moveBy(-10, 0);
			stage.getViewport().getCamera().translate(-10, 0, 0);
			stage.getViewport().getCamera().update();
		}else{
			if(Gdx.input.isKeyPressed(Keys.D)){
				this.moveBy(10, 0);
				stage.getViewport().getCamera().translate(10, 0, 0);
				stage.getViewport().getCamera().update();
			}else{
				
			}
		}
	}

}
