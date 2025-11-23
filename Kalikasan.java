import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/* ---------- Interface: Reportable ---------- */
interface Reportable {
    String generateReport();
}

/* ---------- Data class: PlasticEntry ---------- */
class PlasticEntry {
    private LocalDate date;
    private int plasticUsedGrams;
    private int plasticRecycledGrams;

    public PlasticEntry(LocalDate date, int used, int recycled) {
        this.date = date;
        this.plasticUsedGrams = used;
        this.plasticRecycledGrams = recycled;
    }

    public LocalDate getDate() { return date; }
    public int getPlasticUsedGrams() { return plasticUsedGrams; }
    public int getPlasticRecycledGrams() { return plasticRecycledGrams; }

    @Override
    public String toString() {
        return String.format("%s — used: %dg, recycled/avoided: %dg",
                date, plasticUsedGrams, plasticRecycledGrams);
    }
}

/* ---------- Custom Exception ---------- */
class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}

/* ---------- Abstract class: User ---------- */
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

/* ---------- Subclasses ---------- */
class StudentUser extends User {
    public StudentUser(String name, int capacity) { super(name, capacity); }
    @Override public String getUserType() { return "Student"; }
    @Override public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}

class HouseholdUser extends User {
    public HouseholdUser(String name, int capacity) { super(name, capacity); }
    @Override public String getUserType() { return "Household"; }
    @Override public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}

class BusinessUser extends User {
    public BusinessUser(String name, int capacity) { super(name, capacity); }
    @Override public String getUserType() { return "Business"; }
    @Override public double calculateTotalReducedGrams() {
        double sum = 0;
        for (PlasticEntry e : getEntries()) sum += e.getPlasticRecycledGrams();
        return sum;
    }
}

/* ---------- MAIN APPLICATION ---------- */
public class Kalikasan {
    private static final int MAX_USERS = 20;
    private User[] users = new User[MAX_USERS];
    private int userCount = 0;
    private Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static void main(String[] args) {
        new KalikasanApp().run();
    }

