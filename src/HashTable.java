
public class HashTable {
	
	
	//prime numbers work best to avoid collision
	private int size = 240007;
	private static int entries=0;
	HashEntry[] table;
	HashEntry[] table2;
	private double thresholdfactor;
	private double rehashmultiplyer=2;
	private static char collision='Q';
	private static char markertype='A';
	
public HashTable(){
	
	table = new HashEntry[size];
	for(int i =0; i<=(size-1);i++)
	{
		table[i]=null;
	}
}

public void addEntry(String v, String k){
	//if the amount of entries are larger than thh threshold muliplied by size, reorder table
	if(entries > thresholdfactor*size)
		tablereorder();
	
	HashEntry temp = new HashEntry(v, k);
	temp.setHash(this.hashFunction(k));
	if(table[temp.getHash()]==null)
	table[temp.getHash()]=temp;
	//checks to see if next space is available
	if(table[temp.getHash()+1]==null)
	{
		
		table[temp.getHash()]=temp;
		
	}
	//if not deal using alterante collision handling
	else
	{
		//if collision is set to Q, do quadratic handling
		if(getCollision()=='Q')
			Quad(k,v,temp);
			
		//if collision is set to D, do double hashing	
		if(getCollision()=='D')
		{
			
			doublehash(k,v,temp);
			
		}
		
		
	}
	}

//quadratic handling of the function here. Adds based on quadratic hashing method.
private void Quad(String k, String v, HashEntry temp) {
	
	int value = 0;
	int i= 1;
	while(table[value]!=null)
		
	{
		value = (table[value].getHash() + i*i)%240011 %240007;
		i++;
	}
	
	temp.setHash(value);
	table[value]=temp;
	
}

//new doublehash function defined here. Uses alternate hashing to store value
private void doublehash(String k, String v, HashEntry temp) {
	
	int value = 0;
	int i= 1;
	while(table[value]!=null)
		
	{
		value = 7853 + hashFunction(v)+i %240011 %240007 ;
		i++;
	}
	
	temp.setHash(value);
	table[value]=temp;
	
}

public Integer getIndex(String k){
	
	int h = this.hashFunction(k);
	
	if(table[h]!=null && table[h].getKey()==k)
		return hashFunction(k);
	else return null;
}

public String get(String k){
	
	int h = this.hashFunction(k);
	
	if(table[h]!=null)
		return table[h].getValue();
	
	else return null;
	
	
}

public void setEmptyMarkerScheme(char type)
{
	
	
	
	if(type == 'A')
	{
		
		for(int i = 0;i<size-1;i++)
		{
			
			if(table[i]==null)
			{
				
				table[i].setValue("A");
				
			}
			
			
		}
		
		
	}	
	if(type == 'R')
	{
		
		for(int i = 0;i<size-1;i++)
		{
			
			if(table[i]==null)
			{
				
				table[i].setValue("A");
				
			}
			
			
		}
		
		
	}	
	if(type == 'N')
	{
		
		for(int i = 0;i<size-1;i++)
		{
			
			if(table[i]==null)
			{
				
				table[i].setValue("A");
				
			}
			
			
		}
		
		
	}	






}

public String put(String k, String v){
	
	int h = this.hashFunction(k);
	
	if(table[h]!=null){
		
		String old = table[h].getValue();
		table[h].setValue(v);
		return old;
	}
	else return null;
	
}

public String remove(String k){
	
	int h = this.hashFunction(k);
	
	if(table[h]!= null){
		String old = table[h].getValue();
		if(markertype=='A')
		table[h].setValue("A");
		if(markertype=='N')
		table[h].setValue("-"+table[h].getValue());
		if(markertype=='R')
		{
		//String v = table[h].getValue();;
		int key=table[h].getHash();
		for(int i =0;i<size-1;i++)
		{
			
			if (table[i].getHash() == key);
			{
				int index=table[i].getHash();
				table[i].setValue(table[key].getValue());
				table[i].setKey(table[key].getKey());
				table[i].setHash(table[key].getHash());
				table[key].setHash(0);
				table[key].setKey(null);
				table[key].setHash(0);
			}
			
		}
		
		
		}
		return old;
	}
	else return null;
	
}


public void entrySet(){
	
	for(HashEntry iterator:table)
	{
		if(iterator==null)
			continue;
		else
		System.out.println(iterator.getKey() + " " + iterator.getValue());
		
	}
	
}

public void keySet(){
	for(HashEntry iterator:table)
	{
		if(iterator==null)
			continue;
		else
		System.out.println(iterator.getKey());
		
	}
	
	
}

public void valueSet(){
	
	for(HashEntry iterator:table)
	{
		if(iterator==null)
			continue;
		else
		System.out.println(iterator.getValue());
	}
	
	
}



public int size(){
	
		
	for(int i =0; i<=(size-1); i++){
		
		if(table[i]!=null)
			entries++;
	}
	return entries;
}

public boolean isEmpty(){
	
	boolean empty=true;
	
	for(int i=0; i<=(size-1);i++){
		
		if(table[i]!=null)
			empty=false;
		
	}
	
	return empty;
	
}

private int hashFunction(String s){
	
	int h = 0;
	int length = s.length();
	
	for(int i = 0; i <= (length-1); i++){
		
		 h = h+((length-i)*s.charAt(i));
		 
		}
	
	h = ((((3000*h)+12678)%240011)%240007);
	
	return h;
	}

//Reorders table if a certain amount of entries get filled
/*eg if threshold is set to 80%, and 80% of the table is filled, 
*a new table is made that is the size multiplied by the rehash multiplier(dafualt 2)
*copy the values of table 1 inside table 2 and then set table 1 as table 2
*/
public void tablereorder()
{
	System.out.println("CAPACITY REACHED. REORDERING TABLE....");
	table2 = new HashEntry[(int) (size* rehashmultiplyer)];
	size=(int) (size*rehashmultiplyer);

	for(int i =0; i<=((size*rehashmultiplyer)-1);i++)
	{
		table2[i]=null;
	}
	
	for(int i =0; i<=(table.length-1);i++)
	{
		table2[i].setHash(this.hashFunction(table[i].getKey()));
		table2[i].setValue(table[i].getValue());
	
	}
	
	table=table2;

		
		
}
	
public void setCollisionHandling(char type)
{
	
	
	collision=type;
		
}

public void setRehashThreshold(double factorOrNumber)
{

	
	rehashmultiplyer=factorOrNumber;
	
	
	

}

public static char getCollision() {
	return collision;
}

public static char getMarkertype() {
	return markertype;
}

public static void setMarkertype(char markertype) {
	HashTable.markertype = markertype;
}






}
