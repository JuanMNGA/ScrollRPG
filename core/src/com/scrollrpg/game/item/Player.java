package com.scrollrpg.game.item;

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
	
	private int platIndex = 0;

	public Player(Texture texture) {
		super(texture);
		state = new PlayerState();
		horizontal_speed = 5;
		jump_speed = 5;
		gravity = -10;
		max_jump_height = 150;
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
		        if(state.getState().equals("MOVING_LEFT") || state.getState().equals("MOVING_RIGHT")){
		        	state.idle();
		        }
		        return true;
		    }
		});
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	// Logica de movimiento
	private void onSingleKeyDown(int mostRecentKeycode){
		switch(mostRecentKeycode){
		case Keys.W:
			// Movimiento cuando se pulsa W (salto)
			if(state.getState().equals("IDLE") && !state.getState().equals("FALLING") && !state.getState().equals("FALLING_LEFT") && !state.getState().equals("FALLING_RIGHT")){
				salto();
			}else{
				if(state.getState().equals("MOVING_LEFT") && !state.getState().equals("FALLING") && !state.getState().equals("FALLING_LEFT") && !state.getState().equals("FALLING_RIGHT")){
					salto_izquierda();
				}else{
					if(state.getState().equals("MOVING_RIGHT") && !state.getState().equals("FALLING") && !state.getState().equals("FALLING_LEFT") && !state.getState().equals("FALLING_RIGHT")){
						salto_derecha();
					}
				}
			}
			break;
		case Keys.A:
			// Movimiento horizontal izquierda
			if(!state.getState().equals("JUMPING") && !state.getState().equals("JUMPING_LEFT") && !state.getState().equals("JUMPING_RIGHT") && !state.getState().equals("FALLING") && !state.getState().equals("FALLING_LEFT") && !state.getState().equals("FALLING_RIGHT"))
				moverIzquierda();
			break;
		case Keys.D:
			// Movimiento horizontal derecha
			if(!state.getState().equals("JUMPING") && !state.getState().equals("JUMPING_LEFT") && !state.getState().equals("JUMPING_RIGHT") && !state.getState().equals("FALLING") && !state.getState().equals("FALLING_LEFT") && !state.getState().equals("FALLING_RIGHT"))
				moverDerecha();
			break;
		default:
			break;
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
	
	private void salto_izquierda(){
		this.moveBy(-horizontal_speed, jump_speed);
		cameraTranslate(new Vector3(-horizontal_speed,jump_speed,0), stage);
		state.jumping_left();
		jump_pos = this.getY() + max_jump_height; 
		alreadyTouching = false;
	}
	
	private void salto_derecha(){
		this.moveBy(horizontal_speed, jump_speed);
		cameraTranslate(new Vector3(horizontal_speed,jump_speed,0), stage);
		state.jumping_right();
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
						this.moveBy(0, jump_speed);
						cameraTranslate(new Vector3(0,jump_speed,0), stage);
					}else{
						state.falling();
					}
				}else{
					if(state.getState().equals("JUMPING_LEFT")){
						if(this.getY() <= jump_pos){
							this.moveBy(-horizontal_speed, jump_speed);
							cameraTranslate(new Vector3(-horizontal_speed,jump_speed,0), stage);
						}else{
							state.falling_left();
						}
					}else{
						if(state.getState().equals("JUMPING_RIGHT")){
							if(this.getY() <= jump_pos){
								this.moveBy(horizontal_speed, jump_speed);
								cameraTranslate(new Vector3(horizontal_speed,jump_speed,0), stage);
							}else{
								state.falling_right();
							}
						}else{
							if(state.getState().equals("FALLING") && !alreadyTouching){
								this.moveBy(0, gravity);
								cameraTranslate(new Vector3(0, gravity, 0), stage);
							}else{
								if(state.getState().equals("FALLING_LEFT") && !alreadyTouching){
									this.moveBy(-horizontal_speed, gravity);
									cameraTranslate(new Vector3(-horizontal_speed, gravity, 0), stage);
								}else{
									if(state.getState().equals("FALLING_RIGHT") && !alreadyTouching){
										this.moveBy(horizontal_speed, gravity);
										cameraTranslate(new Vector3(horizontal_speed, gravity, 0), stage);
									}else{
										this.setY(nearPlatform.getRectangle().getY() + nearPlatform.getRectangle().height);
										stage.getViewport().getCamera().position.y = this.getY();
										state.idle();
									}
								}
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
		for(int i = 0; i < map.getPlatforms().size; ++i){
			if(map.getPlatforms().get(i).getRectangle().overlaps(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight())) && !alreadyTouching){
				state.idle();
				platIndex = i;
				alreadyTouching = true;
			}else{
				if(nearPlatform != null){
					System.out.println((this.getX() + this.getWidth()));
					//if(((this.getX() + this.getWidth()) < nearPlatform.getRectangle().x || (this.getX()) > nearPlatform.getRectangle().width) && (!state.getState().equals("IDLE") && !state.getState().equals("JUMPING") && !state.getState().equals("JUMPING_LEFT") && !state.getState().equals("JUMPING_RIGHT")))
						//state.falling();
				}
			}
		}
		System.out.println(platIndex);
		nearPlatform = map.getPlatforms().get(platIndex);
	}
	
	public InputMultiplexer getInputAdapter(){
		return inputAdapter;
	}

}
