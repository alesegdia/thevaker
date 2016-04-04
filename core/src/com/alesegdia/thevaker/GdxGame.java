package com.alesegdia.thevaker;

import com.alesegdia.thevaker.screen.GameplayScreen;
import com.alesegdia.thevaker.screen.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GdxGame extends Game {
	public SpriteBatch batch;
	
	public GameplayScreen gameplayScreen;
	public MenuScreen menuScreen;
	
	public Spritesheet vaker1Sheet;
	public Spritesheet vaker2Sheet;
	public Texture splash;
	public Texture win;
	public Texture lose;

	public BitmapFont font;
	public Music maintheme;
	public Sound shoot;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameplayScreen = new GameplayScreen(this);
		menuScreen = new MenuScreen(this);

		vaker1Sheet = new Spritesheet("tile.png", 1, 4);
		vaker2Sheet = new Spritesheet("tile_2.png", 1, 4);
		splash = new Texture("SPLASH.png");
		win = new Texture("WIN.png");
		lose = new Texture("LOOSE.png");
		RNG.rng = new RNG();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("perfectdos.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 16;
		font = generator.generateFont(parameter);
		
		maintheme = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		maintheme.setLooping(true);
		maintheme.setVolume(0.5f);
		maintheme.play();

		shoot = Gdx.audio.newSound(Gdx.files.internal("disparo.ogg"));

		this.setScreen(menuScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
