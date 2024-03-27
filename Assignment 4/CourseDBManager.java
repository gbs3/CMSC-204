import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface 
{

    private CourseDBStructure courseDBStructure;

    // Constructor initializes the CourseDBStructure with a default size of 10
    public CourseDBManager() 
    {
        courseDBStructure = new CourseDBStructure(10);
    }

    // Add a course to the database
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) 
    {
        CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
        courseDBStructure.add(element);
    }

    // Read course information from a file and add courses to the database
    @Override
    public void readFile(File input) throws FileNotFoundException 
    {
    	try (Scanner scanner = new Scanner(input)) 
    	{
            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) 
                {
                    String[] parts = line.split("\\s+", 5);
                    if (parts.length >= 5) 
                    {
                        add(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], parts[4]);
                    }
                }
            }
        } 
    	catch (FileNotFoundException e) 
    	{
            throw e;
        }
    }
    
    // Retrieve a list of all courses sorted by instructor's name
    @Override
    public ArrayList<String> showAll() 
    {
    	ArrayList<String> allCourses = new ArrayList<>();

        for (int i = 0; i < courseDBStructure.hashTable.length; i++) 
        {
            LinkedList<CourseDBElement> list = courseDBStructure.hashTable[i];
            if (list != null) 
            {
                for (CourseDBElement element : list) 
                {
                    allCourses.add(element.toString());
                }
            }
        }

        allCourses.sort(Comparator.comparing(course -> course.split("Instructor:")[1]));

        return allCourses;
    }

    // Get course information by CRN
    @Override
    public CourseDBElement get(int crn) 
    {
        try 
        {
            return courseDBStructure.get(crn);
        } 
        catch (IOException e) 
        {
            System.err.println("Exception occurred: " + e.getMessage());
        }
        return null;
    }
}
