package turkey.ld33.entities;

import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Animation;
import turkey.ld33.screens.GameScreen;

public class EntityPlane extends EntityArmy
{
	private boolean droppedBomb = false;
	public EntityPlane(GameScreen screen)
	{
		super(48, 14, "textures/entities/plane.png", screen);
		this.x = 800;
		this.y = 400;
	}

	public void update()
	{
		this.x -= 5f;

		if(x < -100)
			this.kill();
		
		if(Math.abs((this.getCenterX() - super.screen.player.getCenterX())) <  50)
		{
			if(!droppedBomb)
			{
				EntityBomb bomb = new EntityBomb(super.screen);
				bomb.x = this.x;
				bomb.y = this.y;
				screen.addEntity(bomb);
				this.droppedBomb = true;
			}
		}
	}
	
	public void kill()
	{
		screen.addAnimation(new Animation(this.getCenterX() - 32, this.getCenterY() - 32, 1, 30, false, new Texture("textures/other/explosion.png")));
		super.kill();
	}
}
