package turkey.ld33.entities;

import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Animation;
import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;

public class EntityBomb extends Entity
{
	private GameScreen screen;

	private Texture texture = new Texture("textures/entities/bomb.png");

	public EntityBomb(GameScreen screen)
	{
		super(15, 30);
		this.screen = screen;
	}

	public void update()
	{
		this.y -= 3f;

		if((this.y <= screen.player.y + screen.player.getHeight() && this.y >= screen.player.y) && (Math.abs((this.getCenterX() - screen.player.getCenterX())) < 75))
		{
			screen.damagePlayer(15);
			this.kill();
			screen.addAnimation(new Animation(this.getCenterX() - 32, this.getCenterY(), 1, 30, false, new Texture("textures/other/explosion.png")));
		}
		else if(this.y < 90)
		{
			this.kill();
			screen.addAnimation(new Animation(this.getCenterX() - 32, this.getCenterY(), 1, 30, false, new Texture("textures/other/explosion.png")));
		}
	}

	public void render()
	{
		Draw2D.drawTextured(x, y, texture);
	}
}
