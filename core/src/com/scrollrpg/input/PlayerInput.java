package com.scrollrpg.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.scrollrpg.game.state.PlayerState;
import com.badlogic.gdx.Input.Keys;

public class PlayerInput {
	
	private InputMultiplexer inputMult;
	
	public PlayerInput(final PlayerState state){
		inputMult = new InputMultiplexer();
        inputMult.addProcessor(new InputAdapter(){

			@Override
			public boolean keyDown(int keycode) {
				if(keycode == Keys.W){
					if(!state.getState().equals("JUMPING") && !state.getState().equals("FALLING"))
						state.jumping();
				}else{
					if(keycode == Keys.A){
						if(!state.getState().equals("FALLING"))
							state.move_left();
					}else{
						if(keycode == Keys.D){
							if(!state.getState().equals("FALLING"))
								state.move_right();
						}
					}
				}
				return super.keyDown(keycode);
			}

			@Override
			public boolean keyUp(int keycode) {
				if(state.getState().equals("MOVING_LEFT") || state.getState().equals("MOVING_RIGHT"))
					state.idle();
				return super.keyUp(keycode);
			}

			@Override
			public boolean keyTyped(char character) {
				return super.keyTyped(character);
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return super.touchDown(screenX, screenY, pointer, button);
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return super.touchUp(screenX, screenY, pointer, button);
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return super.touchDragged(screenX, screenY, pointer);
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return super.mouseMoved(screenX, screenY);
			}

			@Override
			public boolean scrolled(int amount) {
				return super.scrolled(amount);
			}
        	
        });
	}
	
	public InputMultiplexer getInput(){
		return inputMult;
	}

}
