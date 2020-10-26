import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiling Wang
 * @author Aleksandar Veselinovic
 * @author Ali Fahd
 */

public class Player {

    private String name;//player name
    private List<Country> countries = new ArrayList<>();//player's country list
    
    
    /**
    * @Constructor
    */
    public Player(String name) {
        this.name = name;
    }
    
    
    /**
    *@param c Country, add c into list
    */
    public void addCountry(Country c){
        countries.add(c);
    }
    
    
    /**
    *@param c Country, remove c from list
    */
    public void removeCountry(Country c){
        countries.remove(c);
    }
    
    
    /**
     * This method is used for the initial setup to distribute troops between the countries
     * currently evenly so that each country is able to attack (need to have more than 1 troop)
    *@param troops int
    */
    public void distributeTroops(int troops){
        //loop through and evenly distribute the troops while there are still troops left to be placed
        while(troops != 0){
            for (Country c: countries){
                if (troops > 0){
                    c.addTroops(1);
                    troops--;
                }
            }
        }
    }
    
    
    /**
    *@return player's name
    */
    public String getName(){
        return name;
    }
    
    
    /**
    *@return player's country list
    */
    public List<Country> getCountries(){
        return countries;
    }

}
