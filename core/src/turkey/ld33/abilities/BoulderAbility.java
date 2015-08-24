package turkey.ld33.abilities;

import turkey.ld33.entities.EntityBoulder;
import turkey.ld33.screens.GameScreen;

public class BoulderAbility extends Ability
{
	public BoulderAbility(int slot)
	{
		super("boulder", 120, slot);
	}

	public void onUse(GameScreen screen)
	{
		EntityBoulder boulder = new EntityBoulder(screen);
		boulder.throwBoulder(screen.player.x, screen.player.y + 50);
		screen.addEntity(boulder);
		super.onUse(screen);
	}
}
