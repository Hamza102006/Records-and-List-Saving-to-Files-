/**
 * 
 */

/**
 * Author; Hamza Khan
 * Date; November, 2023
 * Desc. Represents objects containing vehicle records 
 * 
 * 
 * Method List;
 * 
 * 	public VehicleRecord() { //default constructor
 * 
 * 	public VehicleRecord(String record) { //over loader constructor
 *  
 * 	public String toString() { //method to turn list in to string 
 * 
 * 	public void processRecord (String record) { //processing the records 
 * 
 *  Many Getters and Setters 
 * 
 */
public class VehicleRecord {


	/**
	 * Instananeous Private Data
	 */

	private String make;
	private String model;
	private int year;
	private char type;


	//default constructor
	public VehicleRecord() {
		//intialize with apporiate values 
		this.make = "";
		this.model = "";
		this.year = 0;
		this.type = ' ';  
	}


	//overloader constructor 
	public VehicleRecord(String record) {
		//intialize with apporiate values 
		processRecord(record);
	}




	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}



	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}


	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}



	//create toString method 
	public String toString() {
		String typeConvert; //string 

		//convert the char to a word

		switch (type) {
		case 'p':
			typeConvert = "Passeneger";
			break;

		case 'v':
			typeConvert = "Van";
			break;

		case 's':
			typeConvert = "SUV";
			break;

		case 't':
			typeConvert = "Truck";
			break;

		default:
			typeConvert = "Unknown";
		}

		//return statement 
		return "Vehicle Make: " + make +  " Vehicle Model: " + model + " Year: " + year
				+ " Type: " + typeConvert;
	}



	//METHOD THAT WILL PROCESS RECORD 
	// Record format; Make, Model, year and type
	public void processRecord (String record) {
		String word[];
		word = record.split("/"); //splits the record in to elements 
		this.make = word[0];
		this.model = word[1];   // assign the elements into different private data
		this.year = Integer.parseInt(word[2]);
		this.type = word[3].charAt(0);

	}





	//self testing main
	public static void main(String[] args) {
		//create an object based on a record 
		String rec = "Honda/Civic/2006/s";


		//create object 
		VehicleRecord info = new VehicleRecord(); //test teh constucotr 

		//test toString
		System.out.println(info.toString());

		info.processRecord(rec);//test teh record data 

		System.out.println(info.toString()); //test with data


		//test the setters and getters
		info.setMake("Benz");
		info.setModel("G Wagon");
		info.setYear(2023);
		info.setType('v');

		//display  
		System.out.println(info.toString()); //test with data

		//test the getters

		System.out.println(info.getMake()); //test with data
		System.out.println(info.getModel()); //test with data
		System.out.println(info.getYear()); //test with data
		System.out.println(info.getType()); //test with data



	}


}


