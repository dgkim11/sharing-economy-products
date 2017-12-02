package com.minna.sharingeco.product.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @since 1.0.0
 * @author Dennis Kim
 */
@Component
@PropertySource({
		"classpath:properties/front-web-default.xml",
		"classpath:properties/front-web-${spring.profiles.active}.xml"
})
@Getter
public class ApplicationProperties {
	@Value("${sys.version}")
	private String sysVersion;

	@Value("${app.version}")
	private String appVersion;
}
