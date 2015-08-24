package turkey.ld33.entities;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;


public class EntityArmy extends Entity
{
	private Texture texture;
	
	protected GameScreen screen;
	
	public EntityArmy(float width, float height, String texutre, GameScreen screen)
	{
		super(width, height);
		this.x = 800;
		this.y = 90;
		texture = new Texture(texutre);
		this.screen = screen;
	}
	
	public void render()
	{
		Draw2D.drawTextured(x, y, texture);
	}
}
