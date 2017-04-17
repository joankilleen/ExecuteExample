/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.killeen.execute;

import java.io.InputStream;
import org.antlr.v4.runtime.TokenStream;

/**
 *
 * @author Joan
 */
public class ExecuteCommandListener extends ExecuteBaseListener {

    private ExecuteParser parser;

    public ExecuteCommandListener(ExecuteParser parser) {
        this.parser = parser;
    }

    @Override
    public void enterScenarioName(ExecuteParser.ScenarioNameContext ctx) {
        System.out.println("Reached enterScenarioName");
        TokenStream tokens = parser.getTokenStream();
        String filename = "";
        if (ctx.STRING() != null) {
            filename = ctx.STRING().getText();
            System.out.println("Scenario filename found: " + filename);
        }
        String resource = "/" + filename + ".txt";
        InputStream fileInput = Main.class.getResourceAsStream(resource);
        if (fileInput == null) {
            throw new IllegalStateException("resource file not found:" + resource);
        };
        Main.startParse(fileInput);

    }

}
