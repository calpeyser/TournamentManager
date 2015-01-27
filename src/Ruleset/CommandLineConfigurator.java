package Ruleset;

import Base.*;

import java.io.*;
import java.lang.reflect.*;

import Model.MockTournament;

/** Configurator which populates configurable from the command line */
public class CommandLineConfigurator extends ManualConfigurator {
	
	protected boolean isConfigured;
	
	public CommandLineConfigurator(Configurable conf) {
		super(conf);
		this.isConfigured = false;
	}

	@Override
	public void configure() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		for (Field field : this.getFields()) {
			try {
				System.out.println(field.getAnnotation(ConfigField.class).name() + ": ");
				String input = reader.readLine();
				field.set(conf, field.getType().cast(input));
			} catch (Exception e) {
				throw new RuntimeException("Command Line read/write to object failed");
			}
		}
		this.isConfigured = true;
	}
	
	@Override
	public boolean isConfigured() {
		return this.isConfigured;
	}
	
	/** Testing on a mock tournament instance */
	public static void main(String[] args) {
		Tournament dummyTournament = new MockTournament();
		ManualConfigurator dummyConf = new CommandLineConfigurator(dummyTournament);
		if (dummyConf.isConfigured()) {
			System.out.println(dummyTournament);
		}
	}
}





