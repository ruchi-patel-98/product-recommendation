package com.ruchi.app;

import com.ruchi.utils.PropertyReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

class App {

    final static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        PropertyReader.initializeProperties();
        System.out.println("Product Recommendation");
        CommandManager commandManager = new CommandManager();
        commandManager.startSystem();
    }
}
