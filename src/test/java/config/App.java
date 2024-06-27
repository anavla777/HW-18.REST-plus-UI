package config;

import org.aeonbits.owner.ConfigFactory;

public class App {
    public static final AppConfig config = ConfigFactory.create(AppConfig.class, System.getProperties());
}
