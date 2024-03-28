/**
 * 
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * Author: Hamza Khan
 * Date: Nov. 2023
 * Description: List being able to be save as records. Allows for user to be prompted
 * 				the choice of inserting, deleting, printing and even increasing the size of the array
 * 				and much more to allow the user much more friendly experiences  
 *     
 * METHOD LIST:
 * 
 * 	public VehicleList() {  //constructor 
 * 
 * 	public int getSize() { //gets the size 
 * 
 * 	public boolean add(VehicleRecord record) { //adding record 
 * 
 * 	public boolean delete (VehicleRecord record) { //deleting records
 * 
 * 	public boolean change (VehicleRecord oldR, VehicleRecord newR) { //changing record 
 * 
 * 	public String toString() { //method to convert list to string
 * 
 * 	public void increaseArraySize(int newSize) { //increasing the array 
 * 
 * 	public static void main(String[] args) { //main self testing 
 * 
 * 	public int linearSearch (String searchModel) { //searching for the model 
 * 
 * 	public int binarySearch (String searchKey) {  //searching for the make
 * 
 * 	public int binarySearch(VehicleRecord rec) { //using binary searching to find thw whole record 
 * 
 * 	public void insertSort () { // sorting alpha 	
 * 
 * 	public void selectSort() { //sorting by year of the cars 
 * 
 *  public void SaveTOFile(String fileName) { //saving file 
 * 
 * 	public boolean readTOFile(String fileName, int replace)  { //repalcing or adding the file as the new list 

 * 
 */
public class VehicleList {

	/**
	 * Private Instance Variables/Attributes  
	 */

	private VehicleRecord list []; //list of VechileRecord object 
	private int maxSize; // the maximum number of records the list can move
	private int size; //usable size

	//Default Constuctor  
	public VehicleList() {
		this.maxSize = 10;
		this.list = new VehicleRecord [this.maxSize];
		this.size = 0;   
	}
	//get size 
	public int getSize() {
		return size;
	}
	/*
	 * Method to add a record into the list 
	 * checks if the list is below the maxSize and adds 
	 * information accordingly.
	 */
	public boolean add(VehicleRecord record) {
		//if the size is below maxSize
		if (this.size < this.maxSize) {
			this.size++;//increae by 1 
			this.list [this.size -1] = record; //add to the record 
			return true; 
		}
		return false;
	}

	public VehicleRecord[] getList() {  //getter for list 
		return list;
	}



	/*
	 * Method to delete a record from list
	 */
	public boolean delete (VehicleRecord record) {

		// First, find the index of the record using binary search which will be the one to get deleted 
		int index = binarySearch(record.getMake());

		// If the record is not found, return false
		if (index == -1) {
			return false;
		}

		// Shift elements up to make sure that the space of the deleted record is covered 
		for (int i = index; i < this.size - 1; i++) {
			// Shift the element at index 'i + 1' to the current position 'i'
			this.list[i] = this.list[i + 1];


		}

		// Decrease the list size
		this.size--;

		insertSort(); //sort alpha

		return true; //return true if the record has been deleted effectilvey 
	}

	//Method to change replace old record info
	public boolean change (VehicleRecord oldR, VehicleRecord newR) {
		if (delete(oldR)) {
			add(newR);

			insertSort(); //sort alpha 

			return true; //add new record because old was found 
		}
		return false; //old record not found 
	}

	/*
	 * toString method to convert the list to to a string 
	 */
	public String toString() {
		String theList = "";
		for (int i = 0; i < this.getSize(); i++ ) {
			theList = theList + "Record " + i + ":" + list[i].toString() + "\n";
		}
		return theList;

	}

	
	public boolean increaseArraySize(int newSize) {
	    if (newSize > this.maxSize) {
	        //object
	        VehicleRecord[] newList = new VehicleRecord[newSize];

	        // Copy existing records to the new list
	        for (int i = 0; i < this.size; i++) {
	            newList[i] = this.list[i];
	        }

	        //set to newList and newSize 
	        this.list = newList;
	        this.maxSize = newSize;
	        return true;
	    } 
	    
        return true;

	 
	}

	public int linearSearch (String searchModel) { 
		//loop to run through the array
		for (int i = 0; i < this.size; i++) { 
			//to compare entered model value by the user to get the correct record 
			if (searchModel.equalsIgnoreCase(this.list[i].getModel())) { 
				return i; //return value 
			}
		}
		return -1;
	}



