import java.util.*;
import java.io.*;
/**
 * This class displays the searching results of a car from a database
 * And allow the users to add or delete car information.
 * @author LI NING
 * @version 18/10/2017
 */
public class ListCars 
{
    private ArrayList<Car> cars;

    /**
     * A constructor to initialize the attributes.
     */
    public ListCars() 
    {
        cars = new ArrayList<Car>();
    }
    
    /**
     * A constructor to initialize the attribute with parameter.
     * @param cars A car type of ArrayList.
     */
    public ListCars(ArrayList<Car> cars)
    {
    	this.cars = cars;
    }

    /**
     * Compare two strings.
     * @param stringA One comparing string.
     * @return A boolean type of value.
     */
    public boolean compareRegNo(String stringA) 
    {
        boolean equal = false;
        try 
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNext())
            {
                String aLine = diskScanner.nextLine();
                String aWord = getAWord(aLine, 0);
                if (stringA.equalsIgnoreCase(aWord))
                    equal = true;
            }
            diskScanner.close();
        } 
        catch (FileNotFoundException e)
        {
            System.out.println("The file doesn't exist.");
        } 
        return equal;
    }

    /**
     * Output cars that are found in database.
     * @param aString
     */
    public void displayFoundCar(String aString) 
    {
        if (!aString.equals(""))
            cars.add(newCar(aString));

        if (cars.size() != 0) 
        {
            print();
            cars.clear();
        }
        else
            System.out.println("No such car with this Registration Number.");
    }
    
    /**
     * Add a string to a text file.
     * @param aString The content of the string.
     */
    public void addCar(String aString)
    {
        try
        {
            FileWriter writer = new FileWriter("usedcars.txt",true);
            PrintWriter pw= new PrintWriter(writer);
            pw.write(aString + System.getProperty("line.separator"));
            pw.close();
            writer.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("Input and output error.");
        }
    }

    public void readFile(String regNo)
    {
        try 
        {
            FileReader inputFile = new FileReader("usedcars.txt");
            Scanner parser = new Scanner(inputFile);
            String line;
            while (parser.hasNextLine())
            {
                line = parser.nextLine();
                String[] words = line.split(",");
                if (!regNo.equals(words[0]))
                    cars.add(new Car(words[0],words[1],words[2],words[3],words[4],words[5],words[6],words[7]));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public void writeToFile()
    {
        try
        {
            PrintWriter outputFile = new PrintWriter("usedcars.txt");
            String message = "";
            for (int i = 0; i < cars.size(); i++)
            {             
                message = cars.get(i).getRegNo() + "," + cars.get(i).getYearMade() + "," + cars.get(i).getColour().get(0)
                        + "," + cars.get(i).getColour().get(1)+ "," + cars.get(i).getColour().get(2) + "," 
                        + cars.get(i).getCarMake() + "," + cars.get(i).getCarModel() + "," + cars.get(i).getPrice();
                

                outputFile.println(message);
            }       
            cars.clear();
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Input and output error.");
        }
    }

    /**
     * Get a line from a text file.
     * @param RegNo Matching string.
     * @return A found line from the text file.
     */
    public String getALine(String RegNo)
    {
        String aLine = "";
        try
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNext()) 
            {
                aLine = diskScanner.nextLine();
                if (RegNo.equalsIgnoreCase(getAWord(aLine, 0)) && !aLine.equals("")) 
                {
                    diskScanner.close();
                    return aLine;
                }
                else
                    aLine = "";
            }
            diskScanner.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File is not found.");
        }
        return aLine;
    }

    /**
     * Get a substring from a line.
     * @param line A line.
     * @param index An integer.
     * @return The substring in position index.
     */
    public String getAWord(String line, int index) 
    {
        String[] words = line.split(",");
        if (line.equals(""))
            return "";
        else
            return words[index];
    }

    /**
     * Get the current year information from the system.
     * @return The current year.
     */
    public int getCurrentYear()
    { 
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Set a new car object.
     * @param line A String type of line.
     * @return A Car type of object.
     */
    public Car newCar(String line) 
    {
        String[] words = line.split(",");
        return new Car(words[0], words[1], words[2], words[3], words[4], words[5], words[6], words[7]);
    }

    /**
     * Print the found cars in database.
     */
    public void print() 
    {
        System.out.println("");
        System.out.println("**************************");
        System.out.println("     Searching result");
        System.out.println("");
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) 
        {
            System.out.println(iterator.next());
            System.out.println("");
        }
        cars.clear();
        System.out.println("**************************");
    }

    /**
     * Search cars by car make.
     */
    public void searchCarMakeAndModel(String carMake, String carModel)
    {
        try 
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNextLine())
            {
                String aLine = diskScanner.nextLine();
                if (carMake.equalsIgnoreCase(getAWord(aLine, 5)) &&
                (carModel.equalsIgnoreCase(getAWord(aLine, 6)) || 
                    carModel.equalsIgnoreCase("ANY")))
                    cars.add(newCar(aLine));
            }
            diskScanner.close();
            if (cars.size() != 0)
            {
                print();
                cars.clear();
            } 
            else
                System.out.println("No such car with this Car Make and Car Model.");
        } 
        catch (IOException e) 
        {
            System.out.println("Input and Output error.");
        } 
        catch (NullPointerException e) 
        {

        }
    }

    public void searchCarAge(String carAge)
    {
        try
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNextLine())
            {
                String aLine = diskScanner.nextLine();
                String words[] = aLine.split(",");
                if (getCurrentYear() - Integer.parseInt(words[1]) <= Integer.parseInt(carAge) &&
                !aLine.equals(""))
                    cars.add(newCar(aLine));
            }
            if (cars.size() != 0) 
            {
                print();
                cars.clear();
            } 
            else
                System.out.println("No such car within this age.");
            diskScanner.close();
        }  
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch(NumberFormatException e)
        {
            System.out.println("Please input a reasonable car age.");
        }
    }

    /**
     * Search car by car price.
     * @param minPrice The minimum car price.
     * @param maxPrice The maximum car price.
     */
    public void searchCarPrice(String minPrice, String maxPrice) 
    {
        try 
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNextLine()) 
            {
                String aLine = diskScanner.nextLine();
                if (Integer.parseInt(getAWord(aLine, 7)) > Integer.parseInt(minPrice) &&
                Integer.parseInt(getAWord(aLine, 7)) <= Integer.parseInt(maxPrice) &&
                !aLine.equals(""))
                    cars.add(newCar(aLine));
            }
            diskScanner.close();
            if (cars.size() != 0) 
            {
                print();
                cars.clear();
            } 
            else
                System.out.println("No such car within this range.");
        } 
        catch (IOException e) 
        {
            System.out.println("Input and Output error.");
        } 
        catch (NullPointerException e) 
        {
            System.out.println("No car is found");
        } 
        catch(NumberFormatException e)
        {
            System.out.println("Please input a reasonable car price.");
        }
    }

    /**
     * Search a car in database by car colour.
     */
    /*
    public void searchingColour()
    {
    String aLine = "";
    System.out.println("Input a car colour for searching:");        
    try
    {
    String matchString = inputColour().replaceAll("\\s","");
    Scanner diskScanner = new Scanner(new File("usedcars.txt"));
    while (diskScanner.hasNextLine()) 
    {
    aLine = diskScanner.nextLine();
    String colours = getAWord(aLine,2) + getAWord(aLine,3) + getAWord(aLine,4);
    if (colours.toLowerCase().contains(matchString) && !matchString.equals("")) 
    cars.add(newCar(aLine));
    }
    diskScanner.close();
    if (cars.size() != 0) 
    {
    print();
    cars.clear();
    }
    else
    System.out.println("No such car with this colour.");
    } 
    catch (FileNotFoundException e) 
    {
    System.out.println("File is not found.");
    }
    }
     */
    /**
     * Search cars by registration number.
     * @return A string.
     */
    public String searchRegNo(String aString) 
    {
        String aLine = "";
        try
        {
            Scanner diskScanner = new Scanner(new File("usedcars.txt"));
            while (diskScanner.hasNext()) 
            {
                aLine = diskScanner.nextLine();
                if (aString.equalsIgnoreCase(getAWord(aLine, 0)))
                {
                    diskScanner.close();
                    return aLine;
                }
                else
                    aLine = "";
            }
            diskScanner.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("The file doesn't exist.");
        } 
        return aLine;
    }
}
