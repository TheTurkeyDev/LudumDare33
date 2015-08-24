package turkey.ld33.entities;

public class Entity
{
	public float x = 0;
	public float y = 0;

	protected float width;
	protected float height;

	private boolean isAlive = true;

	public Entity(float width, float height)
	{
		this.width = width;
		this.height = height;
	}

	public void render()
	{

	}

	public void update()
	{

	}
	
	public boolean isAlive()
	{
		return this.isAlive;
	}
	
	public void kill()
	{
		this.isAlive = false;
	}
	
	public float getWidth()
	{
		return this.width;
	}
	
	public float getHeight()
	{
		return this.height;
	}
	
	public float getCenterX()
	{
		return this.x + (this.width / 2);
	}
	
	public float getCenterY()
	{
		return this.y - (this.height / 2);
	}
}
