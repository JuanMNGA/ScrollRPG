package com.scrollrpg.game.state;

public class PlayerState {
	private enum States{IDLE, MOVING_LEFT, MOVING_RIGHT, JUMPING, JUMPING_LEFT, JUMPING_RIGHT, FALLING, FALLING_LEFT, FALLING_RIGHT, TOUCH_FLOOR};
	
	private States currentState;
	
	public PlayerState(){
		currentState=States.FALLING;
	}
	
	public void idle(){
		currentState=States.IDLE;
		debugCurrent();
	}
	
	public void move_left(){
		currentState=States.MOVING_LEFT;
		debugCurrent();
	}
	
	public void move_right(){
		currentState=States.MOVING_RIGHT;
		debugCurrent();
	}
	
	public void jumping(){
		currentState=States.JUMPING;
		debugCurrent();
	}
	
	public void jumping_left(){
		currentState=States.JUMPING_LEFT;
		debugCurrent();
	}
	
	public void jumping_right(){
		currentState=States.JUMPING_RIGHT;
		debugCurrent();
	}
	
	public void falling(){
		currentState=States.FALLING;
		debugCurrent();
	}
	
	public void falling_left(){
		currentState=States.FALLING_LEFT;
		debugCurrent();
	}
	
	public void falling_right(){
		currentState=States.FALLING_RIGHT;
		debugCurrent();
	}
	
	public void touch_floor(){
		currentState=States.TOUCH_FLOOR;
		debugCurrent();
	}
	
	private void debugCurrent(){
		System.out.println(currentState.name());
	}
	
	public String getState(){
		return currentState.name();
	}
}
