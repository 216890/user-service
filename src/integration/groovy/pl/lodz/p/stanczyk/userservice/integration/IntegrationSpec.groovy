package pl.lodz.p.stanczyk.userservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [IntegrationTestConfiguration.class])
@ActiveProfiles("integration")
class IntegrationSpec extends Specification {
    @LocalServerPort
    def SERVER_PORT
    def SERVER_URL = "localhost"
    @Autowired
    UserTestRepository userRepository
    @Shared
    TestRestTemplate restTemplate = new TestRestTemplate()

    def setup() {
    }

    def cleanup() {
        userRepository.deleteAll()
    }

    String url(String path) {
        "http://$SERVER_URL:$SERVER_PORT$path"
    }
}