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
            System.out.println("created imput stream");
            ExecuteLexer lexer;
            lexer = new ExecuteLexer(input);
            System.out.println("created lexer");
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            System.out.println("created tokens");
            ExecuteParser parser = new ExecuteParser(tokens); // pass column number!
            System.out.println("created parser");
            parser.setBuildParseTree(false); // don't waste time bulding a tree
            System.out.println("set false parser");
            ExecuteContext filectx = parser.execute();
            System.out.println("finished parse file");// parse
            System.out.println(filectx.toInfoString(parser));// parse

        } catch (Exception ex) {
            System.out.println("exception block");
            System.out.println(ex.getClass());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
