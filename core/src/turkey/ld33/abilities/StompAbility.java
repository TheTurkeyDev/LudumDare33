package turkey.ld33.abilities;

import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Animation;
import turkey.ld33.screens.GameScreen;
import turkey.ld33.screens.GameScreen.DamageCause;

public class StompAbility extends Ability
{

	public StompAbility(int slot)
	{
		super("smash", 240, slot);
	}

	public void onUse(GameScreen screen)
	{
		screen.player.setYVel(-100);
		screen.addAnimation(new Animation(screen.player.x - 40, screen.player.y, 1, 30, false, new Texture("textures/other/dust_cloud.png")));
		screen.damageAt(screen.player.x + (screen.player.getWidth() / 2), 100, DamageCause.Smash);
		super.onUse(screen);
	}

}
