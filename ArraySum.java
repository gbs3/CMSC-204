public class ArraySum 
{
    public int sumOfArray(Integer[] a, int index) 
    {
        if (index < 0 || a[index] == null) {
            return 0;
        }
        else 
        {
            return a[index] + sumOfArray(a, index - 1);
        }
    }
}
