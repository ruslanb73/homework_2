package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface BaseConfig extends Config {
    /**
     * Метод для возвращения значения параметра baseUrl из config.properties
     *
     * @return базовый URL
     */
    String baseUrl();
}
