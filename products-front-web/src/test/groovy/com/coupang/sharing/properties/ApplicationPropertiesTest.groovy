package com.coupang.sharing.product.properties

import com.minna.sharingeco.product.properties.ApplicationProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
/**
 * @since 1.0.0
 * @author Dennis Kim
 */
@SpringBootTest
@ActiveProfiles("develop")
@TestPropertySource("/test-develop.xml")
class ApplicationPropertiesTest extends Specification {
    @Autowired
    ApplicationProperties appProps
    @Value('${msg.hello}')
    String msgHello

    def "property should be overridden in test environment"()    {
        expect:
            appProps.sysVersion == "test.0.0"
            appProps.appVersion == "2.2.2"
            msgHello == "hello! in product zone"
    }
}
