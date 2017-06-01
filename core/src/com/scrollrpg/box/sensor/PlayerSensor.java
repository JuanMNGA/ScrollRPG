package com.scrollrpg.box.sensor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.scrollrpg.builder.BoxBodyBuilder;
import com.scrollrpg.game.item.Player;

public class PlayerSensor {

	public Body body;
	
	private String id;
	
	public PlayerSensor(World world, Player player, String id){
		body = BoxBodyBuilder.createSensor(world, player.body.getPosition(), new Vector2(0.5f, 0.1f));
		this.id = id;
		body.setFixedRotation(true);
		for(Fixture item : body.getFixtureList()){
			item.setUserData(this);
		}
	}
	
	public void hit(){
		System.out.println(id + " :golpea");
	}

}