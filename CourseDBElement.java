
public class CourseDBElement implements Comparable<CourseDBElement>
{
	protected String ID;
	protected int CRN;
	protected int credits;
	protected String roomNumber;
	protected String instructor;
	
	// Default constructor initializing fields
	public CourseDBElement()
	{
		ID = null;
		CRN = 0;
		credits = 0;
		roomNumber = null;
		instructor = null;
	}
	
	// Constructor initializing fields with provided values
	public CourseDBElement(String ID, int CRN, int credits, String roomNumber, String instructor)
	{
		this.ID = ID;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructor = instructor;
	}
	
	// Getter method for ID
	public String getID()
	{
	    return ID;
	}
	
	// Setter method for ID
	public void setID(String ID)
	{
	    this.ID = ID;
	}

	// Getter method for CRN
	public int getCRN() 
	{
	    return CRN;
	}

	// Setter method for CRN
	public void setCRN(int CRN) 
	{
	    this.CRN = CRN;
	}
	
	// Getter method for credits
	public int getCredits() 
	{
	    return credits;
	}

	// Setter method for credits
	public void setCredits(int credits) 
	{
	    this.credits = credits;
	}

	// Getter method for roomNumber
	public String getRoomNum() 
	{
	    return roomNumber;
	}

	// Setter method for roomNumber
	public void setRoomNum(String roomNumber) 
	{
		this.roomNumber = roomNumber;
	}
	
	// Getter method for instructor
	public String getInstructor() 
	{
	    return instructor;
	}

	// Setter method for instructor
	public void setInstructor(String instructor) 
	{
	    this.instructor = instructor;
	}
	
	// Generate hash code based on CRN
	@Override
	public int hashCode() 
	{
		String s = String.valueOf(getCRN());
	    long hash = 0;
	    int prime = 31;
	    for (int i = 0; i < s.length(); i++) 
	    {
	      hash = prime * hash + s.charAt(i);
	    }
	    return (int) hash;
	}

	// Convert object to string representation
	@Override
	public String toString() 
	{
		return "\nCourse:" + ID + " CRN:" + CRN + " Credits:" + credits + " Instructor:"
	        + instructor + " Room:" + roomNumber;
	}

	// Compare CourseDBElement objects based on CRN
	@Override
	public int compareTo(CourseDBElement element) 
	{
		return Integer.compare(this.getCRN(), element.getCRN());
	}
	  
	// Check if two objects are equal based on CRN
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == null)
	    {
	        return false;
	    }

	    if (obj == this) 
	    {
	        return true;
	    } 

	    if (!(obj instanceof CourseDBElement)) 
	    {
	        return false;
	    }
	    CourseDBElement cde = (CourseDBElement) obj;
	    return this.getCRN() == cde.getCRN();
	}

}
