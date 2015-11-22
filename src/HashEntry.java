
public class HashEntry {
	
	private String value;
	private String key;
	private int hash;
	
	
public HashEntry(String v, String k){
	
	value = v;
	key = k;
}
	
	
	
	
public String getKey(){
	
	return key;
}

public String getValue(){
	
	return value;
}

public int getHash(){
	
	return hash;
	
}

public void setHash(int i){
	
	hash = i;
}

public void setValue(String s){
	
	value = s;
	
}


}
