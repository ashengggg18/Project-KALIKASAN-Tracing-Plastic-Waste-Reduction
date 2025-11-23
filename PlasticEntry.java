import java.time.LocalDate;

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

