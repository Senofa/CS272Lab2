/**
 * 
 * @author Zachary Holt
 * @date 1/31/19
 * @course CS272
 * @lab Lab 2
 * @purpose Container class designed to hold author names
 * 
 * @invariant numOfItems must always be less than or equal to dataArray.length
 * @invariant the data in the array must be stored from index 0 to numOfItems-1
 *
 */
public class StringSet {
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~member variables~~~~~~~~~~~~~~~~~~~~~~~~~
	private String[] dataArray;
	private int numOfEntries;
	
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * No Arg Constructor - creates an instance of StringSet with a capacity of 2
	 * @author Zachary Holt
	 * @precondition none
	 * @postcondition StringSet instance created with a capacity of 2 and no values entered
	 */
	public StringSet () {
		dataArray = new String[2];
		numOfEntries = 0;
	}//end no arg constructor
	
	
	/**
	 * Capacity Constructor - Creates an instance of StringSet with a non-negative capacity 
	 * @author Zachary Holt
	 * @precondition capacity known and passed in
	 * @postcondition StringSet instance created with a capacity controlled by param
	 * @param _capacity dictates the original array size
	 * @exception IllegalArgumentException _capacity cannot be negative
	 */
	public StringSet (int _capacity) {
		if (_capacity >= 0) {
			dataArray = new String[_capacity];
			numOfEntries = 0;
		} else {
			throw new IllegalArgumentException ("The initial capacity entered is negative: " + _capacity);
		} //end illegal value check
	}//end _capacity constructor
	
	
	/**
	 * Copy Constructor  - Creates a new instance of StringSet with the same member variables as the obj passed in
	 * @author Zachary Holt
	 * @precondition existing instance of StringSet or child passed in
	 * @postcondition new StringSet instance created with a copies of the passed member's vars
	 * @param _obj Needs to be of type StringSet or child
	 * @exception IllegalArgumentException _obj must be of type StringSet or child and not null
	 */
	public StringSet (Object _obj) {
		if (_obj instanceof StringSet && _obj !=null) {
			StringSet copySource = (StringSet) _obj;
			dataArray = new String[copySource.getArraySize()];
			numOfEntries = copySource.getNumOfEntries();
			for (int i = 0; i < numOfEntries; i++) {
				dataArray[i] = copySource.getDataArray()[i];
			}//end array copy loop
		} else {
			throw new IllegalArgumentException ("_obj must not be null and of type StringSet");
		}//end object type and not null check
	}//end copy constructor
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~mutators~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//gets
	public int getArraySize() {
		return (dataArray.length);
	}//end func getArraySize
	
	public String[] getDataArray() {
		return (dataArray);
	}//end func getDataArray
	
	public int getNumOfEntries() {
		return (numOfEntries);
	}//end func getNumOfEntries
	
	public int size() {
		return (getNumOfEntries());
	}//end func size
	
	public int capacity() {
		return (getArraySize());
	}//end func capacity
	
	
	/**
	 * add - adds a string to the StringSet method. Expands the array if needed
	 * @precondition _newString must not be null
	 * @postcondition dataArray has a new value _newString and might be bigger
	 * @param _newString String to be added to the StringSet bag. Must not be null
	 * @exception IllegalArgumentException _newString must not be null
	 */
	public void add (String _newString) {
		//check for non-null string
		if (_newString != null) {
			//check for room to add new string, call ensureCapacity w/ double the needed capacity
			if (dataArray.length == numOfEntries) {
				ensureCapacity((numOfEntries + 1) * 2);
			}//end not enough room check
			dataArray[numOfEntries] = new String(_newString);
			numOfEntries++;
		} else {
			throw new IllegalArgumentException ("New entry must not be null.");
		}//end null check
	} //end func add
	
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~other methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Private Ensure Capacity - Resizes the data array (if necessary) to the _minCapacity via deep copy
	 * @precondition non negative int capacity
	 * @postcondition dataArray will be of size _minCapacity unless _minCapaity is smaller than the current capacity
	 * @param _minCapacity Minimum capacity needed
	 */
	private void ensureCapacity (int _minCapacity) {
		//only resize the array if _minCapacity is bigger than current array
		if (dataArray.length < _minCapacity) {
			//make new temp array with size _minCapacity, deep copy from dataArray then repoint dataArray to temp location
			String[] tempArray = new String[(dataArray.length +1) * 2];
			for (int i = 0; i < numOfEntries; i++) {
				tempArray[i] = dataArray[i]; //its ok to reassign the element ref var's because we are about to reassign the whole array's ref var
			}//end deep copy loop (dataArray to tempArray
			dataArray = tempArray;
		}//end check for bigger array needed
	}//end func ensureCapacity
	
