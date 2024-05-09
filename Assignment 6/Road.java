import java.util.Objects;

public class Road
{
	private Town source;
	private Town destination;
	private int distance;
	private String name;
	
	Road(Town source, Town destination, int distance, String name)
	{
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.name = name;
	}
	
	Road(Town source, Town destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	public int compareTo(Road road)
	{
		int comparison = this.name.compareTo(road.name);
		if(comparison == 0)
		{
			return Integer.compare(this.distance, road.distance);
		}
		return this.name.compareTo(road.name);
	}
	
	public boolean contains(Town town)
	{
		if(source.getName().equals(town.getName()) || destination.getName().equals(town.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean equals(Object x)
	{
		if(this == x)
		{
			return true;
		}
		if(x == null || getClass() != x.getClass())
		{
			return false;
		}
		Road road =(Road) x;
		return distance == road.distance && 
				Objects.equals(source, road.source) &&
				Objects.equals(destination, road.destination) &&
				Objects.equals(name, road.name);
	}
	
	@Override
	public String toString()
	{
		String str = "";
		str = source.getName() + " via " + name + " to " + destination.getName() + " " + distance + " mi";
		return str;
	}
	
	public Town getSource()
	{
		return source;
	}
	
	public Town getDestination()
	{
		return destination;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public String getName()
	{
		return name;
	}

}
