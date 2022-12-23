# Projet Compil - PCL1
# Attendus : 

## Objectifs : 

- Créer un compilateur du langage CHAOS

## Les différentes étapes du projet 

- Construire la grammaire du language (si possible LL1)
    Pour que ANTLR puisse utiliser un lexer et un parser.

- Construire l'AST du programme.

- Construire la TDS du programme.

- Crée des contrôles sémantiques sur différentes parties du programme.

# Utilisation de notre compilateur 

## Commandes et leur affichage : 

Des messages sont présent pour aider à l'éxecution du compilateur.

L'utilisation des différentes commandes est décrite ici:


### Compilation grammaire : 

La commande:

````shell
$ make gram
````

permet de compiler la grammaire de CHAOS. Le résultat de la compilation du lexer et du parser se trouve respectivement dans ./src/lexer et ./src/parser.

### Arbre lexical :

La commande:

````shell
$ make arbrelex src="exemple.exp"
````

permet de compiler le programme affichant l'Arbre lexical et de l'éxécuter sur la source donnée.
Il affiche ensuite l'arbre lexical dans une fenêtre qu'il faut fermé pour terminer l'éxécution.

### Compilation AST :

La commande:

````shell
$ make ast
````

permet de compiler le programme contruisant l'AST et de l'éxécuter sur la source donnée.

### Affichage AST :

La commande:

````shell
$ make showast
````

permet d'afficher une représentation graphique de l'AST dans une fenêtre qu'il faut fermer pour terminer l'éxecution.
Elle est aussi trouvable dans ./out/tree.svg pour une meileure qualité.

### Affichage AST :

La commande:

````shell
$ make tds src="exemple.exp"
````

permet de compiler le programme qui permet de créer et d'afficher dans le terminal la table des symboles de la source donée.

### Compilation complète:

La commande suivante permet de faire les 5 commandes précédentes succésivement:

````shell
$ make all src="exemple.exp"

ou 

$ make src="exemple.exp"
````