	public int binarySearch (String searchKey) { 


		int low = 0;
		int high = this.size - 1;
		int middle;

		//while the low end of my array is below the high end 
		while (low <= high) { 
			middle = (high + low)/2;
			if (searchKey.equals(list[middle].getMake())) { 
				return middle; //found it
			}

			else if (searchKey.compareTo(list[middle].getMake()) < 0) { 
				high = middle - 1; //chnage my high end of the array
			}
			else { 
				low = middle + 1;	 //chnage the low end  of the array 
			}
		}
		return -1; //not found 
	}

	//OVERLOADER Binary Serach
	public int binarySearch(VehicleRecord rec) {

		int low = 0;
		int high = size - 1;
		int middle;

		//while the low end of my array is below the high end 
		while (low <= high) { 
			middle = (high + low)/2; //divde the array in two 
			if (rec.toString().equals(list[middle].toString())) {  //compare the record to the matching record 
				return middle; //found it 
			}

			else if (rec.toString().compareToIgnoreCase(list[middle].toString()) < 0) {  //compare the record to see reulsting value 
				high = middle - 1; //chnage my high end of the array 
			}
			else { 
				low = middle + 1; //chnage the low emed of the array 
			}
		}
		return -1; //not found 
	}


	public void insertSort () { 		
		VehicleRecord currentIndex;
		int i, firstIndex;

		//sets the firstIndex value to a value of zero and the for loop dictates that if the
		//firstIndex is smaller than the list length than add one to the index  
		for (firstIndex = 0; firstIndex < size; firstIndex++) { 

			//sets current value to zero (which is the index value of 0)
			//works kind of like a placeholder to run through rest of the 
			//array
			currentIndex = this.list[firstIndex];

			//if the index value of 'firstIndex' is greater than zero then compare to other string 
			//if the first index is already 0 then it goes straight to the this.list array 
			//and it sets it to the the the index 0 for currentIndex
			//if i is greater than one and comparing currentIndex to this.list[i-1] results in -1 that means the value of index with 
			//the higher index value minuses 1 to set it before the lower index size and the lower index size takes the place of 
			//the higher one 
			for (i = firstIndex; (i > 0) && ((currentIndex.getMake()).compareToIgnoreCase((this.list[i-1].getMake())) < 0); i--) { 
				//swapping the index positions of the 
				this.list[i] = this.list[i-1];
			}
			this.list[i] = currentIndex;
		}
	}

	public void selectSort() { //selection sort 

		// loop to go through each element in the array
		for (int i = 0; i < this.size - 1; i++) {
			//Set or assume that the current index that we are going to work with is the minimum/lowest index 
			int minIndex = i;

			//loop to find the index of the minimum element in the unsorted part of the array
			for (int j = i + 1; j < this.size; j++) {
				// Compare the years of the vehicles at index j and minIndex
				if (this.list[j].getYear() < this.list[minIndex].getYear()) {
					// Update minIndex by swapping it with j if the vehicle at index j has an older year
					minIndex = j;
				}
			}

			// Swap the records at index i and minIndex
			// This makes sure that the vehicle with the older year is moved to its correct position
			//of beinf at the top 
			VehicleRecord temp = this.list[i];
			this.list[i] = this.list[minIndex];
			this.list[minIndex] = temp;
		}
	}
	
	/**
	 * Saving record to the file
	 */
	public void SaveTOFile(String fileName) {

		try  { 
			//creates new file to write to
			PrintWriter wFile = new PrintWriter(new FileWriter(fileName));
			for (int i = 0; i < this.maxSize; i++) { 
				if (this.list[i] == null) { 
					wFile.println("");
				}
				else { 
					wFile.println(this.list[i].getMake() + "/" + this.list[i].getModel() + "/" + this.list[i].getYear() + "/" + this.list[i].getType());
				}
			}
			//END OF FILE 
			wFile.print("EOF");
			//close the file
			wFile.close();
		} catch (IOException e) { 

		}
	}
	
	
	
