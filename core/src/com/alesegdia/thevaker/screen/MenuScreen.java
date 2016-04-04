package com.alesegdia.thevaker.screen;

import com.alesegdia.thevaker.GdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MenuScreen implements Screen {

	private GdxGame g;

	public MenuScreen (GdxGame g)
	{
		this.g = g;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if( Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE) )
		{
			this.g.setScreen(g.gameplayScreen);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g.batch.begin();
		g.batch.draw(g.splash, 0, 0);
		g.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
