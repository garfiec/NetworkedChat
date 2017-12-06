build: clean
	mkdir bin
	javac -cp src/main/java -d bin src/main/java/com/edt/battleships/Battleships.java

run:
	java -cp bin com.edt.battleships.Battleships

all: build run

clean:
	@echo "Cleaning..."
	$(RM) -rf ./bin

.PHONY: clean
