import java.util.ArrayList;
import java.util.Objects;

public class Town implements Comparable<Town> 
{
	private String name = "";
	private ArrayList<Town> adjacentTowns;
	
	public Town(String name)
	{
		this.name = name;
		adjacentTowns = new ArrayList<>();
	}
	
	public Town(Town town)
	{
		this.name = town.name;
		setAdjacentTowns(town.getAdjacentTowns());	
	}
	
	public int compareTo(Town town)
	{
		return this.name.compareTo(town.name);
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
		Town town = (Town) x;
		return Objects.equals(name, town.name);
	}
	
	public void setAdjacentTowns(ArrayList<Town> towns)
	{
		for(int i = 0; i < towns.size(); i++)
		{
			adjacentTowns.add(towns.get(i));
		}
	}
	
	public ArrayList<Town> getAdjacentTowns()
	{
		return adjacentTowns;
	}
	
	public void addAdjacentTowns(Town town)
	{
		adjacentTowns.add(town);
	}
	
	public String getName()
	{
		return name;
	}
	
	public int hashCode()
	{
		return name.hashCode();
	}
	
	//public String toString()
	//{
	//	String str = "";
	//	for(int i = 0; i < adjacentTowns.size(); i++)
	//	{
	//		str += adjacentTowns.get(i).getName() + " ";
	//	}
	//	return "Town name: " + name + "   Adjacent Towns: " + str.trim() + "\n";
	//}
}
