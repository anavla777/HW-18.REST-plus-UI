package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface ProjectConfig extends Config {
    @Config.DefaultValue("chrome")
    String browser();

    @DefaultValue("121")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String wdhost();

    @DefaultValue("normal")
    String loadStrategy();
}
