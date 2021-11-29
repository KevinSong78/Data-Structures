import java.util.Scanner; 
/**
 * Currency will exchange money between Mars, Neptune, and Saturn using conversions to Earth dollars.
 *
 * @Kevin Song
 * @9/29/21
 */
public abstract class Currency implements Exchangeable
{
    /*
     * These variables are used in the planets and methods are used in planets
     */
    double totalFunds;
    String currencyName;
    String planetName;
    public abstract double toEarthDollars(double amount);
    public abstract double fromEarthDollars(double EarthDollars);
    // main method
    public static void main(String args[])
    {
        /*
         * All variables initizalized here are only for the main method
         */
        Scanner s = new Scanner(System.in);
        System.out.println("Mars money:");
        Currency mars = new Mars(Double.parseDouble(s.next()));
        System.out.println("Neptune money:");
        Currency neptune = new Neptune(Double.parseDouble(s.next()));
        System.out.println("Saturn money:");
        Currency saturn = new Saturn (Double.parseDouble(s.next())); 

        System.out.println("<-- Exchanges -->\nEarth -> Mars: 1.30x\nEarth -> Saturn: 0.87x\nEarth -> Neptune: 2.00x");
        /*
         * These variables are initialized here to not create new variables each time in the loop
         */
        double amount = 0; 
        String planet = "";
        String receivePlanet = "";
        
        while(true)
        {
            // Choosing 2 planets to exchange from and to
            System.out.println("What do you want to exchange? (mars, neptune, saturn) or type end to stop");
            planet = s.next();
            if (planet.contains("end"))
            {
                System.exit(0); 
            }
            System.out.println("What do you want to receieve? (mars, neptune, saturn)");
            receivePlanet = s.next();
            // Choosing amount of money to transfer from the first planet
            System.out.println("How much?");
            try
            {
                amount = Double.parseDouble(s.next());
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid inputs, enter a number.");
                continue;
            }
            // Catches capitalization errors
            planet = planet.toLowerCase();
            receivePlanet = receivePlanet.toLowerCase();
            // Checks to determine which exchange method to call, not sure of the practicality as combinations increase rapidly with more planets but I
            // don't know another method.
            if (planet.contains("mars"))
            {
                if (receivePlanet.contains("neptune"))
                {
                    mars.exchange(neptune, amount);
                }
                else if (receivePlanet.contains("saturn"))
                {
                    mars.exchange(saturn, amount);
                }
                else
                {
                    System.out.println("Invalid inputs, enter a planet.\n");
                } 
            }
            else if (planet.contains("saturn"))
            {
                if(receivePlanet.contains("mars"))
                {
                    saturn.exchange(mars, amount);
                }
                else if (receivePlanet.contains("neptune"))
                {
                    saturn.exchange(neptune, amount);
                }
            
                else
                {
                    System.out.println("Invalid inputs, enter a planet.\n");
                }
            }
            else if(planet.contains("neptune"))
            {
                if(receivePlanet.contains("mars"))
                {
                    neptune.exchange(mars, amount);
                }
                else if (receivePlanet.contains("saturn"))
                {
                    neptune.exchange(saturn, amount);
                }
                else
                {
                    System.out.println("Invalid inputs, enter a planet.\n");
                }
            }     
            else
            {
                System.out.println("Invalid inputs, enter a planet.\n");
            }
        }
    }
}
