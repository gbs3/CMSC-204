import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TownGraphManager implements TownGraphManagerInterface
{
	private Graph graph = new Graph();
	
	public TownGraphManager()
	{
		
	}
	
	@Override
	public boolean addRoad(String source, String destination, int distance, String name)
	{
		if(graph.addEdge(new Town(source), new Town(destination), distance, name) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	@Override
	public String getRoad(String source, String destination)
	{
		return graph.getEdge(new Town(source), new Town(destination)).getName();
	}
	
	@Override
	public boolean addTown(String name)
	{
		return graph.addVertex(new Town(name));
	}
	
	@Override
	public Town getTown(String name)
	{
		for(Town town : graph.vertexSet())
		{
			if(town.getName().equals(name))
			{
				return town;
			}
		}
		return null;
	}
	
	@Override
	public boolean containsTown(String name)
	{
		return graph.containsVertex(new Town(name));
	}
	
	@Override
	public boolean containsRoadConnection(String source, String destination)
	{
		return graph.containsEdge(new Town(source), new Town(destination));
	}
	
	@Override
	public ArrayList<String> allRoads()
	{
		ArrayList<String> roads = new ArrayList<>();
		for(Road road : graph.edgeSet())
		{
			roads.add(road.getName());
		}
		return roads;
	}
	
	@Override
	public boolean deleteRoadConnection(String source, String destination, String road)
	{
		return graph.removeEdge(new Town(source), new Town(destination), 0, road) != null;
	}
	
	@Override
	public boolean deleteTown(String name)
	{
		return graph.removeVertex(new Town(name));
	}
	
	@Override
	public ArrayList<String> allTowns()
	{
		ArrayList<String> towns = new ArrayList<>();
		for(Town town : graph.vertexSet())
		{
			towns.add(town.getName());
		}
		return towns;
	}
	
	@Override
	public ArrayList<String> getPath(String source, String destination)
	{
		return graph.shortestPath(new Town(source), new Town(destination));
	}
	
	public void populateTownGraph(File file) throws FileNotFoundException, IOException
	{
		try (Scanner scanner = new Scanner(file)) 
		{
			Town town1 = null, town2 = null;
			String line = "";
			String[] splitLine;
			while(scanner.hasNext())
			{
				line = scanner.nextLine();
				splitLine = line.split(",");
				town2 = new Town(splitLine[3]);
				town1 = new Town(splitLine[2]);
				graph.addVertex(town1);
				graph.addVertex(town2);
				graph.addEdge(town1, town2, Integer.parseInt(splitLine[1]), splitLine[0]);
			}
		}
		catch (NullPointerException | IllegalArgumentException e) 
		{
			e.printStackTrace();
		}
	}

}
