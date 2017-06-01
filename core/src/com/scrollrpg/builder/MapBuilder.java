package com.scrollrpg.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.scrollrpg.builder.item.*;
import com.scrollrpg.constants.Constants;
import com.scrollrpg.game.state.PlayerState;
import com.scrollrpg.utils.AssetsUtils;

public class MapBuilder {
	
	private Array<Map> MapList = new Array<Map>();
	
	private AssetsUtils assets;
	
	public MapBuilder(AssetsUtils assets, World world, PlayerState state){
		this.assets = assets;
		createMaps(world, state);
	}
	
	private void createMaps(World world, PlayerState state){
		// Map 1
		Map tmp_map = new Map();
		//Platform tmp_platform = new Platform();
		tmp_map.addPlatform(new Platform(state, "PLT1", "STATIC", "SQUARE", world, new Vector2(0,-3f), new Vector2(5f, 2f), 0, 0, 0, null, assets.getManager().get("textures/bricks.png", Texture.class)));
		tmp_map.addPlatform(new Platform(state, "PLT2", "STATIC", "SQUARE", world, new Vector2(0, 0.5f), new Vector2(0.4f, 0.2f), 0, 0, 0, null, assets.getManager().get("textures/bricks.png", Texture.class)));
		tmp_map.addPlatform(new Platform(state, "PLT3", "STATIC", "SQUARE", world, new Vector2(0.8f, 1.5f), new Vector2(0.4f, 0.2f), 0, 0, 0, null, assets.getManager().get("textures/bricks.png", Texture.class)));
		MapList.add(tmp_map);
	}
	
	public Array<Map> getMaps(){
		return MapList;
	}

}
