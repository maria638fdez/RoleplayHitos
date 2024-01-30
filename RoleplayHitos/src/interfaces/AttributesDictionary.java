package interfaces;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Set;

public class AttributesDictionary<T extends Enum<T>> extends EnumMap<T, Integer> {

	public AttributesDictionary(Class<T> keyType) {
		super(keyType);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	
	public int GetAttribute(T t) {
	   	return this.get(t);
	}
	
	public void SetAttribute(T attribute, int value) {
		this.put(attribute, value);
		
	}
	
	public ArrayList<String> getAllNames() {
		Set<T> set = this.keySet();
		ArrayList<String> list = new ArrayList<String>(set.size());
		for (T t : set) {
		    list.add(t.toString());
		}
		return list;
	}
	


}
