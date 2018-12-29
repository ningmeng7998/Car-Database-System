import java.util.Scanner;

/**
 * This class accept the users input and display menus.
 * @author LI NING
 * @version 18/10/2017
 */
public class SystemManager
{
	private ListCars listCars;
	private Car car;

	/**
	 * A constructor to initialize the attributes with parameters.
	 * @param aCar A ListCars type of parameter.
	 */
	public SystemManager(Car aCar, ListCars aListCar)
	{
		listCars = aListCar;
		car = aCar;
	}
	
	/**
	 * A constructor to initialize the attributes.
	 */
	public SystemManager()
	{
		listCars = new ListCars();
		car = new Car();
	}   

	public static void main(String[] args)
	{
		SystemManager systemManager = new SystemManager();
		systemManager.start();
	}

	/**
	 * Display main menu.
	 */
	public void displayMenu() 
	{
		System.out.println("");
		System.out.println("(1) Search Cars");
		System.out.println("(2) Add Car");
		System.out.println("(3) Delete Car");
		System.out.println("(4) Exit System");
		System.out.print("Choose an option between 1 to 4:");
	}

	/**
	 * Display search cars menu.
	 */
	public void displaySearchingMenu()
	{
		System.out.println("");
		System.out.println("         Search Cars");
		System.out.println("[1] By Registration Number");
		System.out.println("[2] By Car Make and Car Model");
		System.out.println("[3] By Age");
		System.out.println("[4] By Price (range)");
		System.out.println("[5] Back to Main Menu");
		System.out.print("Choose a searching option between 1 to 5:");
	}
	/**
	 * Input car age.
	 */
	public String inputCarAge() 
	{
		System.out.print("Input a car age: ");
		String carAge = "";
		Scanner keyboard = new Scanner(System.in);
		carAge = keyboard.nextLine().trim();
		try
		{
			if (!carAge.equals("") && car.validDigit(carAge))
			{
				if (Integer.parseInt(carAge) >= 0)
				{
					return carAge;
				} 
			} 
			else
				System.out.println("Error:Please input a non-negative integer as car age");
		}
		catch(NumberFormatException e)
		{
			System.out.println("");
		}
		return carAge;
	}

	/**
	 * Input a car make.
	 */
	public String inputCarMake()
	{
		System.out.print("Input a car make: ");
		Scanner keyboard = new Scanner(System.in);
		String aCarMake = keyboard.nextLine().trim();
		if (car.validLetterOrDigit(aCarMake) && !aCarMake.equals("")) 
			return aCarMake;
		else 
		{
			System.out.println("Invalid Car Make. ");
			return "";
		}
	}

	/**
	 * Input car model.
	 */
	public String inputCarModel() 
	{
		System.out.print("Input a car model: ");
		Scanner keyboard = new Scanner(System.in);
		String aCarModel = keyboard.nextLine().trim();
		if (car.validLetterOrDigit(aCarModel) && !aCarModel.equals("")) 
			return aCarModel;
		else 
		{
			System.out.println("Invalid Car Model.");
			return "";
		}
	}

