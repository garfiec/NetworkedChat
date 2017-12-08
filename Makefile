build: clean
	mkdir bin
	javac -cp src/ -d bin src/com/garfiec/networkchat/client/Chat_Client.java
	javac -cp src/ -d bin src/com/garfiec/networkchat/server/Chat_Server.java
	javac -cp src/ -d bin src/com/garfiec/networkchat/server/Server.java
	javac -cp src/ -d bin src/com/garfiec/networkchat/server/ClientGui.java
	cp -rf src/com/garfiec/networkchat/common/etc bin/com/garfiec/networkchat/common/etc

runc:
	java -cp bin com.garfiec.networkchat.client.Chat_Client

runs:
	java -cp bin com.garfiec.networkchat.server.Chat_Server

runS:
	java -cp bin com.garfiec.networkchat.server.Server

runC:
	java -cp bin com.garfiec.networkchat.server.ClientGui

all: build run

clean:
	@echo "Cleaning..."
	$(RM) -rf ./bin

.PHONY: clean
