package turkey.ld33;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;
import turkey.ld33.screens.HelpScreen;
import turkey.ld33.screens.MainScreen;
import turkey.ld33.screens.Screen;
import turkey.ld33.screens.ScreenManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LDMain extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture img;

	@Override
	public void create()
	{
		new Draw2D();

		Screen mainScreen = new MainScreen();
		Screen gameScreen = new GameScreen();
		Screen helpScreen = new HelpScreen();

		ScreenManager.addScreen(mainScreen);
		ScreenManager.addScreen(gameScreen);
		ScreenManager.addScreen(helpScreen);
		ScreenManager.setCurrentScreen(mainScreen);
	}

	@Override
	public void render()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Draw2D.updateCamera();
		ScreenManager.getCurrentScreen().update();
		ScreenManager.getCurrentScreen().render();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}