	/**
	 * contains - checks for a string matching _stringToFind
	 * @param _stringToFind The string the func is trying to find
	 * @return true if match was found false if no match found
	 */
	public boolean contains (String _stringToFind) {
		if (_stringToFind != null) {
			for (int i = 0; i < numOfEntries; i++) {
				if (dataArray[i].equals(_stringToFind)) {
					return true;
				}//end matching string check
			}//end array search loop
			
			//no match found
			return false;
		} else {
			return false;
		}//end null _compareString check
	}//end func contains
	
	/**
	 * toString - returns a string with all elements of dataArray tab delineated
	 * @return String containing the contents of dataArray delineated by tabs or if no elements returns a message indicating this 
	 */
	public String toString() {
		String returnString = "";
		if (numOfEntries == 0) {
			returnString = "No Entries Found";
		} else {
			//entries found, loop through array and put together returnString
			for (int i = 0; i < numOfEntries; i++) {
				returnString += dataArray[i] + "\t";
			}//end dataArray parse loop
		}//end any entries check
		
		return (returnString);
	}//end func toString
	
	/**
	 * remove - removes a string from dataArray if its found. decrements numOfEntries
	 * @param _whatToRemove the string to find and remove
	 * @return true when string is succesfully removed and false when arg is null or not found in dataArray
	 * @postcondition dataArray is modified
	 */
	public boolean remove (String _whatToRemove) {
		if (_whatToRemove != null) {
			//loop through dataArray and check for _whatToRemove
			//if found, replace with last array element and decrement numOfEntries
			for (int i = 0; i < numOfEntries; i++) {
				if (dataArray[i].equals(_whatToRemove)) {
					dataArray[i] = new String (dataArray[numOfEntries - 1]);
					numOfEntries--;
					return true;
				}//end equals to _whatToRemove check
			}//end dataArray search loop
			
			return false;
		} else {
			return false;
		}//end null arg check
	}//end func remove
	
	
	/**
	 * swap - swaps the values of two elements in dataArray
	 * @param _index1 First index to be swapped
	 * @param _index2 Second index to be swapped
	 * @postcondition dataArray is modified to have the two values at index1 and index2 swapped
	 */
	private void swap (int _index1, int _index2) {
		String temp = new String(dataArray[_index1]);
		dataArray[_index1] = new String(dataArray[_index2]);
		dataArray[_index2] = temp;
	}//end func swap
	
	
	/**
	 * sortAscend - sorts dataArray into ascending alphabetical order
	 * @postcondition dataArray is sorted alphabetically
	 */
	public void sortAscend() {
		for (int cur = 0; cur < numOfEntries; cur++) {
			for (int i = cur; i < numOfEntries; i++) {
				if (dataArray[i].compareTo(dataArray[cur]) < 0) {
					swap(i, cur);
				}//end need swap check
			}//end compare parse/swap loop (inside)
		}//end current compare loop (outside)
	}//end func sortAscend
	
	
	/**
	 * addOrdered - sorts dataArray and adds a string to it in the right location
	 * @precondition _newString is not null
	 * @precondition **Lab called for precondition that dataArray be ordered already but I wanted to make it more robust**
	 * @postcondition dataArray is sorted in ascending order with a new element
	 * @param _newString string to be added, must not be null
	 */
	public void addOrdered (String _newString) {
		if (_newString != null) {
			sortAscend();
			//System.out.printf("\naddOrderedTest numOfEntries: %d capacity: %d\n", numOfEntries, dataArray.length);
			if (numOfEntries == dataArray.length) {
				ensureCapacity((numOfEntries + 1) * 2);
			}//end enough capacity check
			for (int i = 0; i < numOfEntries; i++) {
				if (dataArray[i].compareTo(_newString) > 0) {
					//correct place found, put the new string in the last slot and swap with this slot. reorder after
					dataArray[numOfEntries] = new String (_newString);
					swap (i, numOfEntries);
					numOfEntries++;
					sortAscend();
					return;
				}//end check for right place
			}//end array search loop
			//if we make it here, the right place hasn't been found so just put it in the next spot
			dataArray[numOfEntries] = new String (_newString);
			numOfEntries++;
		} else {
			throw new IllegalArgumentException ("New element cannot be null.");
		}//end null arg check
	}//end addOrdered
	
	
	//Test Code
	public static void main(String[] args) {
		System.out.printf("%s\n", "Testing no arg constructor");
		StringSet bag1 = new StringSet();
		System.out.printf("No arg constructor ran, capacity: %d\n", bag1.capacity());
		
		System.out.printf("\n%s\n", "Testing capacity constructor with 5");
		StringSet bag2 = new StringSet(5);
		System.out.printf("Capacity constructor ran, capacity: %d\n", bag2.capacity());
		
		System.out.printf("\n%s\n", "Testing add method and toString methods on bag2");
		bag2.add("FirstTestYo");
		bag2.add("Here's Another Test!");
		bag2.add("Let's Try A Third.");
		bag2.add("Will four break it?");
		bag2.add("I guess not, 5 might?");
		bag2.add("Naw, but 6 probably will, resized array yo!");
		System.out.printf("%s\n", bag2);
		
		System.out.printf("\n%s\n", "Testing copy constructor with bag2");
		StringSet bag3 = new StringSet(bag2);
		System.out.printf("Copy constructor ran, capacity: %d\n", bag3.capacity());
		System.out.printf("bag3 contains: %s\n", bag3);
		
		System.out.printf("\n%s\n", "Testing bag3.contains with arg \"Let's Try A Third.\" Should return true");
		if (bag3.contains("Let's Try A Third.")) {
			System.out.printf("%s\n", "True");
		} else {
			System.out.printf("%s\n", "False");
		}//end contains check
		
		System.out.printf("\n%s\n", "Testing bag3.contains with arg \"Well, I'm not in there.\" Should return false");
		if (bag3.contains("Well, I'm not in there.")) {
			System.out.printf("%s\n", "True");
		} else {
			System.out.printf("%s\n", "False");
		}//end contains check
		
		System.out.printf("\n%s\n", "Testing bag3.contains with null arg. Should return false");
		if (bag3.contains(null)) {
			System.out.printf("%s\n", "True");
		} else {
			System.out.printf("%s\n", "False");
		}//end contains check
		
		System.out.printf("\n%s\n", "Testing bag3.remove with arg \"FirstTestYo\" Should remove successfully");
		System.out.printf("Before remove: %s\n", bag3);
		if (bag3.remove("FirstTestYo")) {
			System.out.printf("After remove: %s\n", bag3);
		} else {
			System.out.printf("%s\n", "String to remove not found");
		}//end bag3.remove response check
		
		System.out.printf("\n%s\n", "Testing bag3.remove with arg \"NawBro\" Should not be able to remove");
		System.out.printf("Before remove: %s\n", bag3);
		if (bag3.remove("NawBro")) {
			System.out.printf("After remove: %s\n", bag3);
		} else {
			System.out.printf("%s\n", "String to remove not found");
		}//end bag3.remove response check
		
		System.out.printf("\n%s\n", "Testing bag3.remove with null arg. Should not be able to remove");
		System.out.printf("Before remove: %s\n", bag3);
		if (bag3.remove(null)) {
			System.out.printf("After remove: %s\n", bag3);
		} else {
			System.out.printf("%s\n", "String to remove not found");
		}//end bag3.remove response check
		
		System.out.printf("\n%s\n", "Testing sortAscend");
		System.out.printf("Before sort: %s\n", bag3);
		bag3.sortAscend();
		System.out.printf("After sort: %s\n", bag3);
		
		System.out.printf("\n%s\n", "Testing bag1.addOrdered with arg \"AAAAA\"");
		System.out.printf("Before addOrdered: %s\n", bag1);
		bag1.addOrdered("AAAAA");
		System.out.printf("After addOrdered: %s\n", bag1);
		
		System.out.printf("\n%s\n", "Testing bag2.addOrdered with arg \"AAAAA\"");
		System.out.printf("Before addOrdered: %s\n", bag2);
		bag2.addOrdered("AAAAA");
		System.out.printf("After addOrdered: %s\n", bag2);
		
		System.out.printf("\n%s\n", "Testing bag2.addOrdered with arg \"HHHHH\"");
		System.out.printf("Before addOrdered: %s\n", bag2);
		bag2.addOrdered("HHHHH");
		System.out.printf("After addOrdered: %s\n", bag2);
		
		System.out.printf("\n%s\n", "Testing bag2.addOrdered with arg \"PPPPP\"");
		System.out.printf("Before addOrdered: %s\n", bag2);
		bag2.addOrdered("PPPPP");
		System.out.printf("After addOrdered: %s\n", bag2);
		
		
	}//end func main
	
}//end StringSet class
