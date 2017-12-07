build: clean
	mkdir bin
	javac -cp src/ -d bin src/com/garfiec/networkchat/client/Chat_Client.java
	javac -cp src/ -d bin src/com/garfiec/networkchat/server/Chat_Server.java

runc:
	java -cp bin com.garfiec.networkchat.client.Chat_Client

runs:
	java -cp bin com.garfiec.networkchat.server.Chat_Server

all: build run

clean:
	@echo "Cleaning..."
	$(RM) -rf ./bin

.PHONY: clean
