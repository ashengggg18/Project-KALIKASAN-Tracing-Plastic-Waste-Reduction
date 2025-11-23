public class HouseholdUser extends User {
    public HouseholdUser(String name, int capacity) { super(name, capacity); }
    
    @Override 
    public String getUserType() { return "Household"; }
    
    @Override 
    public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}