package com.scrollrpg.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.IntSet;
import com.scrollrpg.builder.item.Map;
import com.scrollrpg.builder.item.Platform;
import com.scrollrpg.game.entity.Entity;
import com.scrollrpg.game.state.PlayerState;

public class Player extends Entity{
	
	private PlayerState state;
	
	private float horizontal_speed;
	private float jump_speed;
	private float gravity;
	private float max_jump_height;
	private float jump_pos;
	
	private boolean alreadyTouching = false;
	
	private Platform nearPlatform = null;
	
	private IntSet downKeys;
	
	private InputMultiplexer inputAdapter;
	
	private Stage stage;

	public Player(Texture texture) {
		super(texture);
		state = new PlayerState();
		horizontal_speed = 5;
		jump_speed = 5;
		gravity = -10;
		max_jump_height = 100;
		jump_pos = this.getY() + max_jump_height;
		
		downKeys = new IntSet(20);
		
		inputAdapter = new InputMultiplexer();
		inputAdapter.addProcessor(new InputAdapter(){
			public boolean keyDown (int keycode) {
		        downKeys.add(keycode);
		        onSingleKeyDown(keycode);
		        return true;
		    }
			
			public boolean keyUp (int keycode) {
		        downKeys.remove(keycode);
		        if(!state.getState().equals("JUMPING"))
		        	state.idle();
		        return true;
		    }
		});
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void onSingleKeyDown(int mostRecentKeycode){
		switch(mostRecentKeycode){
		case Keys.W:
			if(state.getState().equals("IDLE") && !state.getState().equals("FALLING")){
				salto();
			}
			break;
		case Keys.A:
			if(!state.getState().equals("JUMPING"))
				moverIzquierda();
			break;
		case Keys.D:
			if(!state.getState().equals("JUMPING"))
				moverDerecha();
			break;
		}
		
	}
	
	private void onMultipleKeysDown (int mostRecentKeycode){
	    //Keys that are currently down are in the IntSet. Do whatever you like, for example:
		if (downKeys.contains(Input.Keys.W) && downKeys.contains(Input.Keys.A)){
			System.out.println("SALTO_DIAGONAL");
		}
		if (downKeys.contains(Input.Keys.W) && downKeys.contains(Input.Keys.D)){
			System.out.println("SALTO_DIAGONAL");
		}
	    //Alt-F4 to quit:
	    if (downKeys.contains(Input.Keys.ALT_LEFT) || downKeys.contains(Input.Keys.ALT_RIGHT)){
	        if (downKeys.size == 2 && mostRecentKeycode == Input.Keys.F4){
	            Gdx.app.exit();
	        }
	    }
	}
	
	private void moverDerecha(){
		state.move_right();
	}
	
	private void salto(){
		this.moveBy(0, jump_speed);
		cameraTranslate(new Vector3(0,jump_speed,0), stage);
		state.jumping();
		jump_pos = this.getY() + max_jump_height; 
		alreadyTouching = false;
	}
	
	private void moverIzquierda(){
		state.move_left();
	}
	
	public void update(Map map, Stage stage){
		touchAnyPlatform(map);
		if(state.getState().equals("MOVING_LEFT")){
			this.moveBy(-horizontal_speed, 0);
			cameraTranslate(new Vector3(-horizontal_speed,0,0), stage);
		}else{
			if(state.getState().equals("MOVING_RIGHT")){
				this.moveBy(horizontal_speed, 0);
				cameraTranslate(new Vector3(horizontal_speed,0,0), stage);
			}else{
				if(state.getState().equals("JUMPING")){
					if(this.getY() <= jump_pos){
						System.out.println(jump_pos);
						this.moveBy(0, jump_speed);
						cameraTranslate(new Vector3(0,jump_speed,0), stage);
					}else{
						state.falling();
					}
				}else{
					if(state.getState().equals("JUMPING_LEFT")){
						if(this.getY() <= jump_pos){
							System.out.println(jump_pos);
							this.moveBy(-horizontal_speed, jump_speed);
							cameraTranslate(new Vector3(-horizontal_speed,jump_speed,0), stage);
						}else{
							state.falling();
						}
					}else{
						if(state.getState().equals("JUMPING_RIGHT")){
							if(this.getY() <= jump_pos){
								System.out.println(jump_pos);
								this.moveBy(horizontal_speed, jump_speed);
								cameraTranslate(new Vector3(horizontal_speed,jump_speed,0), stage);
							}else{
								state.falling();
							}
						}else{
							if(state.getState().equals("FALLING") && !alreadyTouching){
								this.moveBy(0, gravity);
								cameraTranslate(new Vector3(0, gravity, 0), stage);
							}else{
								this.setY(nearPlatform.getRectangle().height);
								stage.getViewport().getCamera().position.y = this.getY();
								state.idle();
							}
						}
					}
				}
			}
		}
	}
	
	private void cameraTranslate(Vector3 pos, Stage stage){
		stage.getViewport().getCamera().translate(pos.x, pos.y, pos.z);
		stage.getViewport().getCamera().update();
	}
	
	private void touchAnyPlatform(Map map){
		int platIndex = 0;
		for(int i = 0; i < map.getPlatforms().size; ++i){
			System.out.println(this.getY() - 1);
			if(map.getPlatforms().get(i).getRectangle().overlaps(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight())) && !alreadyTouching){
				state.idle();
				platIndex = i;
				alreadyTouching = true;
			}
			System.out.println(platIndex);
		}
		nearPlatform = map.getPlatforms().get(platIndex);
	}
	
	public InputMultiplexer getInputAdapter(){
		return inputAdapter;
	}

}
