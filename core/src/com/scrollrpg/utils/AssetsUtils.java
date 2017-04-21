package com.scrollrpg.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

public class AssetsUtils {
	
	private AssetManager assets = new AssetManager();
	
	public AssetsUtils(){
		FileHandleResolver resolver = new InternalFileHandleResolver();
		assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
	}
	
	public void load(){
		// Loaders
		// Textos i18n
		assets.load("i18n/messages", I18NBundle.class);
		// Fuente por defecto
		assets.load("arial.ttf", BitmapFont.class, CreateFontParameter(20,Color.WHITE));
		// Carga del resto de archivos
		// Texturas
		// Musica
	}
	
	public AssetManager getManager(){
		return assets;
	}
    
    private FreeTypeFontLoaderParameter CreateFontParameter(int size, Color color){
    	FreeTypeFontLoaderParameter parameter = new FreeTypeFontLoaderParameter();
        parameter.fontFileName = "fonts/arial.ttf";
        parameter.fontParameters.size = size;
        parameter.fontParameters.color = color;
        parameter.fontParameters.minFilter = TextureFilter.Nearest;
        parameter.fontParameters.magFilter = TextureFilter.Linear;
        return parameter;
    }
    
    public boolean update(){
    	return assets.update();
    }
    
    public boolean isLoaded(String resource){
    	return assets.isLoaded(resource);
    }
    
    public void dispose(){
    	assets.dispose();
    }

}