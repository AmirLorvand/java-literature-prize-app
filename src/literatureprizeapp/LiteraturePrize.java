package literatureprizeapp;

import java.util.ArrayList;
import java.util.List;

public class LiteraturePrize {

    private final int year;
    private final List<Laureate> laureates;

    public LiteraturePrize(int year) {
        this.year = year;
        this.laureates = new ArrayList<>();
    }

    public void addLaureate(Laureate laureate) {
        this.laureates.add(laureate);
    }

    public int getYear() {
        return year;
    }

    public List<Laureate> getLaureates() {
        return laureates;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("| ").append(year).append(" ");
        if (laureates.size() == 0) {
            stringBuilder.append("| ").append("    Not awarded   ").append("|\n");
        }
        for (Laureate laureate : laureates) {
            stringBuilder.append("| ").append(laureate.toString()).append("|\n");

        }
        return stringBuilder.toString();
    }
}
