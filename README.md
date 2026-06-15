# Java Literature Prize App

This project is a console-based **Java application** for exploring Nobel Literature Prize data. It was originally developed as a university coursework project and demonstrates object-oriented programming, file handling, text parsing, collections, searching, filtering, and formatted console output.

The program reads literature prize data from a text file and allows users to list prize winners by year range, select a specific year, and search laureates by writing genre.

---

## Project Timeline

* **Originally completed:** 2024
* **Refactored and published on GitHub:** 2026
* **Context:** Java programming coursework project

This repository has been cleaned and documented for portfolio purposes.

---

## Project Status

This is a completed coursework-style Java console application. It is not a production system, but it demonstrates core Java programming skills, including object-oriented design, file parsing, menu-driven interaction, and data search functionality.

---

## Features

* Loads Nobel Literature Prize data from a text file
* Stores prize records using Java classes
* Lists literature prize winners within a selected year range
* Displays detailed prize information for a selected year
* Searches laureates by writing genre
* Handles years where the prize was not awarded
* Uses formatted console output for readable results
* Demonstrates basic object-oriented modelling with multiple classes

---

## Technologies Used

* Java
* Object-Oriented Programming
* File handling
* Text parsing
* Lists and collections
* Console input/output
* Makefile

---

## Project Structure

```text
.
├── src/
│   └── literatureprizeapp/
│       ├── LiteraturePrizeApp.java
│       ├── LiteraturePrize.java
│       └── Laureate.java
├── literature-prizes.txt
├── Makefile
├── .gitignore
└── README.md
```

---

## Main Classes

### `LiteraturePrizeApp`

Main application class. It loads the literature prize data, displays the menu, handles user input, and provides the main features:

* list winners by year range
* select a specific year
* search by writing genre

### `LiteraturePrize`

Represents a literature prize record for a specific year. It stores the year and the associated laureates.

### `Laureate`

Represents a prize winner and stores information such as:

* name
* birth year
* death year
* nationality
* language
* citation
* writing genres

---

## How to Compile

Use the Makefile:

```bash
make
```

This compiles the Java source files into the `out/` directory.

---

## How to Run

```bash
make run
```

Or run manually after compilation:

```bash
java -cp out literatureprizeapp.LiteraturePrizeApp
```

---

## How to Clean Build Files

```bash
make clean
```

This removes the generated `out/` directory.

---

## Example Menu

When the program starts, the user sees a menu similar to:

```text
----------------------
Literature prize menu
----------------------
List ...............1
Select .............2
Search .............3
Exit ...............0
----------------------
Enter choice >
```

---

## Example Use Cases

### List prize winners by year range

The user can enter a start year and end year to view all literature prize winners within that range.

### Select a specific year

The user can enter a year and view detailed information about the literature laureate or laureates for that year, including language, genre, and citation.

### Search by genre

The user can search for a writing genre, and the application displays matching laureates and the year they received the prize.

---

## What I Learned

Through this coursework project, I practised:

* Designing Java programs using multiple classes
* Reading structured data from a text file
* Parsing text into objects
* Using `ArrayList` and Java collections
* Creating a menu-driven console application
* Searching and filtering data
* Formatting console output
* Separating data models from application logic
* Preparing a Java coursework project for GitHub presentation

---

## Future Improvements

Possible improvements include:

* Improving input validation for non-numeric menu and year entries
* Making genre search case-insensitive
* Adding unit tests
* Refactoring file parsing into a separate service/class
* Improving table formatting for long names and citations
* Adding support for exporting results to a file
* Adding a graphical user interface
* Refactoring the project into clearer `src`, `data`, and `tests` folders

---

## Author

**Amir Lorvand**

