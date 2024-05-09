import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> 
{
	private ArrayList<String> shortestPath = new ArrayList<String>();
	private Set<Town> towns = new HashSet<>();
	private Set<Road> roads = new HashSet<>();
	
	public Graph()
	{
		
	}
	
	@Override
	public Road getEdge(Town source, Town destination)
	{
		for(Road road : roads)
		{
			if(road.contains(source) && road.contains(destination))
			{
				return road;
			}
		}
		return null;
	}
	
	@Override
	public Road addEdge(Town source, Town destination, int distance, String name) throws NullPointerException, IllegalArgumentException
	{
		if(source == null || destination == null)
		{
			throw new NullPointerException();
		}
		if(!containsVertex(source) || !containsVertex(destination))
		{
			throw new IllegalArgumentException();
		}
		Road road = new Road(destination, source, distance, name);
		roads.add(road);
		return road;
	}
	
	@Override
	public boolean addVertex(Town town) throws NullPointerException
	{
		if(town == null)
		{
			throw new NullPointerException();
		}
		if(towns.contains(town))
		{
			return false;
		}
		towns.add(town);
		return true;
	}
	
	@Override
	public boolean containsEdge(Town source, Town destination)
	{
		for(Road r: roads)
		{
			if(r.contains(source) && r.contains(destination))
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean containsVertex(Town town)
	{
		return towns.contains(town);
	}
	
	@Override
	public Set<Road> edgeSet()
	{
		return roads;
	}
	
	@Override
	public Set<Road> edgesOf(Town town)
	{
		Set<Road> townRoads = new HashSet<>();
		for(Road road : roads)
		{
			if(road.contains(town))
			{
				townRoads.add(road);
			}
		}
		return townRoads;
	}
	
	@Override
	public Road removeEdge(Town source, Town destination, int distance, String name)
	{
		Road removedRoad = new Road(source, destination, distance, name);
		Iterator<Road> iterator = roads.iterator();
		while(iterator.hasNext())
		{
			Road road = iterator.next();
			if(road.getSource().equals(source) && road.getDestination().equals(destination) &&
					road.getDistance() == distance && road.getName().equals(name))
			{
				iterator.remove();
				removedRoad = road;
				break;
			}
		}
		return removedRoad;
	}
	
	@Override
	public boolean removeVertex(Town town)
	{
		if(town == null)
		{
			return false;
		}
		else
		{
			towns.remove(town);
			return true;
		}
	}
	
	@Override
	public Set<Town> vertexSet()
	{
		return towns;
	}
	
	@Override
	public ArrayList<String> shortestPath(Town source, Town destination)
	{
		ArrayList<String> str = new ArrayList<>();
		return str;
	}
	
	@Override
	public void dijkstraShortestPath(Town source) 
	{
	    
	}


}
