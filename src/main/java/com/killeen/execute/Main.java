/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.killeen.execute;

import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import com.killeen.execute.ExecuteParser;
import com.killeen.execute.ExecuteParser.ExecuteContext;
import com.killeen.execute.ExecuteLexer;
import java.util.List;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author Joan
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("##################################");
        //ANTLRInputStream input = new ANTLRInputStream(System.in);
        String resource = "/executeCommandExamples.txt";
        InputStream fileInput = Main.class.getResourceAsStream(resource);
        if (fileInput == null) {
            throw new IllegalStateException("resource file not found:" + resource);
        };

        try {

            ANTLRInputStream input = new ANTLRInputStream(fileInput);
            ExecuteLexer lexer;
            lexer = new ExecuteLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            /*List<? extends Token> list;
            list = lexer.getAllTokens();

            list.forEach((next) -> {
                System.out.println("Token:" + next + " " + next.getStartIndex() + " " + next.getStopIndex());
            });*/
            ExecuteParser parser = new ExecuteParser(tokens); // pass column number!

            /*ExecuteContext filectx = parser.execute();
            System.out.println("finished parse file");// parse
            System.out.println(filectx.toInfoString(parser));// parse*/

            ParseTree tree = parser.scriptContent(); // parse
            System​.out.println(tree.toStringTree(parser));
            ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
            ExecuteCommandListener execute = new ExecuteCommandListener(parser);
            walker.walk(execute, tree); // initiate walk of tree with listener

        } catch (Exception ex) {
            System.out.println("exception block");
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    static void startParse(InputStream fileInput){
        
    }

}
