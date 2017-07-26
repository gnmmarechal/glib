package glib.utils;
import java.util.Set;
import java.util.HashSet;

public class Lists
{
	public static boolean hasDuplicates(final String[] list)
	{
		Set<String> lump = new HashSet<String>();
		for (String i : list)
		{
			if (lump.contains(i)) return true;
			lump.add(i);
		}
		return false;
	}	
}