	//reads the file and check if the file needs to be replaced or added 
	public boolean readTOFile(String fileName, int replace)  { 
		try {
			
			//open the file in fileName to read
			BufferedReader input = new BufferedReader(new FileReader (fileName));
			int dataSIZE = 0;
			
			//find the max size of list 
			while (!input.readLine().equalsIgnoreCase("EOF")) { 
				dataSIZE++;
			}
			
			//Checks to see if user wanted to replace data
			//OR 
			// if they want to add on to current data
			//based on their choice the code will set the size
			
			if (replace == 0) { 
				
				//Initializes size of information currently held to 0
				//this is because the data will be replaced 
				this.size = 0;
				
				//initializes new max size
				increaseArraySize(dataSIZE);
			}
			else { 
				//initializes new max size, and add on to the current
				//max size
				increaseArraySize(dataSIZE + this.maxSize);
			}
			
			input.close(); // Close file
			//opens file  to be read
			BufferedReader inputNumber2 = new BufferedReader(new FileReader(fileName));

			//loop through the file and save the data in the array
			while (true) { 
				
				//reads line from file and is stored into variable
				String data = inputNumber2.readLine();
				
				//Checks the line read from file to see if it contains information
				if ((!data.equalsIgnoreCase("EOF")) && (!data.equalsIgnoreCase(""))) {	
					VehicleRecord rec = new VehicleRecord();
					rec.processRecord(data); //splits the record in to elements 
					add(rec);//adds to list
				}
				
				//breaks out of infinite loop if next line is EOF or empty,
				//meaning no more information is left 
				else { 
					break;
				}
			}
			//close the input stream
			inputNumber2.close();
			return true;
			
			
			
			//ERROR TRAPPING 
		} catch (FileNotFoundException e) { 
			return false;
		}
		catch (NullPointerException e) {
			return false;
		}
		catch (NumberFormatException e)  { 
			return false;
		}
		
		
		
		
		//ERROR TRAPPING FOR THE INDEX AS WHEN I WAS RUNNING THE CODE I WOULD OFTEN
		//HAVE AN ERROR WHICH STATED THAT MY FIRST INDEX WAS OUT OF BOUNDS 
		catch (ArrayIndexOutOfBoundsException e) { 
			return false;
		}
		catch (IOException e) { 
			e.printStackTrace();
			return false;
			
		}
		
	}






	/**
	 * @param args
	 */