    private void run() {
        System.out.println("Welcome to KALIKASAN — Plastic Waste Tracker");
        boolean running = true;
        while (running) {
            try {
                showMenu();
                String raw = scanner.nextLine().trim();
                if (raw.isEmpty()) { System.out.println("Please choose an option."); continue; }
                int choice = Integer.parseInt(raw);
                switch (choice) {
                    case 1: createUser(); break;
                    case 2: addEntry(); break;
                    case 3: showUserReports(); break;
                    case 4: showSystemSummary(); break;
                    case 5: deleteUser(); break;
                    case 0: running = false; break;
                    default: System.out.println("Unknown option. Try again."); break;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException iie) {
                System.out.println("Input error: " + iie.getMessage());
            } catch (DateTimeParseException dtpe) {
                System.out.println("Invalid date format. Use MM-DD-YYYY.");
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
            }
        }
        System.out.println("Goodbye - keep reducing plastic!");
        scanner.close();
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Create user");
        System.out.println("2) Add daily entry");
        System.out.println("3) Show user reports");
        System.out.println("4) System summary");
        System.out.println("5) Delete user (by ID)");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private void createUser() throws InvalidInputException {
        if (userCount >= users.length)
            throw new InvalidInputException("User capacity reached.");
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) throw new InvalidInputException("Name cannot be empty.");
        System.out.print("Choose type (1=Student,2=Household,3=Business): ");
        int t = Integer.parseInt(scanner.nextLine().trim());
        User u;
        switch (t) {
            case 1: u = new StudentUser(name, 365); break;
            case 2: u = new HouseholdUser(name, 365); break;
            case 3: u = new BusinessUser(name, 365); break;
            default: throw new InvalidInputException("Invalid user type.");
        }
        users[userCount++] = u;
        System.out.println("Created user: " + u.getName() + " (ID=" + u.getId() + ")");
    }

    private void addEntry() throws InvalidInputException {
        if (userCount == 0)
            throw new InvalidInputException("No users exist. Create one first.");

        System.out.print("Enter user ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        User u = findUserById(id);
        if (u == null) throw new InvalidInputException("User not found.");

        System.out.print("Date (MM-DD-YYYY) or leave blank for today: ");
        String dateInput = scanner.nextLine().trim();

        LocalDate date = dateInput.isEmpty()
                ? LocalDate.now()
                : LocalDate.parse(dateInput, DATE_FORMAT);

        System.out.print("Plastic used (grams): ");
        int used = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Plastic recycled/avoided (grams): ");
        int recycled = Integer.parseInt(scanner.nextLine().trim());

        if (used < 0 || recycled < 0)
            throw new InvalidInputException("Values must be non-negative.");

        PlasticEntry e = new PlasticEntry(date, used, recycled);

        boolean ok = u.addEntry(e);
        if (!ok) {
            System.out.println("User entry storage full; entry not saved.");
            return;
        }

        System.out.println("Entry added.");

        //  MESSAGE SYSTEM BASED ON USER INPUT:
        if (recycled > 0) {
            System.out.println(
                    "Amazing work!\n" +
                    "You're making a real difference! Here's the transformative impact of your effort today:\n\n" +
                    " Transformative Impact You Made:\n" +
                    " You helped lower the amount of plastic entering landfills.\n" +
                    " You contributed to cleaner oceans and waterways.\n" +
                    " You reduced carbon emissions created from plastic production.\n" +
                    " You helped protect wildlife from harmful plastic pollution.\n\n" +
                    " Keep it up! Every small action shapes a more sustainable future.\n"
            );
        } else {
            System.out.println(
                    " It's okay-there's always room to grow! \n\n" +
                    " Ways You Can Reduce Waste:\n" +
                    " Bring reusable bags, bottles, and containers.\n" +
                    " Choose products with minimal or eco-friendly packaging.\n" +
                    " Practice recycling and proper waste segregation.\n" +
                    " Avoid single-use plastics like straws, cups, and utensils.\n\n" +
                    " Possible Environmental Impacts When Waste Isn't Reduced:\n" +
                    " More plastic ends up in landfills.\n" +
                    " Increased marine pollution harms sea life.\n" +
                    " Higher carbon emissions from new plastic production.\n" +
                    " More litter and environmental hazards.\n\n" +
                    "Don't worry-small steps can create amazing change. You can start again tomorrow! \n" +
                    "Let's Try Again!\n"
            );
        }
    }

    private User findUserById(int id) {
        for (int i = 0; i < userCount; i++)
            if (users[i].getId() == id) return users[i];
        return null;
    }

    private void showUserReports() {
        if (userCount == 0) {
            System.out.println("No users to report.");
            return;
        }
        for (int i = 0; i < userCount; i++)
            System.out.println("\n" + users[i].generateReport());
    }

    private void showSystemSummary() {
        double total = 0;
        for (int i = 0; i < userCount; i++)
            total += users[i].calculateTotalReducedGrams();
        System.out.println("System total reduced (grams): " + (long) total);
        if (userCount > 0) {
            User top = users[0];
            for (int i = 1; i < userCount; i++) {
                if (users[i].calculateTotalReducedGrams() > top.calculateTotalReducedGrams())
                    top = users[i];
            }
            System.out.println("Top contributor: " + top.getName() + " (ID=" + top.getId()
                    + ") — " + (long) top.calculateTotalReducedGrams() + "g reduced");
        }
    }

    private void deleteUser() throws InvalidInputException {
        if (userCount == 0)
            throw new InvalidInputException("No users to delete.");
        System.out.print("Enter user ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        int idx = -1;
        for (int i = 0; i < userCount; i++)
            if (users[i].getId() == id) { idx = i; break; }
        if (idx == -1) throw new InvalidInputException("User not found.");
        for (int i = idx; i < userCount - 1; i++) users[i] = users[i + 1];
        users[userCount - 1] = null;
        userCount--;
        System.out.println("User ID " + id + " deleted.");
    }
}
