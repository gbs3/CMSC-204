import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookTester {
	
	private GradeBook g1;
	private GradeBook g2;

	@BeforeEach
	void setUp() throws Exception
	{
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		
		g1.addScore(50);
		g1.addScore(75);
		
		g2.addScore(25);
		g2.addScore(43);
		g2.addScore(7);
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore() 
	{
		g1.addScore(5);
		g1.addScore(10);
		
		g2.addScore(15);
		g2.addScore(20);
		
		assertEquals(4, g1.getScoreSize());
        assertEquals(5, g2.getScoreSize());
        
        assertEquals("50.0 75.0 5.0 10.0 0.0 ", g1.toString());
        assertEquals("25.0 43.0 7.0 15.0 20.0 ", g2.toString());
	}

	@Test
	void testSum() 
	{
		assertEquals(125.0, g1.sum(), 0.001);
        assertEquals(75.0, g2.sum(), 0.001);
	}

	@Test
	void testMinimum() 
	{
		assertEquals(50, g1.minimum(), 0.001);
        assertEquals(7, g2.minimum(), 0.001);
	}

	@Test
	void testFinalScore() 
	{
		assertEquals(75.0, g1.finalScore(), 0.001);
        assertEquals(68.0, g2.finalScore(), 0.001);
	}

	@Test
	void testGetScoreSize() 
	{
		assertEquals(2, g1.getScoreSize());
        assertEquals(3, g2.getScoreSize());
	}

	@Test
	void testToString()
	{
		assertEquals("50.0 75.0 0.0 0.0 0.0 ", g1.toString());
        assertEquals("25.0 43.0 7.0 0.0 0.0 ", g2.toString());
	}

}
