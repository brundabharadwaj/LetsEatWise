package com.foodcomparison.config.datadog;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatadogConfig {

    @Value("${datadog.api.key}")
    private String apiKey;

    @Value("${datadog.app.key}")
    private String appKey;

    @Value("${datadog.site:datadoghq.eu}")
    private String site;

    public String getApiKey() {
        return apiKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSite() {
        return site;
    }
}
