package com.scrollrpg.builder.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
	
	private Rectangle platformLogic;
	private Sprite platformTile;
	
	public Platform(){
		platformLogic = new Rectangle();
		platformTile = new Sprite();
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

}
