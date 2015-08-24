package turkey.ld33.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import turkey.ld33.graphics.Draw2D;
import turkey.ld33.screens.GameScreen;

public class Ability
{
	private String name;

	private Texture texture;
	private Texture textureCD;

	private int slot;

	protected int cooldown;
	protected int cooldownTick = 0;

	public Ability(String name, int cooldown, int slot)
	{
		this.name = name;
		texture = new Texture("textures/icons/" + name + "_ability.jpg");
		textureCD = new Texture("textures/icons/" + name + "_ability_cool_down.jpg");
		this.cooldown = cooldown;
		this.slot = slot;
	}

	public void onUse(GameScreen screen)
	{
		this.cooldownTick = cooldown;
	}

	public void update()
	{
		if(cooldownTick > 0)
			cooldownTick--;
	}

	public void render()
	{
		Texture tex = this.isOnCoolDown() ? textureCD : texture;
		Draw2D.drawTextured(50 + (slot * 100), 20, tex);
		if(this.cooldownTick > 600)
			Draw2D.drawString(60 + (slot * 100), 65, "" + (this.cooldownTick / 60), 2, Color.WHITE);
		else if(this.cooldownTick > 0)
			Draw2D.drawString(70 + (slot * 100), 65, "" + (this.cooldownTick / 60), 2, Color.WHITE);
	}

	public String getName()
	{
		return this.name;
	}

	public int getCoolDown()
	{
		return this.cooldown;
	}

	public boolean isOnCoolDown()
	{
		return this.cooldownTick != 0;
	}
}
