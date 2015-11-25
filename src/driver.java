
public class driver {

	public static void main(String[] args) {
		
		HashTable ht = new HashTable();
		ht.addEntry("hello", "hello");
		ht.addEntry("hi", "hi");
		System.out.println(ht.getIndex("hello"));
		System.out.println(ht.getIndex("ho"));
		ht.entrySet();
		ht.keySet();
		ht.valueSet();
	}

}
