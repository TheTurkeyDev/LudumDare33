package turkey.ld33.abilities;

import turkey.ld33.graphics.Animation;
import turkey.ld33.screens.GameScreen;
import turkey.ld33.screens.GameScreen.DamageCause;

import com.badlogic.gdx.graphics.Texture;

public class FlameBreathAbility extends Ability
{
	public FlameBreathAbility(int slot)
	{
		super("fire_breath", 420, slot);
	}

	public void onUse(GameScreen screen)
	{
		super.onUse(screen);
		screen.damageAt(screen.player.x + screen.player.getWidth() + 120, 140, DamageCause.Fire);
		screen.addAnimation(new Animation(screen.player.x + 150, screen.player.y, 1, 30, false, new Texture("textures/other/flame.png")));
	}
}
