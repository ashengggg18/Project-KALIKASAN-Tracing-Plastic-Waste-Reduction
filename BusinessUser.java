public class BusinessUser extends User {
    public BusinessUser(String name, int capacity) { super(name, capacity); }
    
    @Override 
    public String getUserType() { return "Business"; }
    
    @Override 
    public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}