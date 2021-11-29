public class Saturn extends Currency
{
    public Saturn(double n)
    {
        currencyName = "SaturnSilver";
        totalFunds = (n);
        planetName = "Saturn";
    }
    public double toEarthDollars(double amount)
    {
        amount /= SaturnSilver;
        amount = Math.round(amount * 100.0) / 100.0;
        return amount;
    }
    public double fromEarthDollars(double earthDollars)
    {
       earthDollars *= SaturnSilver;
       earthDollars = Math.round(earthDollars * 100.0) / 100.0;
       return earthDollars; 
    }
    
    public void exchange(Currency other, double amount)
    {
        if(totalFunds - amount < 0)
        {
            System.out.println("Uh oh - Saturn only has an available balance of $" + totalFunds + ", which is less than $" +amount +"\n");
        }
        else
        {
            totalFunds -= amount;
            totalFunds = Math.round(totalFunds *100.0)/100.0;
            other.totalFunds += other.fromEarthDollars(this.toEarthDollars(amount));
            other.totalFunds = Math.round(other.totalFunds *100.0)/100.0;
            
            System.out.println("Converting from "+currencyName+" to "+other.currencyName+" and initiating transfer...");
            System.out.println("$"+amount+ " " +currencyName + " = $" +toEarthDollars(amount) +" EarthDollars = $" +other.fromEarthDollars(toEarthDollars(amount))+" "+other.currencyName);
            System.out.println(planetName +" has a total of $" + totalFunds + " " +currencyName);
            System.out.println(other.planetName + " has a total of $" + other.totalFunds + " " + other.currencyName +"\n");
        }
    }
}
