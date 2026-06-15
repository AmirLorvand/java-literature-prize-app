JAVAC = javac
JAVA = java
SRC_DIR = src
OUT_DIR = out
MAIN_CLASS = literatureprizeapp.LiteraturePrizeApp

SOURCES = $(SRC_DIR)/literatureprizeapp/LiteraturePrizeApp.java 
$(SRC_DIR)/literatureprizeapp/LiteraturePrize.java 
$(SRC_DIR)/literatureprizeapp/Laureate.java

all:
mkdir -p $(OUT_DIR)
$(JAVAC) -d $(OUT_DIR) $(SOURCES)

run: all
$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS)

clean:
rm -rf $(OUT_DIR)
