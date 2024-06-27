package config;

import org.aeonbits.owner.ConfigFactory;

public class Project {
    public static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
}
