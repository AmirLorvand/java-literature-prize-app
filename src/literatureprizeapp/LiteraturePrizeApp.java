package literatureprizeapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LiteraturePrizeApp {

    //List to store LiteraturePrize objects
    private static final List<LiteraturePrize> literaturePrizesList = new ArrayList<>();
    //Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Load literature prizes from file
        loadLiteraturePrizes();
        int choice;

        //Main menu loop
        do {
            //Display the main menu
            displayMenu();

            //Read the user's choice
            choice = Integer.parseInt(scanner.nextLine());

            //Execute the selected action
            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    select();
                    break;
                case 3:
                    search();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, Please try again");
                    break;
            }
        } while (choice != 0);

        //Close the scanner
        scanner.close();
    }

    //Method to load literature prizes from a file
    private static void loadLiteraturePrizes() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/literature-prizes.txt"))) {
            String line;
            int year;
            int lineCount = 1;
            String[] parts = new String[0];
            String[] nameBirthDeathSplit = new String[0];
            String[] years;
            String birthYear = null;
            String deathYear = null;
            Laureate laureate;
            LiteraturePrize literaturePrize = null;
            StringBuilder citation = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty() || line.equals("-----")) {
                    literaturePrizesList.add(literaturePrize);
                    lineCount = 1;
                    literaturePrize = null;
                    citation = new StringBuilder();
                } else if (lineCount == 1) {
                    year = Integer.parseInt(line.trim());
                    literaturePrize = new LiteraturePrize(year);
                    lineCount++;
                } else if (lineCount == 2) {
                    if (!line.equals("Not awarded")) {
                        parts = line.split("\\|");
                        nameBirthDeathSplit = parts[0].trim().split(" \\(");
                        years = nameBirthDeathSplit[1].replaceAll("[^0-9\\-]", "").split("-");
                        birthYear = years[0];
                        deathYear = years.length > 1 ? years[1] : null;
                    }
                    lineCount++;
                } else if (lineCount == 3) {
                    citation.append(line).append(" ");
                    lineCount++;
                } else {
                    String[] genres = line.split(", ");
                    laureate = new Laureate(nameBirthDeathSplit[0], Integer.parseInt(birthYear), deathYear == null ? null : Integer.parseInt(deathYear), parts[1].trim().split(","), parts[2].trim().split(","), citation.toString(), genres);
                    literaturePrize.addLaureate(laureate);
                    lineCount = 2;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading literature-prizes.txt file: " + e.getMessage());
            System.exit(0);
        }
    }

    //This method allows the user to list literature prize winners within a specified range of years
    private static void list() {
        int start;
        int end;
        do {
            System.out.println("Enter start year >");
            start = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter end year >");
            end = Integer.parseInt(scanner.nextLine());

            //Validates the input years
            if (start < 1901 || end > 2023 || start >= end) {
                System.out.println("Invalid input. Start year must be between 1901 and 2023, end year must be between start year and 2023.");
            }
        } while (start < 1901 || end > 2023 || start >= end);

        StringBuilder stringBuilder = new StringBuilder();
        //Formatting the header of the list
        stringBuilder.append("---------------------------------------------------------\n");
        stringBuilder.append("| Year | Prize winners (and associated nations) |\n");
        stringBuilder.append("---------------------------------------------------------\n");
        //Iterates through the literature prizes and adds them to the list if they fall within the specified range
        for (LiteraturePrize prize : literaturePrizesList) {
            if (prize.getYear() >= start && prize.getYear() <= end) {
                stringBuilder.append(prize.toString());
            }
        }
        stringBuilder.append("---------------------------------------------------------\n");
        System.out.println(stringBuilder);
    }

    //This method allows the user to select a specific year and view the literature prize winners for that year
    private static void select() {
        int year;
        do {
            System.out.println("Enter year of prize >");
            year = Integer.parseInt(scanner.nextLine());

            //Validates the input year
            if (year < 1901 || year > 2023) {
                System.out.println("Invalid input, Year must be between 1901 and 2023");
            }
        } while (year < 1901 || year > 2023);

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("| Winner(s)               | Born | Died | Language(s) | Genre(s)   |");
        System.out.println("------------------------------------------------------------------------------------------");
        StringBuilder stringBuilder = new StringBuilder();
        //Iterates through the literature prizes and prints the laureates for the selected year
        for (LiteraturePrize prize : literaturePrizesList) {
            if (prize.getYear() == year) {
                if (prize.getLaureates().size() == 0) {
                    System.out.println("                   Not awarded                       ");
                }
                for (Laureate laureate : prize.getLaureates()) {
                    stringBuilder.append("| ").append(laureate.getName()).append("\t  | ").append(laureate.getBirthYear()).append(" | ").append(laureate.getDeathYear()).append(" | ")
                            .append(String.join(",", laureate.getLanguage())).append("|").append(String.join(",", laureate.getGenres())).append("|\n");
                    stringBuilder.append("------------------------------------------------------------------------------------------\n");
                    stringBuilder.append("                                             Citation:                                    \n");
                    stringBuilder.append(laureate.getCitation()).append("\n");
                    stringBuilder.append("------------------------------------------------------------------------------------------\n");
                }
                System.out.println(stringBuilder);
                break;
            }
        }
    }

    //This method allows the user to search for literature prize winners based on a specific writing genre
    private static void search() {
        //Prompt the user to enter a search term for the writing genre
        System.out.print("Enter search term for writing genre > ");
        String input = scanner.nextLine();

        //Print the header for the search results table
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("| Name                        | Genres                                             | Year |");
        System.out.println("------------------------------------------------------------------------------------------");

        //Reverse the list of literature prizes to start searching from the most recent prizes
        Collections.reverse(literaturePrizesList);

        //Iterate through the literature prizes and their laureates to find matches for the search term
        for (LiteraturePrize prize : literaturePrizesList) {
            for (Laureate laureate : prize.getLaureates()) {
                for (String genre : laureate.getGenres()) {
                    //Check if the genre contains the search term
                    if (genre.contains(input)) {
                        //Create a list to store the genres with the search term highlighted
                        List<String> changeGenres = new ArrayList<>();
                        for (String changGenre : laureate.getGenres()) {
                            //Find the index of the search term in the genre
                            int startIndex = changGenre.indexOf(input);
                            int endIndex = startIndex + input.length() - 1;
                            //If the search term is found, highlight it by converting it to uppercase
                            if (startIndex != -1) {
                                StringBuilder result = new StringBuilder(changGenre);
                                for (int i = startIndex; i <= endIndex; i++) {
                                    char upperCaseChar = Character.toUpperCase(result.charAt(i));
                                    result.setCharAt(i, upperCaseChar);
                                }
                                changeGenres.add(String.valueOf(result));
                            } else {
                                //If the search term is not found, add the genre as it is
                                changeGenres.add(changGenre);
                            }
                        }
                        //Print the search result for the matching laureate with the highlighted genre
                        System.out.printf("| %-28s | %-51s | %-4s |%n", laureate.getName(), String.join(", ", changeGenres), prize.getYear());
                        System.out.println("------------------------------------------------------------------------------------------");
                        //Break after finding the first matching genre to avoid duplicate entries for the same laureate
                        break;
                    }
                }
            }
        }
        //Reverse the list back to its original order
        Collections.reverse(literaturePrizesList);
    }

    private static void displayMenu() {
        System.out.println("----------------------");
        System.out.println("Literature prize menu");
        System.out.println("----------------------");
        System.out.println("List ...............1");
        System.out.println("Select .............2");
        System.out.println("Search .............3");
        System.out.println("Exit ...............0");
        System.out.println("----------------------");
        System.out.print("Enter choice > ");
    }
}
