package turkey.ld33.entities;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;

public class EntitySoldierTier2 extends EntityArmy
{
	private boolean facingLeft = true;
	private boolean moving = true;

	private int fireDelay = 30;
	private int fireDelayTick = 0;

	private Texture textureLeft;
	private Texture textureRight;

	public EntitySoldierTier2(GameScreen screen)
	{
		super(32f, 32f, "textures/entities/soldier_T2_left.png", screen);
		textureLeft = new Texture("textures/entities/soldier_T2_left.png");
		textureRight = new Texture("textures/entities/soldier_T2_right.png");
	}

	public void update()
	{
		moving = false;
		double distTo = Math.abs(this.x - super.screen.player.getCenterX());

		if(distTo > 175)
		{
			moving = true;
			if(this.x < super.screen.player.getCenterX())
			{
				facingLeft = false;
				this.x += 3f;
			}
			else
			{
				facingLeft = true;
				this.x -= 3f;
			}
		}
		
		if(!moving)
		{
			this.fireDelayTick++;
			
			if(this.fireDelayTick >= this.fireDelay)
			{
				if(super.screen.player.y < 132)
				{
					super.screen.damagePlayer(1);
				}
				this.fireDelayTick = 0;
			}
		}


	}

	@Override
	public void render()
	{
		if(this.facingLeft)
			Draw2D.drawTextured(x, y, textureLeft);
		else
			Draw2D.drawTextured(x, y, textureRight);
	}

}
