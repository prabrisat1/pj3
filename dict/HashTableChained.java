/* HashTableChained.java */
package dict;
import hw5.list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/

    private int a, b, p, N; // variables for compression and hash table size
    private int size;

    private List[] hTable;

    /** 
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
	N = primeGreaterThan((sizeEstimate));
 	p = primeGreaterThan(2*N);
	a = (int) (Math.random() * p);
	b = (int) (Math.random() * p);
	hTable = new List[N];
    }

    /**
     *  Returns the first prime greater than an integer n.
     *
     **/

    private int primeGreaterThan(int n){ 
	int max = n*2;
	boolean[] prime = new boolean[max];
	int i;
	for (i = 2; i < max; i++) {
	    prime[i] = true;
	}

	for (int divisor = 2; divisor * divisor < n; divisor++) {
	    if (prime[divisor]) {
		for(i = 2 * divisor; i < max; i += divisor) {
		    prime[i] = false;
		}
	    }
	}

	for (int k = n+1; k < max; k++){
	    if(prime[k])
		return k;
	}

	return -1;
    }

    /** 
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
	N = 101;
 	p = primeGreaterThan(5*N);
	a = (int) (Math.random() * p);
	b = (int) (Math.random() * p);

	hTable = new List[N];    
    }

    // Returns x mod y
    private int mod(int x, int y){
	int result = (x % y);
	if (result < 0){
	    result += y;
	}

	return result;
    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
	return mod(mod(a*code + b, p), N);
    }

    /** 
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
	// Replace the following line with your solution.
	return size;
    }

    /** 
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
	// Replace the following line with your solution.
	return size == 0;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Resize the hash table if necessary
        resize();

	int i = compFunction(key.hashCode());

	if (hTable[i] == null){
	    hTable[i] = new SList();
	}
	Entry e = new Entry();
	e.key = key;
	e.value = value;
	hTable[i].insertBack(e);
	this.size++;


   	return e;
    }

    /** 
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
	// Replace the following line with your solution.
	int i = compFunction(key.hashCode());

	if (hTable[i] == null){
	    return null;
	}
	try{
	    for (ListNode n = hTable[i].front(); n.isValidNode(); n = n.next()){
		Entry e = (Entry) n.item();
		if(e.key.equals(key)){
		    return e;
		}
	    }
	}
	catch(InvalidNodeException e){
	    return null;
	}


   	return null;
    }

    /** 
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        
        // Resize the hash table if necessary
        resize();

	int i = compFunction(key.hashCode());

	if (hTable[i] == null){
	    return null;
	}
	try{
	    for (ListNode n = hTable[i].front(); n.isValidNode(); n = n.next()){
		Entry e = (Entry) n.item();
		if(e.key.equals(key)){
		    if(hTable[i].length() == 0)
			hTable[i] = null;
		    else
			n.remove();

		    this.size--;
		    return e;
		}
	    }
	}
	catch(InvalidNodeException e){
	    return null;
	}

   	return null;
    }


     /** 
     *  Resizes this hash table if necessary. Expands the table if the load
     *  factor is too big and shrinks it if it is too small. Does nothing if
     *  The load factor is fine.
     *
     *  @return void
     */

    public void resize(){
        if((size*1.0)/N >= .75){ // If the load factor is too big, expand

            N = primeGreaterThan(2*N);
        }
        else if((size*1.0)/N <= .25){ // If the load factor is too small, shrink

            N = primeGreaterThan(N/2);
        }
        else{ // Otherwise, no need to do anything, so return.
            return;
        }
 
        // Change the compression variables, make a new table, and store the old table 

        p = primeGreaterThan(2*N);
        a = (int) (Math.random() * p);
        b = (int) (Math.random() * p);
        List[] oldTable = hTable;
        hTable = new List[N];

        // Get the entries from the old table and insert them in the new table
        for(int i = 0; i < oldTable.length; i++){
            List currList = oldTable[i];
            if(currList == null){
                continue;
            }
            try{ // Do or do not, there is no try
                ListNode currNode = currList.front();
                while(currNode.isValidNode()){
                    Entry item = (Entry) currNode.item();
                    insert(item.key(), item.value());
                    currNode = currNode.next();
                }
            }
            catch(InvalidNodeException e){
                System.err.println(e);
            }
        }

    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
	for(int i = 0; i < N; i++){
	    hTable[i] = null;
	}
	this.size = 0;
    }

    public void printHistogram(){
	int collisions = 0;
	int mostCollisions = 0;

	for(int i = 0; i < this.hTable.length; i++){

	    String stars = "";
	    if(hTable[i] != null){
		if(this.hTable[i].length() > mostCollisions)
		    mostCollisions = this.hTable[i].length();
		if(this.hTable[i].length() > 1)
		    collisions += this.hTable[i].length() - 1;
		for(int k = 0; k < this.hTable[i].length(); k++){
		    stars += "*";
		}
	    }
	    System.out.println(i + ": " + stars);
  	}

	System.out.println("\nNumber of Collisions " + collisions);
	System.out.println("Most Collisions: " + mostCollisions);
	System.out.println("Load Factor n/N: " + this.size + "/" + this.N + " = " + ((float)this.size)/this.N);

    }
}
