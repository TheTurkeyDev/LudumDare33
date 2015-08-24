package turkey.ld33.screens;

import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.gui.GuiButton;

public class MainScreen extends Screen
{
	private Texture background = new Texture("textures/mainmenu.png");

	public MainScreen()
	{
		super("Main_Screen");
		super.addGuiComponent(new GuiButton(0, 300, 400, 200, 50, "START", new Texture("textures/icons/button.png")));
		super.addGuiComponent(new GuiButton(1, 300, 300, 200, 50, "HELP", new Texture("textures/icons/button.png")));
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, background);
		super.render();

	}

	public void update()
	{

	}

	public void onComponentClicked(int id)
	{
		if(id == 0)
		{
			ScreenManager.setCurrentScreen("Game_Screen");
		}
		else if(id == 1)
		{
			ScreenManager.setCurrentScreen("Help_Screen");
		}
	}
}