	//Self-Testing Method 
	public static void main(String[] args) {
		//Create vehicle list object
		VehicleList cars = new VehicleList();

		//infinite loop for testing 
		while(true) {
			char command;
			command = JOptionPane.showInputDialog(null,"Enter All CAPS Please!\n" + "I - Insert\n" + "L - Linear search by Model\n" + "B - Binary search by Make\n" + "Z - Alphabetical Order\n" + "N - SelectionSort by Year\n"+
					"A - Increase Array Size\n" + "F - BinarySearch by Record (OL)\n" + "S - SAVE\n"+ "R - READ\n" + "P - Print\n" + "D - Delete\n" + "C - Change\n" + "Q - Quit", "I").charAt(0);

			if (command == 'Q') {
				break; //break out of the loop 
			}
			switch (command) {
			case 'I': {  //INSERT RECORDS 

				//Prompt for info
				String record = JOptionPane.showInputDialog(null, "Enter <Make/Model/Year/Type", 
						"Honda/Civic/2006/s");

				//create a vehicle record object 
				VehicleRecord info = new VehicleRecord();
				//process info 
				info.processRecord(record);

				//checks to see if info can be added to list
				if (cars.add(info)) { 
					JOptionPane.showMessageDialog(null, "Insert Succesfull");
				}
				else {
					JOptionPane.showMessageDialog(null, "Insert NOT Succesfull");
				}
				break;
			}

			//DELETE RECORDS
			case 'D': {
				// Prompt user for the make of the record to delete
				String makeToDelete = JOptionPane.showInputDialog(null, "Enter the car make to delete!");

				// Create a object
				VehicleRecord rec = new VehicleRecord();

				// Set the the make to deleted 
				rec.setMake(makeToDelete);

				// Check if the record is deleted successfully
				if (cars.delete(rec)) {
					// Display a success message if the record is deleted
					JOptionPane.showMessageDialog(null, "Record Deleted");
				} else {
					// Display a message if the record is not found or could not be deleted
					JOptionPane.showMessageDialog(null, "Record not found or could not be deleted");
				}
				
				break;
			}

			//CHANGE RECORDS 
			case 'C': {
				String oldRecord = JOptionPane.showInputDialog(null, "Enter <Make/Model/Year/Type", 
						"Honda/Civic/2006/s");
				//create a vehicle record for old object 
				VehicleRecord oldInfo = new VehicleRecord();
				//process old information
				oldInfo.processRecord(oldRecord);
				String newRecord = JOptionPane.showInputDialog(null, "Enter <Make/Model/Year/Type", 
						"Honda/Civic/2006/s");
				//create a vehicle record for new information
				VehicleRecord newInfo = new VehicleRecord();
				//process new information
				newInfo.processRecord(newRecord);
				//checks to see if old info can be replaced by new info
				if (cars.change(oldInfo, newInfo)) {
					JOptionPane.showMessageDialog(null, "Record Changed");
				}
				else {
					JOptionPane.showMessageDialog(null, "Record NOT Changed/WAS NOT FOUND");
				} 
				break;

			}

			//PRINT LIST
			case 'P': {
				JOptionPane.showMessageDialog(null, cars.toString());
				break;
			}

			//INCREASING THE ARRAY SIZE 
			case 'A':{
				//prompts user for the new list size 
				int addedSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter the Value you would like to increse the array by:"));
				//calls increase list size method to increase size
				cars.increaseArraySize(addedSize);
				break;

			}
			//FINDING RECORD BY MODEL USING LINEAR SEARCH 
			case 'L': { 
				
				//ask user to enter the model they want to find 
				String modelToFind = JOptionPane.showInputDialog(null, "Enter the car model to find!");
				
				//find the loction
				int location = cars.linearSearch(modelToFind);
				
				if (location >=0) { 
					//if found display the record 
					JOptionPane.showMessageDialog(null, cars.getList()[location]);
				}
				else { 
					//if not found display message
					JOptionPane.showMessageDialog(null, modelToFind + " not found!");
				}
				break;

			}
			//FINDING RECORD BY MAKE USING BINARY SEARCH 
			case 'B': { 
				//ask user to eneter the make they want to find 
				String makeToFind = JOptionPane.showInputDialog(null, "Enter the car make to find!");
				
				//find the location of the make
				int location = cars.binarySearch(makeToFind);
			
				if (location >=0) { 
					//if found display the record 
					JOptionPane.showMessageDialog(null, "Record Found: " + cars.getList()[location]);
				}
				else { 
				
					//if not found display message
					JOptionPane.showMessageDialog(null, makeToFind + " not found!");
				}
				break;
			}

			//FINDING RECORD BY USING ENTERED RECORD USING BINARY SEARCH 
			case 'F': { 
			
				// ask the user to enter the record to find 
				String recToFind = JOptionPane.showInputDialog(null, "Enter the car record to find! <Make/Model/Year/Type>", "Honda/Civic/2006/s");

				//create object and process info
				VehicleRecord info = new VehicleRecord();
				info.processRecord(recToFind);

				//find the location of the search 
				int location = cars.binarySearch(info);

				if (location >=0) { 
					
					//if found display the record 
					JOptionPane.showMessageDialog(null, "Record Found: " + cars.getList()[location]);
				}
				else { 
					
					//if not found display message
					JOptionPane.showMessageDialog(null, recToFind + " not found!");
				}
				break;
			}

			//SORTING LIST BY ALPHA ORDER USING INSERT SORT  
			case 'Z': {
				// Call the selectSort method to perform the sorting
				cars.insertSort();
				
				// Display a message to inform the user that the list has been sorted
				JOptionPane.showMessageDialog(null, "List has been alphabetically sorted.");
				
				// Break out of the switch statement
				break;
			}
			
			
			//SORTING LIST BY YEAR ORDER USING SELECTION SORT  
			case 'N': {
				//OLDEST TO NEWEST 
				// Call the selectSort method to perform the sorting
				cars.selectSort();

				// Display a message to inform the user that the list has been sorted
				JOptionPane.showMessageDialog(null, "List has been sorted by the oldest to newest year.");

				// Break out of the switch statement
				break;
			}
			
			case 'S':{
				//prompts user for file name
				String fileName = JOptionPane.showInputDialog(null,"Enter the name of file to save","Records (1).txt");
				//calls method to save info to file
				cars.SaveTOFile(fileName);
				//shows confirmation message
				JOptionPane.showMessageDialog(null, fileName + " has been saved!");
				break;

			}

			case 'R':{
				// Prompt the user for the file name
				String fileName = JOptionPane.showInputDialog(null, "Enter the name of the file to save:", "Records (1).txt");

				// Prompt the user for the decision to replace or add
				int decision = JOptionPane.showConfirmDialog(null,
				        "Would you like to replace the current data with data from the file?",
				        "Replace or Add?",
				        JOptionPane.YES_NO_OPTION);

				// Continue with the rest of your code
				int replace;
				if (decision == JOptionPane.YES_OPTION) {
				    replace = 0; // User chose to replace
				} else {
				    replace = 1; // User chose to add
				}

				if (cars.readTOFile(fileName, replace)) {
				    JOptionPane.showMessageDialog(null, fileName + " has been opened and read!");
				} else {
				    JOptionPane.showMessageDialog(null, fileName + " could not be found OR is corrupted!\nCheck your file or the file name you've entered!");
				}
				break;
			}


			}


			}
		}
	}

