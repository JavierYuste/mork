package es.urjc.etsii.grafo.autoconfig.irace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Algorithm configuration via pairs of key values
 */
public class AlgorithmConfiguration {
    private Map<String, String> params;

    /**
     * Initialize an algorithm configuration from a map
     * @param params algorithm parameters as pairs of key-value
     */
    public AlgorithmConfiguration(Map<String, String> params) {
        this.params = params;
    }

    /**
     * Build an algorithm configuration from a string value, such those returned from Irace.
     * Example: "constructive=random balanced=true initialmaxdiffratio=0.8193 cooldownexpratio=0.9438 cyclelength=9"
     * @param args String representation of the algorithm parameters, see example in method description
     */
    public AlgorithmConfiguration(String[] args) {
        Map<String, String> config = new HashMap<>();
        for (String arg : args) {
            String[] keyValue = arg.split("=");
            if(keyValue.length == 1){
                throw new IllegalArgumentException("Invalid algorithm parameter, length != 2: " + keyValue);
            }
            if (config.containsKey(keyValue[0])) {
                throw new IllegalArgumentException("Duplicated key: " + keyValue[0]);
            }
            String value = keyValue[1];
            if(value.startsWith("'") && value.endsWith("'")){
                // Quotes probably used so terminal does not improperly split / modify value, remove as they should not be necessary
                value = value.substring(1, value.length()-1);
            }
            config.put(keyValue[0], value);
        }
        this.params = config;
    }

    /**
     * Get the value of a config property
     *
     * @param property config key
     * @return Optional with value if key was in config map, empty Optional if key was not in config map.
     */
    public Optional<String> getValue(String property) {
        return Optional.ofNullable(params.get(property));
    }

    /**
     * Get the value of a config property
     *
     * @param key          config key
     * @param defaultValue value to return if key is not in config map
     * @return Config value if key present, default value otherwise
     */
    public String getValue(String key, String defaultValue) {
        return params.getOrDefault(key, defaultValue);
    }

    /**
     * Get the value of a config property and parse as integer value
     *
     * @param key config key
     * @return Optional with value if key was in config map, empty Optional if key was not in config map.
     */
    public Optional<Integer> getValueAsInt(String key) {
        return getValue(key).map(Integer::parseInt);
    }

    /**
     * Get the value of a config property and parse as integer value
     *
     * @param key          config key
     * @param defaultValue value to return if key is not in config map
     * @return Config value if key present, default value otherwise
     */
    public int getValueAsInt(String key, int defaultValue) {
        return getValueAsInt(key).orElse(defaultValue);
    }

    /**
     * Get the value of a config property and parse as double value
     *
     * @param key config key
     * @return Optional with value if key was in config map, empty Optional if key was not in config map.
     */
    public Optional<Double> getValueAsDouble(String key) {
        return getValue(key).map(Double::parseDouble);
    }

    /**
     * Get the value of a config property and parse as a double value
     *
     * @param key          config key
     * @param defaultValue value to return if key is not in config map
     * @return Config value as double if key present, default value otherwise
     */
    public double getValueAsDouble(String key, double defaultValue) {
        return getValueAsDouble(key).orElse(defaultValue);
    }

    /**
     * Get all config properties related to the irace algorithm
     *
     * @return all key/values as a map.
     */
    public Map<String, String> getConfig() {
        return Collections.unmodifiableMap(this.params);
    }
}
