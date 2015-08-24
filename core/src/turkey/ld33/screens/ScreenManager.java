package turkey.ld33.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class ScreenManager
{
	private static List<Screen> screens = new ArrayList<Screen>();

	private static Screen currentScreen;

	public static void addScreen(Screen screen)
	{
		screens.add(screen);
	}

	public static void setCurrentScreen(Screen screen)
	{
		if(currentScreen != null)
			currentScreen.unloadScreen();
		currentScreen = screen;
		currentScreen.loadScreen();
		Gdx.input.setInputProcessor(currentScreen);
	}

	public static void setCurrentScreen(String screenName)
	{
		for(Screen screen: screens)
		{
			if(screen.getName().equalsIgnoreCase(screenName))
			{
				if(currentScreen != null)
					currentScreen.unloadScreen();
				currentScreen = screen;
				currentScreen.loadScreen();
				Gdx.input.setInputProcessor(currentScreen);
			}
		}
	}

	public static Screen getCurrentScreen()
	{
		return currentScreen;
	}

	public static Screen getScreenFromName(String screenName)
	{
		for(Screen screen: screens)
			if(screen.getName().equalsIgnoreCase(screenName))
				return screen;
		return null;
	}
}
