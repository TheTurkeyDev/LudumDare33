package turkey.ld33.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import turkey.ld33.abilities.Ability;
import turkey.ld33.abilities.BoulderAbility;
import turkey.ld33.abilities.FlameBreathAbility;
import turkey.ld33.abilities.StompAbility;
import turkey.ld33.entities.Entity;
import turkey.ld33.entities.EntityArmy;
import turkey.ld33.entities.EntityPlane;
import turkey.ld33.entities.EntityPlayer;
import turkey.ld33.entities.EntitySoldierTier1;
import turkey.ld33.entities.EntitySoldierTier2;
import turkey.ld33.entities.EntityTank;
import turkey.ld33.graphics.Animation;
import turkey.ld33.graphics.Draw2D;
import turkey.ld33.gui.GuiButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends Screen
{
	private Texture background = new Texture("textures/background.png");
	private Texture road = new Texture("textures/road.png");

	private GuiButton restart;
	private GuiButton mainMenu;

	public EntityPlayer player;

	private float playerHealth = 100;
	private int score = 0;

	private int[] playerKeys = new int[] { Keys.W, Keys.A, Keys.S, Keys.D };

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Animation> animations = new ArrayList<Animation>();

	private int selectedSlot = 0;
	private Ability[] playerAbilities = new Ability[3];

	public float lastClickX;

	private int spawnDelay = 420;
	private int spawns = 0;
	private int spawnDelayTick = 0;

	private Random rand = new Random();

	private boolean paused = false;
	private boolean gameOver = false;

	public GameScreen()
	{
		super("Game_Screen");
	}

	public void loadScreen()
	{
		super.clearCompnents();
		player = new EntityPlayer();
		Gdx.input.setCursorCatched(true);

		playerHealth = 100;
		score = 0;
		selectedSlot = 0;
		spawnDelay = 420;
		spawns = 0;
		spawnDelayTick = 0;
		paused = false;
		gameOver = false;
		entities.clear();
		animations.clear();
		playerAbilities[0] = new BoulderAbility(0);
		playerAbilities[1] = new StompAbility(1);
		playerAbilities[2] = new FlameBreathAbility(2);

		super.addGuiComponent(restart = new GuiButton(0, 300, 200, 200, 50, "Restart", new Texture("textures/icons/button.png")));
		super.addGuiComponent(mainMenu = new GuiButton(1, 300, 100, 200, 50, "Main Menu", new Texture("textures/icons/button.png")));

		restart.setVisible(false);
		mainMenu.setVisible(false);
	}

	public void update()
	{
		if(paused)
			return;

		player.update();

		for(int i = entities.size() - 1; i >= 0; i--)
		{
			Entity ent = entities.get(i);
			if(!ent.isAlive())
			{
				entities.remove(i);
				continue;
			}
			ent.update();
		}

		for(int i = this.animations.size() - 1; i >= 0; i--)
		{
			Animation a = animations.get(i);
			if(!a.isRunning())
			{
				animations.remove(i);
				continue;
			}
			a.update();
		}

		for(Ability a : this.playerAbilities)
			if(a != null)
				a.update();

		this.spawnDelayTick++;
		if(this.spawnDelayTick >= this.spawnDelay)
		{

			if(spawns < 5)
			{
				this.addEntity(new EntitySoldierTier1(this));
			}
			else if(spawns < 10)
			{
				if(rand.nextInt(10) == 1)
					this.addEntity(new EntitySoldierTier1(this));
				this.addEntity(new EntitySoldierTier2(this));
				this.spawnDelay -= 5;
			}
			else if(spawns < 20)
			{
				if(rand.nextInt(5) == 1)
					this.addEntity(new EntitySoldierTier1(this));
				if(rand.nextInt(10) == 1)
					this.addEntity(new EntitySoldierTier2(this));
				this.addEntity(new EntityTank(this));
				this.spawnDelay -= 5;
			}
			else if(spawns < 40)
			{
				if(rand.nextInt(5) == 1)
					this.addEntity(new EntitySoldierTier1(this));
				if(rand.nextInt(10) == 1)
					this.addEntity(new EntitySoldierTier2(this));
				if(rand.nextInt(10) == 1)
					this.addEntity(new EntityTank(this));
				this.addEntity(new EntityPlane(this));
				this.spawnDelay -= 10;
			}
			else
			{
				if(rand.nextBoolean())
					this.addEntity(new EntitySoldierTier1(this));
				if(rand.nextBoolean())
					this.addEntity(new EntitySoldierTier2(this));
				if(rand.nextBoolean())
					this.addEntity(new EntityTank(this));
				if(rand.nextBoolean())
					this.addEntity(new EntityPlane(this));
				if(this.spawnDelay >= 180)
					this.spawnDelay -= 10;
			}

			spawns++;
			this.spawnDelayTick = 0;
		}

		if(this.playerHealth <= 0)
		{
			this.paused = true;
			this.gameOver = true;
			Gdx.input.setCursorCatched(false);
			this.restart.setVisible(true);
			this.mainMenu.setVisible(true);
		}
	}

	public void render()
	{
		Draw2D.drawTextured(0, 100, background);
		Draw2D.drawTextured(0, 0, road);

		Draw2D.drawString(50, Gdx.graphics.getHeight() - 10, "Score:", 2, Color.WHITE);
		Draw2D.drawString(100 - (("" + this.score).length() * 10), Gdx.graphics.getHeight() - 40, "" + this.score, 2, Color.WHITE);

		player.render();
		for(Entity ent : entities)
			ent.render();
		for(Animation a : this.animations)
			a.render();

		Draw2D.drawRect(200, 550, 400, 50, Color.RED, true);
		Draw2D.drawRect(200, 550, this.playerHealth * 4, 50, Color.GREEN, true);
		Draw2D.drawString(300, 590, "Health " + (int) this.playerHealth + "/100", 2, Color.WHITE);

		Draw2D.drawRect(45 + (this.selectedSlot * 100), 15, 70, 70, Color.WHITE, true);

		for(Ability a : this.playerAbilities)
			if(a != null)
				a.render();

		if(paused)
		{
			if(this.gameOver)
			{
				Draw2D.drawString((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + 200, "Game Over", 6, Color.WHITE);
				Draw2D.drawString((Gdx.graphics.getWidth() / 2) - 200, (Gdx.graphics.getHeight() / 2) + 75, "Score: " + this.score, 6, Color.WHITE);
			}
			else
			{
				Draw2D.drawString((Gdx.graphics.getWidth() / 2) - 125, (Gdx.graphics.getHeight() / 2) + 75, "Paused", 6, Color.WHITE);
			}
		}

		super.render();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if(keycode >= Keys.ESCAPE)
		{
			if(!this.gameOver)
			{
				this.paused = !this.paused;
				Gdx.input.setCursorCatched(!this.paused);
				this.restart.setVisible(this.paused);
				this.mainMenu.setVisible(this.paused);
				return true;
			}

		}

		if(this.paused)
			return false;

		for(int i : this.playerKeys)
			if(keycode == i)
				return this.player.keyDown(keycode);

		if(keycode >= 8 && keycode <= 10)
		{
			this.selectedSlot = keycode - 8;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if(this.paused)
			return false;
		for(int i : this.playerKeys)
		{
			if(keycode == i)
			{
				this.player.keyUp(keycode);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(super.touchDown(screenX, screenY, pointer, button))
			return true;

		if(this.paused)
			return false;
		super.touchDown(screenX, screenY, pointer, button);
		lastClickX = screenX;

		if(this.playerAbilities[this.selectedSlot] != null && !this.playerAbilities[this.selectedSlot].isOnCoolDown())
		{
			this.playerAbilities[this.selectedSlot].onUse(this);
			return true;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		if(this.paused)
			return false;
		this.selectedSlot += amount;
		if(selectedSlot > 2)
			selectedSlot -= 3;
		else if(selectedSlot < 0)
			selectedSlot += 3;
		return false;
	}

	public void onComponentClicked(int id)
	{
		if(id == 0)
		{
			ScreenManager.setCurrentScreen("Game_Screen");
		}
		if(id == 1)
		{
			ScreenManager.setCurrentScreen("Main_Screen");
		}
	}

	public void addEntity(Entity ent)
	{
		this.entities.add(ent);
	}

	public void addAnimation(Animation a)
	{
		this.animations.add(a);
	}

	public List<Entity> getEntities()
	{
		return this.entities;
	}

	public void damageAt(float pos, int range, DamageCause cause)
	{
		for(Entity ent : entities)
		{
			if(ent instanceof EntityArmy)
			{
				if(Math.abs(ent.getCenterX() - pos) < range && ent.y < 150)
				{
					ent.kill();

					if(ent instanceof EntitySoldierTier1)
					{
						this.score += 10;
						if(cause.equals(DamageCause.Fire))
							this.addAnimation(new Animation(ent.x, ent.y, 1, 120, false, new Texture("textures/other/pile_of_ash.png")));
						else if(cause.equals(DamageCause.Smash))
							this.addAnimation(new Animation(ent.x, ent.y, 1, 120, false, new Texture("textures/other/squished_player.png")));
					}
					else if(ent instanceof EntitySoldierTier2)
					{
						this.score += 20;
						if(cause.equals(DamageCause.Fire))
							this.addAnimation(new Animation(ent.x, ent.y, 1, 120, false, new Texture("textures/other/pile_of_ash.png")));
						else if(cause.equals(DamageCause.Smash))
							this.addAnimation(new Animation(ent.x, ent.y, 1, 120, false, new Texture("textures/other/squished_player.png")));
					}
					else if(ent instanceof EntityTank)
					{
						this.score += 50;
						this.addAnimation(new Animation(ent.x, ent.y, 1, 120, false, new Texture("textures/other/tank_fire.png")));
					}
				}
			}
		}
	}

	public void damagePlayer(int amount)
	{
		this.playerHealth -= amount;
	}

	public void givePlayerPoints(int amount)
	{
		this.score += amount;
	}

	public enum DamageCause
	{
		Fire, Smash, Explosion;
	}
}