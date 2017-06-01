package com.scrollrpg.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.scrollrpg.box.sensor.PlayerSensor;
import com.scrollrpg.builder.item.Platform;

public class CustomContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA == null || fixB == null) return;
		if(fixA.getUserData() == null || fixB.getUserData() == null) return;
		
		if(isPlatformContact(fixA, fixB)){
			if(fixA.getUserData() instanceof Platform){
				Platform plA = (Platform) fixA.getUserData();
				PlayerSensor plB = (PlayerSensor) fixB.getUserData();
			
				plA.hit();
			}else{
				PlayerSensor plA = (PlayerSensor) fixA.getUserData();
				Platform plB = (Platform) fixB.getUserData();
			
				plB.hit();
			}
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA == null || fixB == null) return;
		if(fixA.getUserData() == null || fixB.getUserData() == null) return;
		
		if(isPlatformContact(fixA, fixB)){
			if(fixA.getUserData() instanceof Platform){
				Platform plA = (Platform) fixA.getUserData();
				PlayerSensor plB = (PlayerSensor) fixB.getUserData();
			
				plA.nohit();
			}else{
				PlayerSensor plA = (PlayerSensor) fixA.getUserData();
				Platform plB = (Platform) fixB.getUserData();
			
				plB.nohit();
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isPlatformContact(Fixture a, Fixture b){
		if(a.getUserData() instanceof PlayerSensor || b.getUserData() instanceof PlayerSensor){
			if(a.getUserData() instanceof Platform || b.getUserData() instanceof Platform){
				return true;
			}
		}
		return false;
	}

}
