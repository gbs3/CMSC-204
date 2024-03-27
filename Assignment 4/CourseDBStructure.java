import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface 
{

    protected LinkedList<CourseDBElement>[] hashTable;

    // Constructor initializing hashTable array with specified size
    @SuppressWarnings("unchecked")
	public CourseDBStructure(int size) 
    {
        hashTable = new LinkedList[size];
    }

    // Constructor for testing purposes
    @SuppressWarnings("unchecked")
    public CourseDBStructure(String testing, int size) 
    {
        hashTable = new LinkedList[size];
    }

    // Add a CourseDBElement to the structure. If no linked list exists at the hash code, it creates one and adds the element.
    @Override
    public void add(CourseDBElement element) 
    {
    	int index = getHashIndex(element);

        if (hashTable[index] == null) 
        {
            hashTable[index] = new LinkedList<CourseDBElement>();
            hashTable[index].add(element);
        } 
        else 
        {
            boolean found = false;
            for (CourseDBElement existingElement : hashTable[index]) 
            {
                if (existingElement.getCRN() == element.getCRN()) 
                {
                    existingElement.setID(element.getID());
                    existingElement.setCredits(element.getCredits());
                    existingElement.setRoomNum(element.getRoomNum());
                    existingElement.setInstructor(element.getInstructor());
                    found = true;
                    break;
                }
            }
            if (!found) 
            {
                hashTable[index].add(element);
            }
        }
    }

    // Get a CourseDBElement with the given CRN and throws an exception if not found
    @Override
    public CourseDBElement get(int crn) throws IOException 
    {
        int index = getHashIndex(new CourseDBElement("", crn, 0, "", ""));

        if (hashTable[index] != null) 
        {
            for (CourseDBElement element : hashTable[index]) 
            {
                if (element.getCRN() == crn)
                {
                    return element;
                }
            }
        }
        throw new IOException("Course not found");
    }

    // Get the size of the hash table
    @Override
    public int getTableSize() 
    {
        return hashTable.length;
    }

    private int getHashIndex(CourseDBElement element) 
    {
        int hashIndex = element.hashCode() % hashTable.length;
        if (hashIndex < 0) 
        {
            hashIndex += hashTable.length;
        }
        return hashIndex;
    }

    // Return a list of all CourseDBElements in the structure
    @Override
    public ArrayList<String> showAll() 
    {
        ArrayList<String> allCourses = new ArrayList<>();
        for (LinkedList<CourseDBElement> list : hashTable) 
        {
            if (list != null) 
            {
                for (CourseDBElement element : list) 
                {
                    allCourses.add(element.toString());
                }
            }
        }
        return allCourses;
    }
}
