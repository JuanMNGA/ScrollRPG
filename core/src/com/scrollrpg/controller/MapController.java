package com.scrollrpg.controller;

import com.badlogic.gdx.physics.box2d.World;
import com.scrollrpg.builder.MapBuilder;
import com.scrollrpg.builder.item.Map;
import com.scrollrpg.game.state.PlayerState;
import com.scrollrpg.utils.AssetsUtils;

public class MapController {
	
	private MapBuilder map_builder;
	
	private AssetsUtils assets;
	
	public MapController(AssetsUtils assets, World world, PlayerState state){
		this.assets = assets;
		map_builder = new MapBuilder(this.assets, world, state);
	}
	
	public Map getFirstMap(){
		return map_builder.getMaps().first();
	}
	
	public Map getNextMap(Map current, String cardinal){
		return map_builder.getMaps().get(map_builder.getMaps().indexOf(current, true)).getLink(cardinal);
	}

}
