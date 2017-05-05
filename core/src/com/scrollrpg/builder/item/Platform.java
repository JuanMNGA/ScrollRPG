package com.scrollrpg.builder.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
	
	private Rectangle platformLogic;
	private Sprite platformTile;
	
	public Platform(){
		platformLogic = new Rectangle();
		platformTile = new Sprite();
	}
	
	public Platform(float x, float y, float width, float height, Texture texture){
		platformLogic = new Rectangle();
		platformTile = new Sprite();
		platformLogic.set(x, y, width, height);
		setSprite(texture);
	}
	
	public void setPlatform(float x, float y, float width, float height, Texture texture){
		platformLogic.set(x, y, width, height);
		setSprite(texture);
	}
	
	private void setSprite(Texture texture){
		platformTile.setTexture(texture);
		platformTile.setBounds(platformLogic.getX(), platformLogic.getY(), platformLogic.getWidth(), platformLogic.getHeight());
	}
	
	public Rectangle getRectangle(){
		return platformLogic;
	}
	
	public void draw(Batch batch, float delta){
		batch.draw(platformTile, platformLogic.x, platformLogic.y, platformLogic.width, platformLogic.height);
	}

}
