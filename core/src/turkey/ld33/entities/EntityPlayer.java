package turkey.ld33.entities;

import turkey.ld33.graphics.Draw2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class EntityPlayer extends Entity
{
	private Texture texture = new Texture("textures/entities/Monster_Idle.png");

	private float xVel = 0;
	private float yVel = 0;

	private boolean wDown = false;
	private boolean aDown = false;
	private boolean sDown = false;
	private boolean dDown = false;

	private boolean inAir = false;

	private float xSpeed = 210f;
	private float ySpeed = 1000f;

	public EntityPlayer()
	{
		super(150, 140);
		this.x = 100;
		this.y = 90;
	}

	public void render()
	{
		Draw2D.drawTextured(x, y, texture);
	}

	public void update()
	{
		this.x += this.xVel;
		if(x < 0)
			x = 0;
		else if(x > 650)
			x = 650;
		if((this.y += this.yVel) < 90)
			this.y = 90;

		xVel = 0;

		if(this.y == 90)
			this.inAir = false;

		if(this.wDown && !this.inAir)
		{
			yVel = ySpeed * Gdx.graphics.getDeltaTime();
			this.inAir = true;
			this.wDown = false;
		}
		if(this.sDown)
		{

		}
		if(this.aDown)
			xVel = -(xSpeed * Gdx.graphics.getDeltaTime());
		if(this.dDown)
			xVel = xSpeed * Gdx.graphics.getDeltaTime();

		this.yVel -= 1.75;
	}

	public boolean keyDown(int keycode)
	{
		if(keycode == Keys.W)
		{
			this.wDown = true;
			return true;
		}
		else if(keycode == Keys.A)
		{
			this.aDown = true;
			return true;
		}
		else if(keycode == Keys.S)
		{
			this.sDown = true;
			return true;
		}
		else if(keycode == Keys.D)
		{
			this.dDown = true;
			return true;
		}
		return false;

	}

	public boolean keyUp(int keycode)
	{
		if(keycode == Keys.W)
		{
			this.wDown = false;
			return true;
		}
		else if(keycode == Keys.A)
		{
			this.aDown = false;
			return true;
		}
		else if(keycode == Keys.S)
		{
			this.sDown = false;
			return true;
		}
		else if(keycode == Keys.D)
		{
			this.dDown = false;
			return true;
		}
		return false;
	}

	public void setYVel(float vel)
	{
		this.yVel = vel;
	}

	public void setXVel(float vel)
	{
		this.xVel = vel;
	}
}