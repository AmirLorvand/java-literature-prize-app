package literatureprizeapp;

public class Laureate {

    private final String name;
    private final int birthYear;
    private final Integer deathYear; // Can be null
    private final String[] nationality;
    private final String[] language;
    String citation;
    private final String[] genres;

    public Laureate(String name, int birthYear, Integer deathYear, String[] nationality, String[] language, String citation, String[] genres) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.language = language;
        this.citation = citation;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getDeathYear() {
        if (deathYear == null) {
            return "------";
        } else {
            return deathYear.toString();
        }

    }

    public String[] getNationality() {
        return nationality;
    }

    public String[] getLanguage() {
        return language;
    }

    public String getCitation() {
        return citation;
    }

    public String[] getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return name
                + "[ "
                + (String.join(", ", nationality))
                + " ]";
    }
}