	/**
	 * Input car price.
	 */
	public void inputCarPrice() 
	{
		System.out.println("");
		String minPrice = "";
		String maxPrice = "";
		try
		{
			System.out.print("Input a minimum car pirce: ");
			String price = inputPrice();
			if (!price.equals("")) 
			{
				minPrice = price;
				price = "";
				if (Integer.parseInt(minPrice) > 0 && car.validDigit(minPrice))
				{
					System.out.print("Input a maximum car pirce: ");
					price = inputPrice();
					if (!price.equals("")) 
					{
						maxPrice = price;
						if (Integer.parseInt(maxPrice) > 0 && car.validDigit(maxPrice))
						{
							if (Integer.parseInt(maxPrice) >= Integer.parseInt(minPrice))
								listCars.searchCarPrice(minPrice, maxPrice);
							else
								System.out.println(
										"Error: the maximum car price should be greater than the minimum car price. ");
						}
						else
							System.out.println("Error. Invalid car price.");
					}
				}
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please input a reasonable car price.");
		}
	}

	/**
	 * Input a car color.
	 * @return A string type of car color.
	 */
	public String inputColour()
	{
		String colour = "";
		String validColour = "";
		Scanner keyboard = new Scanner(System.in);
		if (keyboard.hasNextLine())
		{
			colour = keyboard.nextLine().trim();
			if (car.validLetter(colour))
				validColour = colour;
			else 
			{
				System.out.println("Invalid colour.");
				validColour = "";
			}
		}
		return validColour;
	}

	/**
	 * Input a car's price. 
	 */
	public String inputPrice() 
	{
		Scanner keyboard = new Scanner(System.in);
		String aPrice = "";
		aPrice = keyboard.nextLine().trim();
		String price = "";
		try
		{
			if (!aPrice.equals("") && car.validDigit(aPrice) && Integer.parseInt(aPrice) > 0)
				price = aPrice;
			else
			{
				System.out.println("Error. Invalid car price.");
				price = "";
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please input a reasonable car price.");
		}
		return price;
	}

	/**
	 * input registration number.
	 * @return A String type of registration number.
	 */
	public String inputRegNo()
	{
		System.out.println("");
		System.out.print("Input the registration number: ");
		Scanner keyboard = new Scanner(System.in);
		String aRegNo = keyboard.nextLine().trim();
		if(aRegNo.trim().length() >= 1 && aRegNo.trim().length() <= 6 && car.validLetterOrDigit(aRegNo))
			return aRegNo;
		else 
		{
			System.out.println("Invalid registration number! ");
			return "";
		}
	}

	/**
	 * Input a car's year made. 
	 * @return A string type of year made.
	 */
	public String inputYearMade() 
	{
		System.out.println("");
		System.out.print("Input year made: ");
		Scanner keyboard = new Scanner(System.in);
		String yearMade = keyboard.nextLine().trim();
		String year = "";
		try
		{
			if (car.validDigit(yearMade) && !yearMade.equals("") && Integer.parseInt(yearMade) > 0)
			{
				if (Integer.parseInt(yearMade) <= listCars.getCurrentYear())
					year = yearMade;
				else
				{
					year = "";
					System.out.println("Error. Year made cannot be greater than current year.");
				}               
			}
			else
				System.out.println("Error. Please input a positive integer as year made. ");
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please input a reasonable year made.");
		}
		return year;
	}

	/**
	 * Searching Options.
	 */
	public void searchingOptions() 
	{
		String searchNo = "";
		do
		{
			Scanner keyboard = new Scanner(System.in);
			searchNo = keyboard.nextLine().trim();
			switch (searchNo) 
			{
			case "1":
				String RegNo = inputRegNo();
				if (!RegNo.equals(""))
					listCars.displayFoundCar(listCars.searchRegNo(RegNo));
				displaySearchingMenu();
				searchNo = "";
				break;
			case "2":
				String carMake = inputCarMake();
				if (!carMake.equals("") && car.validLetterOrDigit(carMake))
				{
					String carModel = inputCarModel();
					if (!carModel.equals("") && car.validLetterOrDigit(carModel))
						listCars.searchCarMakeAndModel(carMake,carModel);
				}
				displaySearchingMenu();
				searchNo = "";
				break;
			case "3":
				String carAge = inputCarAge();  
				if (!carAge.equals(""))
					listCars.searchCarAge(carAge);
				displaySearchingMenu();
				searchNo = "";
				break;
			case "4":
				inputCarPrice();    
				displaySearchingMenu();
				searchNo = "";
				break;
			case "5":           
				break;
			default:
			{
				System.out.println("No such an option. Please choose an option between 1 - 5.");
				displaySearchingMenu();
			}
			}
		} 
		while (!searchNo.equals("5"));
	}

	/**
	 * Add a new car to the database.
	 */
	public void setNewCar()
	{
		Car newCar = new Car();
		String regNo = inputRegNo();
		while (regNo.equals(""))
		{
			regNo = inputRegNo();
		}

		if (listCars.compareRegNo(regNo))
			System.out.println("This car has already existed.");
		else 
		{
			newCar.setRegNo(regNo);
			String yearMade = inputYearMade();
			while (yearMade.equals("")) 
				yearMade = inputYearMade();
			newCar.setYearMade(yearMade);
			System.out.print("Input first car colour: ");
			String colour1 = inputColour();
			while (colour1.equals(""))
			{
				System.out.println("You must enter as leat one colour.");
				System.out.print("Input first car colour: ");
				
				colour1 = inputColour();
			}
			System.out.print("Input second car colour(Press Enter if this colour does not exist): ");
			String colour2 = inputColour();
			while (colour2.equals(colour1)) 
			{
				System.out.println("Duplicated colour.");
				System.out.print("Input second car colour(Press Enter if this colour does not exist): ");
				colour2 = inputColour();
			}
			System.out.print("Input third car colour(Press Enter if this colour does not exist): ");
			String colour3 = inputColour();
			while (colour3.equals(colour1) || (colour3.equals(colour2) && !colour2.equals("")))
			{
				System.out.println("Duplicated colour.");
				System.out.print("Input third car colour(Press Enter if this colour does not exist): ");
				colour3 = inputColour();
			}
			newCar.setColour(colour1, colour2, colour3);
			String carMake = inputCarMake();
			while (carMake.equals("")) 
				carMake = inputCarMake();
			newCar.setCarMake(carMake);
			String carModel = inputCarModel();
			while (carModel.equals(""))
				carModel = inputCarModel();
			newCar.setCarModel(carModel);
			System.out.print("Input a car price: ");
			String carPrice = inputPrice();
			while (carPrice.equals(""))
			{
				System.out.print("Input a car price: ");
				carPrice = inputPrice();					
			}
			newCar.setPrice(carPrice);
			String message = newCar.getRegNo() + "," + newCar.getYearMade() + "," + newCar.getColour().get(0)
					+ "," + newCar.getColour().get(1)+ "," + newCar.getColour().get(2) + "," 
					+ newCar.getCarMake() + "," + newCar.getCarModel() + "," + newCar.getPrice();
			listCars.addCar(message);
			System.out.println("You have added a car to the database.");
		}

	}
	/**
	 * Start the database system.
	 */
	public void start() 
	{
		String optionNumber = "0";
		Scanner keyboard = new Scanner(System.in);
		do 
		{
			displayMenu();
			optionNumber = keyboard.nextLine().trim();

			switch (optionNumber) 
			{
			case "1": 
				displaySearchingMenu();
				searchingOptions();
				break;
			case "2":
				setNewCar();
				break;
			case "3":
				String regNo = inputRegNo();
				if (car.validLetterOrDigit(regNo) && !regNo.equals(""))
				{
					if (!listCars.searchRegNo(regNo).equals("")&& 
							regNo.trim().length() >= 1 && regNo.trim().length() <= 6)
					{
						listCars.readFile(regNo);
						listCars.writeToFile();
						System.out.println("The car has been deleted.");
					}
					else 
						System.out.println("This car does not exist.");
				}
				break;
			case "4":
				System.exit(0);
			default:
				System.out.println("No such an option. Please choose an option between 1 to 4.");
			}
		} 
		while (!optionNumber.equals("4"));
	}   
}