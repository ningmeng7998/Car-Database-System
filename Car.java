import java.util.ArrayList;
import java.util.Calendar;

/**
 * A class that includes all the information about cars.
 * @author LI NING
 * @version 18/10/2017
 */
public class Car
{
    private String regNo;
    private String yearMade;
    private ArrayList<String> colour;
    private String carMake;
    private String carModel;
    private String price;

    /**
     * A constructor to initialize the attributes in car class.
     */
    public Car()
    {
        colour = new ArrayList<>();
        regNo = "";
        yearMade = "";
        setColour("", "", "");
        carMake = "";
        carModel = "";
        price = "";     
    }

    /**
     * A constructor to initialize the attributes from user's input.
     * @param reg Registration number.
     * @param ym Year made of a car.
     * @param c1 Car color.
     * @param c2 Car color.
     * @param c3 Car color.
     * @param cMake Car make.
     * @param cModel Car model.
     * @param carPrice Car price.
     */ 
    public Car(String reg, String ym, String c1, String c2, String c3, String cMake, String cModel, String carPrice)
    {
        colour = new ArrayList<>();
        setRegNo(reg);
        setYearMade(ym);
        setColour(c1, c2, c3);
        setCarMake(cMake);
        setCarModel(cModel);
        setPrice(carPrice);
    }

    public void display()
    {
        System.out.println(toString());
    } 

    /**
     * Get the car make. 
     * @return Car make.
     */
    public String getCarMake()
    {
        return carMake;
    }

    /**
     * Get car model.
     * @return Car model.
     */
    public String getCarModel()
    {
        return carModel;
    }

    public ArrayList<String> getColour()
    {
        return colour;
    }

    /**
     * Get the price of a car.
     * @return The price of a car.
     */
    public String getPrice() 
    {
        return price;
    }

    /**
     * Get the registration number.
     * @return The registration number of a car.
     */
    public String getRegNo() 
    {
        return regNo;
    }

    /**
     * Get year made of a car.
     * @return The year made of a car.
     */
    public String getYearMade()
    {
        return yearMade;
    }

    /**
     * Set car make.
     * @param carMake The car make.
     */
    public void setCarMake(String carMake)
    {
        if (validLetterOrDigit(carMake) && !carMake.equals(""))
            this.carMake = carMake;
        else
            this.carMake = "";
    }

    /**
     * Set the car model.
     * @param carModel The car model.
     */
    public void setCarModel(String carModel)
    {
        if (validLetterOrDigit(carModel) && !carModel.equals(""))
            this.carModel = carModel;
        else
            this.carModel = "";
    }

    public void setColour(String c1, String c2, String c3) 
    {
    	colour.clear();
        if (!c1.equals("") && validLetter(c1))
            this.colour.add(0,c1);     
        else
            this.colour.add(0,"");
            
        if (!c2.equals("") && validLetter(c2) && !c2.equals(c1))
            this.colour.add(1, c2);
        else
            this.colour.add(1,"");
            
        if (!c3.equals("") && validLetter(c3) && !c3.equals(c1) && !c3.equals(c2))
            this.colour.add(2, c3);
        else
            this.colour.add(2,"");
    }

    /**
     * Set car price.
     * @param price A price.
     */
    public void setPrice(String price)
    {
        if (!price.equals("") && validDigit(price))
        {
            if (Integer.parseInt(price) > 0)
                this.price = price;
            else
                this.price = "";
        }
        else
            this.price = "";
    }

    /**
     * Set registration number.
     * @param regNo A registration number.
     */
    public void setRegNo(String regNo)
    {
        if (regNo.trim().length() >= 1 && regNo.trim().length() <= 6 && validLetterOrDigit(regNo))
            this.regNo = regNo;
        else 
            this.regNo = "";
    }

    /**
     * Set year made.
     * @param yearMade A year made.
     */
    public void setYearMade(String yearMade)
    {
        if (validDigit(yearMade) && 
        !yearMade.equals("") && 
        Integer.parseInt(yearMade) > 0 && 
        Integer.parseInt(yearMade) <= Calendar.getInstance().get(Calendar.YEAR))
            this.yearMade = yearMade;
        else 
            this.yearMade = "";
    }

    /**
     * Overwrite the toString method.
     */
    public String toString()
    {
        return "*Rigistration Number: " + regNo + "\n" +
        "*Year Made: " + yearMade + "\n" +
        "*Colour: " + colour.get(0) + "," + colour.get(1) + "," + colour.get(2) + "\n" +
        "*Car Make: " + carMake + "\n" +
        "*Car Model: " + carModel + "\n" + 
        "*Car Price: " + price;
    }

    /**
     * Check weather a string input is valid digits.     * 
     * @param aString The input string.
     * @return A boolean type of value.
     */
    public boolean validDigit(String aString) 
    {
        int position = 0;
        char thisCharacter;
        while (position < aString.length()) 
        {
            thisCharacter = aString.charAt(position);
            if (!Character.isDigit(thisCharacter))
                return false;
            position++;
        }
        return true;
    }

    /**
     * Check weather a string input is valid letter. 
     * @param aString The input string.
     * @return A boolean type of value.
     */
    public boolean validLetter(String aString) 
    {
        int position = 0;
        char thisCharacter;
        while (position < aString.length()) 
        {
            thisCharacter = aString.charAt(position);
            if (!Character.isLetter(thisCharacter))
                return false;
            position++;
        }
        return true;
    }

    /**
     * Check the if a string input is valid letter or digit. 
     * @param aString A String type.
     * @return A boolean type of value.
     */
    public boolean validLetterOrDigit(String aString) 
    {
        int position = 0;
        char thisCharacter;
        while (position < aString.length()) 
        {
            thisCharacter = aString.charAt(position);
            if (!Character.isLetterOrDigit(thisCharacter))
                return false;
            position++;
        }
        return true;
    }
}
