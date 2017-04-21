package com.scrollrpg.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinUtils {
	public SkinUtils(){
		
	}
	
    public Skin CreateSkin(){
    	Skin tmp_skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
		return tmp_skin;
    	
    }
    
    private BitmapFont CreateFont(int size, Color color){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.minFilter = TextureFilter.Nearest;
        parameter.magFilter = TextureFilter.Linear;
        BitmapFont tmpFont = generator.generateFont(parameter);
        tmpFont.getData().markupEnabled = true;
        generator.dispose();
        return tmpFont;
    }
}
