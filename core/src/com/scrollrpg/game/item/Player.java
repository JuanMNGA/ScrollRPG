package com.scrollrpg.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.scrollrpg.builder.item.Map;
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

	public Player(Texture texture) {
		super(texture);
		state = new PlayerState();
		horizontal_speed = 5;
		jump_speed = 5;
		gravity = -10;
		max_jump_height = 100;
		jump_pos = this.getY() + max_jump_height;
	}
	
	public void move(Stage stage){
		if(Gdx.input.isKeyPressed(Keys.A)){
			this.moveBy(-horizontal_speed, 0);
			cameraTranslate(new Vector3(-horizontal_speed,0,0), stage);
			state.move_left();
		}else{
			if(Gdx.input.isKeyPressed(Keys.D)){
				this.moveBy(horizontal_speed, 0);
				cameraTranslate(new Vector3(horizontal_speed,0,0), stage);
				state.move_right();
			}else{
				if(Gdx.input.isKeyPressed(Keys.W) && state.getState().equals("IDLE")){
					this.moveBy(0, jump_speed);
					cameraTranslate(new Vector3(0,jump_speed,0), stage);
					state.jumping();
					jump_pos = this.getY() + max_jump_height; 
					alreadyTouching = false;
				}else{
					if(state.getState().equals("MOVING_LEFT") || state.getState().equals("MOVING_RIGHT"))
						state.idle();
				}
			}
		}
	}
	
	public void update(Map map, Stage stage){
		if(state.getState().equals("JUMPING")){
			if(this.getY() <= jump_pos){
				System.out.println(jump_pos);
				this.moveBy(0, jump_speed);
				cameraTranslate(new Vector3(0,jump_speed,0), stage);
			}else{
				state.falling();
			}
		}
		if(state.getState().equals("FALLING") && !alreadyTouching){
			touchAnyPlatform(map);
			this.moveBy(0, gravity);
			cameraTranslate(new Vector3(0, gravity, 0), stage);
		}
	}
	
	private void cameraTranslate(Vector3 pos, Stage stage){
		stage.getViewport().getCamera().translate(pos.x, pos.y, pos.z);
		stage.getViewport().getCamera().update();
	}
	
	private void touchAnyPlatform(Map map){
		for(int i = 0; i < map.getPlatforms().size; ++i){
			System.out.println(this.getY() - 1);
			if(map.getPlatforms().get(i).getRectangle().contains(this.getX(), this.getY() - 1)){
				state.idle();
				this.setY(map.getPlatforms().get(i).getRectangle().height);
				alreadyTouching = true;
			}else{
				state.falling();
			}
		}
	}

}
