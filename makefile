gram:
	java -jar ./lib/antlr-4.9.2-complete.jar Lexertiger.g4 -no-listener -o ./src/lexer

	java -jar ./lib/antlr-4.9.2-complete.jar Lexertiger.g4 Parsertiger.g4 -no-listener -visitor -o ./src/parser

	javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/Main.java -d ./bin

	java -cp ./lib/antlr-4.9.2-complete.jar:./bin Main ./examples/8reines.exp

	dot -Tsvg ./out/tree.dot -o ./out/tree.svg

