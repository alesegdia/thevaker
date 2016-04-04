package com.alesegdia.thevaker.screen;

import com.alesegdia.thevaker.GdxGame;
import com.alesegdia.thevaker.RNG;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameplayScreen implements Screen {

	private GdxGame g;
	
	// AI STATES **********************************
	public static final int STATUS_WAIT = 0;
	public static final int STATUS_DRAWING = 1;
	public static final int STATUS_SHOOTING = 2;
	public static final int STATUS_DEAD = 3;
	
	// GAME CONSTANTS *****************************
	public static final float AI_BASE_DRAWING_TIME = 0.3f;
	public static final float AI_EXTRA_DRAWING_TIME = 3f;
	public static final float AI_DRAWING_REDUCTION_FACTOR = 2f;
	public static final float AI_THINK_INTERVAL = 0.1f;
	public static final float AI_BASE_DUMMY_TIME = 0;
	public static final float AI_ACTION_PROBABILITY = 0.05f;
	
	float globalTimer = 0f;
	float nextAIThink = 0f;
	float aiDrawingTimer = 0f;
	int gameLevel = 0;
	int aiStatus = 0;

	boolean endGame;
	boolean playerWon;

	private int playerStatus;

	private boolean didSound;
	
	public GameplayScreen (GdxGame g)
	{
		this.g = g;
	}
	
	@Override
	public void show() {
		this.globalTimer = 0f;
		this.nextAIThink = AI_THINK_INTERVAL;
		this.aiDrawingTimer = 0f;
		this.aiStatus = 0;
		this.endGame = false;
		this.playerWon = false;
		this.aiStatus = STATUS_WAIT;
		this.playerStatus = STATUS_WAIT;
		this.didSound = false;
	}
	
	float getAIDrawingTime()
	{
		return AI_BASE_DRAWING_TIME + AI_EXTRA_DRAWING_TIME / (this.gameLevel + 1);
	}
	
	void doAI()
	{
		if( globalTimer > 1f )
		{
			switch( this.aiStatus )
			{
			case STATUS_WAIT:
				if( this.nextAIThink <= 0 )
				{
					if( RNG.rng.nextFloat() < AI_ACTION_PROBABILITY )
					{
						this.aiStatus = STATUS_DRAWING;
						this.aiDrawingTimer = getAIDrawingTime();
					}
					this.nextAIThink = AI_THINK_INTERVAL;
				}
				else
				{
					this.nextAIThink -= Gdx.graphics.getDeltaTime();
				}
				break;
			case STATUS_DRAWING:
				this.aiDrawingTimer -= Gdx.graphics.getDeltaTime();
				if( this.aiDrawingTimer <= 0 )
				{
					this.aiStatus = STATUS_SHOOTING;
					this.playerStatus = STATUS_DEAD;
				}
				break;
			case STATUS_SHOOTING:
				break;
			}
		}
	}

	@Override
	public void render(float delta) {
		this.globalTimer += Gdx.graphics.getDeltaTime();
		
		if( this.endGame && !this.didSound )
		{
			this.g.shoot.play();
			this.didSound = true;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		g.batch.begin();
		g.batch.draw(g.vaker2Sheet.get(this.playerStatus), 0, 0);
		g.batch.draw(g.vaker1Sheet.get(this.aiStatus), 32, 0);
		
		if( !this.endGame )
		{
			g.font.draw(g.batch, "lvl" + this.gameLevel, 0,64);
		}
		
		if( this.endGame )
		{
			if( this.playerWon )
			{
				g.batch.draw(g.win, 0, 48);
			}
			else
			{
				g.batch.draw(g.lose, 0, 48);
			}
		}
		g.batch.end();
		
		boolean playerShoot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched();

		if( this.endGame )
		{
			if( playerShoot && this.playerWon )
			{
				this.g.setScreen(this);
			}
			else if( playerShoot && !this.playerWon )
			{
				this.g.setScreen(g.menuScreen);
			}
		}
		else
		{
			this.globalTimer += Gdx.graphics.getDeltaTime();
			doAI();
			
			if( this.aiStatus == STATUS_SHOOTING )
			{
				endGame = true;
				playerWon = false;
				this.gameLevel = 0;
			}
			else
			{
				playerShoot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched();
				
				if( playerShoot )
				{

					this.playerStatus = STATUS_SHOOTING;
					playerWon = this.aiStatus == STATUS_DRAWING;
					this.aiStatus = STATUS_DEAD;
					if( this.playerWon )
					{
						if( !endGame )
						{
							this.gameLevel++;
						}
					}
					endGame = true;

				}
			}
		}
		
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
