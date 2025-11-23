import java.util.Arrays;

abstract class User implements Reportable {
    private static int nextId = 1;
    private final int id;
    private String name;
    private PlasticEntry[] entries;
    private int entryCount = 0;

    public User(String name, int capacity) {
        this.id = nextId++;
        setName(name);
        this.entries = new PlasticEntry[capacity];
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            this.name = "Unnamed";
        else
            this.name = name.trim();
    }

    public boolean addEntry(PlasticEntry e) {
        if (e == null) return false;
        if (entryCount >= entries.length) return false;
        entries[entryCount++] = e;
        return true;
    }

    public PlasticEntry[] getEntries() {
        PlasticEntry[] copy = new PlasticEntry[entryCount];
        System.arraycopy(entries, 0, copy, 0, entryCount);
        return copy;
    }

    public abstract String getUserType();
    public abstract double calculateTotalReducedGrams();

    @Override
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("User %d — %s (%s)%n", id, name, getUserType()));
        sb.append("Entries:\n");
        PlasticEntry[] es = getEntries();
        if (es.length == 0) sb.append("  (no entries)\n");
        else for (PlasticEntry e : es) sb.append("  ").append(e.toString()).append("\n");
        sb.append(String.format("Total reduced (grams): %.0f%n", calculateTotalReducedGrams()));
        return sb.toString();
    }
}