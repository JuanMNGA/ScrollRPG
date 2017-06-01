package com.scrollrpg.builder;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BoxBodyBuilder {
	
	private BoxBodyBuilder(){
		throw new IllegalAccessError("Utility class");
	}
	
	public static Body createBody(String type, String shapeType, World world, Vector2 position, Vector2 size, float density, float restitution, float friction, Vector2[] vertices){
		BodyDef bodyDef = new BodyDef();
		switch(type){
		case "STATIC":
			bodyDef.type = BodyDef.BodyType.StaticBody;
			break;
		case "DYNAMIC":
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			break;
		case "KINEMATIC":
			bodyDef.type = BodyDef.BodyType.KinematicBody;
			break;
		}
		
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		switch(shapeType){
		case "CIRCLE":
			CircleShape circleShape = new CircleShape();
			fixtureDef.shape = circleShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			circleShape.dispose();
			return body;
		case "EDGE":
			EdgeShape edgeShape = new EdgeShape();
			edgeShape.set(-size.x/2, -size.y/2, size.x/2, -size.y/2);
			fixtureDef.shape = edgeShape;
			body.createFixture(fixtureDef);
			edgeShape.dispose();
			return body;
		case "SQUARE":
			PolygonShape polyShape = new PolygonShape();
			polyShape.setAsBox(size.x/2, size.y/2);
			fixtureDef.shape = polyShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			polyShape.dispose();
			return body;
		default:
			PolygonShape defaultShape = new PolygonShape();
			defaultShape.set(vertices);
			fixtureDef.shape = defaultShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			defaultShape.dispose();
			return body;
		}
	}

	public static Body createBody(String type, String shapeType, World world, Vector2 position, Vector2 size, float density, float restitution, float friction){
		BodyDef bodyDef = new BodyDef();
		switch(type){
		case "STATIC":
			bodyDef.type = BodyDef.BodyType.StaticBody;
			break;
		case "DYNAMIC":
			bodyDef.type = BodyDef.BodyType.DynamicBody;
			break;
		case "KINEMATIC":
			bodyDef.type = BodyDef.BodyType.KinematicBody;
			break;
		}
		
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		switch(shapeType){
		case "CIRCLE":
			CircleShape circleShape = new CircleShape();
			fixtureDef.shape = circleShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			circleShape.dispose();
			return body;
		case "EDGE":
			EdgeShape edgeShape = new EdgeShape();
			edgeShape.set(-size.x/2, -size.y/2, size.x/2, -size.y/2);
			fixtureDef.shape = edgeShape;
			body.createFixture(fixtureDef);
			edgeShape.dispose();
			return body;
		case "SQUARE":
			PolygonShape polyShape = new PolygonShape();
			polyShape.setAsBox(size.x/2, size.y/2);
			fixtureDef.shape = polyShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			polyShape.dispose();
			return body;
		default:
			PolygonShape defaultShape = new PolygonShape();
			//defaultShape.set(vertices);
			defaultShape.setAsBox(size.x/2, size.y/2);
			fixtureDef.shape = defaultShape;
			if(density != 0)
				fixtureDef.density = density;
			if(restitution != 0)
				fixtureDef.restitution = restitution;
			if(friction != 0)
				fixtureDef.friction = friction;
			body.createFixture(fixtureDef);
			defaultShape.dispose();
			return body;
		}
	}
	
	public static Body createSensor(World world, Vector2 position, Vector2 size){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape polyShape = new PolygonShape();
		polyShape.setAsBox(size.x/2, size.y/2);
		fixtureDef.shape = polyShape;
		fixtureDef.density = 0;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		polyShape.dispose();
		
		return body;
	}
}
