
SRC		=	$(src)

all: gram arbrelex ast showast tds

	@echo "${BOLD}${SGREEN}Execution terminée${S} ✌️  🎉"

gram:

	@echo "${BOLD}${SCYAN}Compilation: ${S}${SBLUE}Grammaire${S} 📖"

	@java -jar ./lib/antlr-4.9.2-complete.jar Lexertiger.g4 -no-listener -o ./src/lexer

	@java -jar ./lib/antlr-4.9.2-complete.jar Lexertiger.g4 Parsertiger.g4 -no-listener -visitor -o ./src/parser


arbrelex:

	@echo "${BOLD}${SPURPLE}Affichage: ${S}${SBLUE}Arbre lexical de ${SRC}${S} 🖼️"

	@javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/arbrelexical/Main.java -d ./bin

	@echo "${BOLD}${SYELLOW}🚩 Fermé la fenêtre pour afficher la suite${S}"

	@java -cp ./lib/antlr-4.9.2-complete.jar:./bin arbrelexical/Main ${SRC}


showast: outast

	@echo "${BOLD}${SPURPLE}Affichage: ${S}${SBLUE}Arbre synthaxique${S} 🖼️"

	@javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/ast/display.java -d ./bin

	@echo "${BOLD}${SYELLOW}🚩 Fermé la fenêtre pour afficher la suite${S}"

	@java -cp ./lib/antlr-4.9.2-complete.jar:./bin ast/display 

	

ast:

	@echo "${BOLD}${SCYAN}Création: ${S}${SBLUE}Arbre synthaxique  de ${SRC}${S} 📖"

	@javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/ast/Main.java -d ./bin

	@java -cp ./lib/antlr-4.9.2-complete.jar:./bin ast/Main ${SRC}

outast:

	@echo "${BOLD}${SCYAN}Extraction: ${S}${SBLUE}Arbre synthaxique${S} ⛏️"

	@dot -Tpng ./out/tree.dot -o ./out/tree.png

	@dot -Tsvg ./out/tree.dot -o ./out/tree.svg

tds:

	@echo "${BOLD}${SPURPLE}Affichage: ${S}${SBLUE}Table des symboles de ${SRC}${S} 🖼️"

	@echo " "

	@echo " "

	@javac -cp ./lib/antlr-4.9.2-complete.jar:./src ./src/tds/Main.java -d ./bin

	@java -cp ./lib/antlr-4.9.2-complete.jar:./bin tds/Main ${SRC}



S 		=		\033[0m
BOLD 	= 		\033[1m

# Colors

SGREEN	=		\033[92m
SYELLOW	=		\033[93m
SBLUE	=		\033[94m
SPURPLE	=		\033[95m
SCYAN	=		\033[96m
