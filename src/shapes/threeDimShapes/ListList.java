/**
 * Date: April 11, 2021
 * Description: A class allowing syntactical sugar to lists of lists
 * @author Matthew
 * 
 */

package shapes.threeDimShapes;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Function;

// TODO Copy class to PersonalProjects.src.calc package

// TODO Properly document methods
public class ListList<E> implements List<List<E>>, Cloneable
{
	// List of lists variable constructor
	List<List<E>> list;
	
	// Method constructor
	public ListList()
	{
		list = new ArrayList<List<E>>();
	}
	
	// Method constructor
	public ListList(int i)
	{
		list = new ArrayList<List<E>>(i);
	}
	
	// Method constructor
	public <T extends AbstractList<E>> ListList(T list)
	{
		this.list.add(list);
	}
	
	// Maximum sublist size
	public int maxSize()
	{
		if (list.isEmpty())
		{
			return -1; // TODO Document empty list return result
		}
		
		int max = 0;
		
		for (List<E> sublist : list)
		{
			max = Math.max(max, sublist.size());
		}
		
		return max;
	}
	
	// Minimum sublist size
	public int minSize()
	{
		if (list.isEmpty())
		{
			return -1; // TODO Document empty list return result
		}
		
		int min = Integer.MAX_VALUE; // Maximum value returned by size()
		
		for (List<E> sublist : list)
		{
			min = Math.min(min, sublist.size());
		}
		
		return min;
	}
	
	// List size
	@Override
	public int size()
	{
		return list.size();
	}
	
	// Is list empty
	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	// Does a list contain this element
	@Override
	public boolean contains(Object o)
	{
		for (List<E> sublist : list)
		{
			if (sublist.contains(o))
			{
				return true;
			}
		}
		
		return false;
	}
	
	// List iterator
	@Override
	public Iterator<List<E>> iterator()
	{
		return list.iterator();
	}
	
	// Convert list of lists to array of arrays
	@Override
	public Object[][] toArray()
	{
		Object[][] out = new Object[list.size()][maxSize()];
		
		for (int i = 0; i < list.size(); i++)
		{
			out[i] = list.get(i).toArray();
		}
		
		return out;
	}
	
	// Convert list of lists to typed array of arrays
	public <T extends Object> T[][] toArray(T[][] a)
	{
		// Initializing new typed array
		T[][] out = Arrays.asList(new Object[list.size()][maxSize()]).toArray(a);
		
		// Adding cast elements to array
		for (int i = 0; i < list.size(); i++)
		{
			out[i] = list.get(i).toArray(out[0]);
		}
		
		return out;
	}
	
	// Convert list of lists to typed array
	@Override
	public <T> T[] toArray(T[] a)
	{
		return list.toArray(a);
	}
	
	// Add sublist
	@Override
	public boolean add(List<E> e)
	{
		return list.add(e);
	}
	
	// Remove element
	@Override
	public boolean remove(Object o)
	{
		return list.remove(o);
	}
	
	// List contains all elements
	// TODO Recode containsAll method for sublist generalization
	@Override
	public boolean containsAll(Collection<?> c)
	{
		return list.containsAll(c);
	}
	
	@Override
	public boolean addAll(Collection<? extends List<E>> c)
	{
		return list.addAll(c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends List<E>> c)
	{
		return list.addAll(index, c);
	}
	
	@Override
	public boolean removeAll(Collection<?> c)
	{
		return list.removeAll(c);
	}
	
	@Override
	public boolean retainAll(Collection<?> c)
	{
		return list.retainAll(c);
	}
	
	// Clear list
	@Override
	public void clear()
	{
		list.clear();
	}
	
	// Get list
	@Override
	public List<E> get(int i)
	{
		return list.get(i);
	}
	
	// Get element
	public E get(int i, int j)
	{
		return list.get(i).get(j);
	}
	
	// Get element
	public E get(int[] indices)
	{
		return list.get(indices[0]).get(indices[1]);
	}
	
	// Set element in list
	public E set(int i, int j, E e)
	{
		return list.get(i).set(j, e);
	}
	
	// Set element in list
	public E set(int[] indices, E e)
	{
		return list.get(indices[0]).set(indices[1], e);
	}
	
	// Set element
	@Override
	public List<E> set(int index, List<E> element)
	{
		return list.set(index, element);
	}
	
	// Add element
	@Override
	public void add(int index, List<E> element)
	{
		list.add(index, element);
	}
	
	// Remove element
	@Override
	public List<E> remove(int index)
	{
		return list.remove(index);
	}
	
	// Get index of element
	@Override
	public int indexOf(Object o)
	{
		return list.indexOf(o);
	}
	
	@Override
	public int lastIndexOf(Object o)
	{
		return list.lastIndexOf(o);
	}
	
	@Override
	public ListIterator<List<E>> listIterator()
	{
		return list.listIterator();
	}
	
	@Override
	public ListIterator<List<E>> listIterator(int index)
	{
		return list.listIterator(index);
	}
	
	@Override
	public List<List<E>> subList(int fromIndex, int toIndex)
	{
		return list.subList(fromIndex, toIndex);
	}
	
	@Override
	public void forEach(Consumer<? super List<E>> action)
	{
		list.forEach(action);
	}
	
	// Deep clone
	public ListList<E> clone(Function<E, E> clone)
	{
		ListList<E> out = new ListList<E>(list.size());
		
		// Deep-cloning lists
		for (int i = 0; i < list.size(); i++)
		{
			// Adding sublist
			out.list.add(new ArrayList<E>());
			
			for (int j = 0; j < list.get(i).size(); j++)
			{
				if (get(i, j) instanceof Cloneable)
				{
					// Performing deep clone
					out.list.get(i).add(clone.apply(get(i, j)));
				}
				else
				{
					// Not cloneable: performing shallow clone
					out.list.get(i).add(get(i, j));
				}
			}
		}
		
		return out;
	}
	
	// Shallow clone
	@SuppressWarnings("unchecked")
	@Override
	public ListList<E> clone()
	{
		try
		{
			return (ListList<E>) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null; // Impossible
		}
	}
	
}
