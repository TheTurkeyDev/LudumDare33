package turkey.ld33.entities;

import turkey.ld33.screens.GameScreen;

public class EntityTank extends EntityArmy
{
	private boolean moving = true;

	private int fireDelay = 120;
	private int fireDelayTick = 0;
	
	public EntityTank(GameScreen screen)
	{
		super(100, 19, "textures/entities/tank.png", screen);
	}
	
	public void update()
	{
		moving = false;
		double distTo = Math.abs(this.x - super.screen.player.getCenterX());

		if(distTo > 300)
		{
			moving = true;
			if(this.x < super.screen.player.getCenterX())
			{
				this.x += 1f;
			}
			else
				this.x -= 1f;
		}
		
		if(!moving)
		{
			this.fireDelayTick++;
			
			if(this.fireDelayTick >= this.fireDelay)
			{
				if(super.screen.player.y < 132)
				{
					super.screen.damagePlayer(10);
				}
				this.fireDelayTick = 0;
			}
		}


	}
}