package turkey.ld33.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.gui.GuiButton;

public class HelpScreen extends Screen
{
	private Texture background = new Texture("textures/mainmenu.png");

	public HelpScreen()
	{
		super("Help_Screen");
		super.addGuiComponent(new GuiButton(0, 300, 450, 200, 50, "Back", new Texture("textures/icons/button.png")));
	}

	public void render()
	{
		Draw2D.drawTextured(0, 0, background);
		Draw2D.drawString(50, 400, "Use A to move left and D to move right. Use W to jump", 2, Color.WHITE);
		Draw2D.drawString(50, 300, "Right click to use your selected ability", 2, Color.WHITE);
		Draw2D.drawString(10, 200, "Use your scroll wheel or numbers 1-3 to select diffent abilities", 2, Color.WHITE);
		Draw2D.drawString(50, 100, "This is and endless wave game!", 2, Color.WHITE);
		Draw2D.drawString(50, 30, "Survive as long as you can!", 2, Color.WHITE);
		super.render();

	}

	public void update()
	{

	}

	public void onComponentClicked(int id)
	{
		if(id == 0)
		{
			ScreenManager.setCurrentScreen("Main_Screen");
		}
	}
}