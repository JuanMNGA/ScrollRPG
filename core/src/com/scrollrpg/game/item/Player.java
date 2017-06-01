package com.scrollrpg.game.item;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.scrollrpg.box.sensor.PlayerSensor;
import com.scrollrpg.builder.BoxBodyBuilder;
import com.scrollrpg.game.entity.Entity;
import com.scrollrpg.game.state.PlayerState;

public class Player extends Entity{
	
	public Body body;
	
	private String id;
	
	private final PlayerState state;
	
	private PlayerSensor sensor;
	
	private Vector2 size, position;

	public Player(Texture texture, World world, String id) {
		super(texture);
		size = new Vector2(0.5f, 1f);
		position = new Vector2(-0.5f,1f);
		body = BoxBodyBuilder.createBody("DYNAMIC", "SQUARE",world, position, size , 0.1f, 0.2f, 0.5f);
		this.id = id;
		body.setFixedRotation(true);
		for(Fixture item : body.getFixtureList()){
			item.setUserData(this);
		}
		state = new PlayerState();
		sensor = new PlayerSensor(world, this, "PlayerSensor");
		this.sprite.setBounds(body.getPosition().x, body.getPosition().y, 0.5f, 1);
	}
	
	private void cameraTranslate(Vector3 pos, OrthographicCamera camera){
		camera.position.set(pos);
		camera.update();
	}
	
	public void hit(){
		System.out.println(id + " :golpea");
	}
	
	public void update(OrthographicCamera camera){
		switch(state.getState()){
		default:
			break;
		case "MOVING_LEFT":
			if(body.getLinearVelocity().x > -2f)
				body.applyForceToCenter(new Vector2(-0.5f, 0), true);
			break;
		case "MOVING_RIGHT":
			if(body.getLinearVelocity().x < 2f)
				body.applyForceToCenter(new Vector2(0.5f, 0), true);
			break;
		case "JUMPING":
			body.applyLinearImpulse(new Vector2(0, 0.2f), body.getLocalCenter(), true);
			state.falling();
			break;
		}
		cameraTranslate(new Vector3(body.getPosition().x, body.getPosition().y, 0), camera);
		sensor.body.setTransform(body.getPosition().add(new Vector2(0, -size.y/2)), 0);
	}
	
	public PlayerState getState(){
		return state;
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		this.sprite.setPosition(body.getPosition().x - size.x/2, body.getPosition().y - size.y/2);
		super.draw(batch, parentAlpha);
	}

}
