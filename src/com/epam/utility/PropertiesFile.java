package com.epam.utility;

import java.util.Properties;

public class PropertiesFile {
    private String password;
    private String autoReconnect;
    private String characterEncoding;
    private String useUnicode;
    private Properties properties;

    public Properties PropertiesFile(String user, String password, String autoReconnect, String characterEncoding, String useUnicode) {
        this.password = password;
        this.autoReconnect = autoReconnect;
        this.characterEncoding = characterEncoding;
        this.useUnicode = useUnicode;
        properties = new Properties();
        properties.put("user", user);
        properties.put("password", password);
        properties.put("autoReconnect", autoReconnect);
        properties.put("characterEncoding", characterEncoding);
        properties.put("useUnicode", useUnicode);
        return properties;
    }
}
