package tds;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import lexer.Lexertiger;
import parser.Parsertiger;
import parser.Parsertiger.ProgramContext;
import ast.*;

public class Main {

    public static void main(String[] args){

        if (args.length < 1){
            System.out.println("Error : Expected 1 argument filepath, found 0");
            return;
        }

        String testFile = args[0];

        try {

            //chargement du fichier et construction du parser
            CharStream input = CharStreams.fromFileName(testFile);
            Lexertiger lexer = new Lexertiger(input); 
            CommonTokenStream stream = new CommonTokenStream(lexer);
            Parsertiger parser = new Parsertiger(stream);

            ProgramContext program = parser.program();

            // Visiteur de création de l'AST + création de l'AST
            AstCreator creator = new AstCreator();
            Ast ast = program.accept(creator);

            // Visiteur de création de la TDS + affichage de la TDS

            TdsVisitor tdscreator = new TdsVisitor();
            ast.accept(tdscreator);
            tdscreator.afficher();
            while(!tdscreator.exceptions.isEmpty()){
                System.err.println(tdscreator.exceptions.pop().getMessage());
            }
            


        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (RecognitionException e) {
            e.printStackTrace();
        }
        

    }
    
}
