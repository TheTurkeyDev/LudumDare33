package turkey.ld33.entities;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;
import turkey.ld33.screens.GameScreen.DamageCause;

import com.badlogic.gdx.graphics.Texture;

public class EntityBoulder extends Entity
{
	private Texture texture = new Texture("textures/entities/boulder.png");
	
	private float xVel = 0;
	private float yVel = 0;

	private GameScreen screen;

	public EntityBoulder(GameScreen screen)
	{
		super(40, 40);
		this.screen = screen;
	}

	public void throwBoulder(float x, float y)
	{
		this.x = x;
		this.y = y;
		this.yVel = 7f;
		this.xVel = 7f;
	}

	public void update()
	{
		this.x += this.xVel;
		if((this.y += this.yVel) < 100)
			this.kill();

		this.yVel -= .1f;
		
		for(Entity ent: screen.getEntities())
		{
			if(ent instanceof EntityPlane)
			{
				if(this.y > 350 && this.y < 450)
				{
					if(Math.abs(this.getCenterX() - ent.getCenterX()) < 60)
					{
						screen.givePlayerPoints(100);
						ent.kill();
					}
				}
			}
		}
	}

	public void render()
	{
		Draw2D.drawTextured(x, y, texture, 0);
	}
	
	public void kill()
	{
		screen.damageAt(this.getCenterX(), 30, DamageCause.Explosion);
		super.kill();
	}
}