package com.tiaot33;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Description:
 *
 * @author tiaot33
 * @since 2021-11-19-17:14
 */
@Configuration
@ConfigurationProperties(prefix = "path")
@Getter
@Setter
public class PathProperty {
    private List<String> source;
    private String target;
    private List<String> excludeDir;
    private String scraperFailedDir;
}
