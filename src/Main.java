import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import lexer.Lexertiger;
import parser.Parsertiger;
import parser.Parsertiger.ProgramContext;
import ast.*;
import graphViz.GraphVizVisitor;

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

            // code d'affichage de l'arbre syntaxique
            JFrame frame = new JFrame("Arbre Lexical CHAOS");
            JPanel panel = new JPanel();
            TreeViewer viewer = new TreeViewer(Arrays.asList(
                    parser.getRuleNames()),program);
            viewer.setScale(0.50); // Scale a little
            panel.add(viewer);
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

            

            // Visiteur de création de l'AST + création de l'AST
            AstCreator creator = new AstCreator();
            Ast ast = program.accept(creator);

            // Visiteur de représentation graphique + appel
            GraphVizVisitor graphViz = new GraphVizVisitor();
            ast.accept(graphViz);
        
            graphViz.dumpGraph("./out/tree.dot");


            TdsVisitor tdscreator = new TdsVisitor();
            ast.accept(tdscreator);


        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (RecognitionException e) {
            e.printStackTrace();
        }
        

    }
    
}
