public class StudentUser extends User {
    public StudentUser(String name, int capacity) { super(name, capacity); }
    
    @Override 
    public String getUserType() { return "Student"; }
    
    @Override 
    public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}