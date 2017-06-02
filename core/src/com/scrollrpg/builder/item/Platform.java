package com.scrollrpg.builder.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.scrollrpg.builder.BoxBodyBuilder;
import com.scrollrpg.game.entity.Entity;
import com.scrollrpg.game.state.PlayerState;

public class Platform extends Entity{

	public Body body;
	
	private String id;
	
	private PlayerState state;
	
	private Vector2 position, size;
	
	public Platform(PlayerState state, String id, String type, String shapeType, World world, Vector2 position, Vector2 size, float density, float restitution, float friction, Vector2 linearMovement, Texture texture){
		super(texture);
		body = BoxBodyBuilder.createBody(type, shapeType, world, position, size, density, restitution, friction);
		this.id = id;
		body.setFixedRotation(true);
		for(Fixture item : body.getFixtureList()){
			item.setUserData(this);
		}
		this.state = state;
		this.sprite.setBounds(position.x - size.x, position.y - size.y, size.x, size.y);
		this.position = position;
		this.size = size;
	}
	
	public Platform(PlayerState state, String id, String type, String shapeType, World world, Vector2 position, Vector2 size, float density, float restitution, float friction, Vector2 linearMovement, Vector2[] vertices, Texture texture){
		super(texture);
		body = BoxBodyBuilder.createBody(type, shapeType, world, position, size, density, restitution, friction, vertices);
		this.id = id;
		body.setFixedRotation(true);
		for(Fixture item : body.getFixtureList()){
			item.setUserData(this);
		}
		this.state = state;
		this.sprite.setBounds(position.x - size.x, position.y - size.y, size.x, size.y);
		this.position = position;
		this.size = size;
	}
	
	public void hit(){
		System.out.println(id + " :golpea");
		state.touch_floor();
	}
	
	public void nohit(){
		System.out.println(id + " :no golpea");
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		super.draw(batch, parentAlpha);
		this.sprite.setPosition(body.getPosition().x - size.x/2, body.getPosition().y - size.y/2);
	}

}
