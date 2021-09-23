package pl.lodz.p.stanczyk.userservice.integration.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pl.lodz.p.stanczyk.userservice.infrastructure.UserEntity
import pl.lodz.p.stanczyk.userservice.integration.IntegrationSpec

import static pl.lodz.p.stanczyk.userservice.integration.api.UserEntityBuilder.aUser

class GetUsersEndpointSpec extends IntegrationSpec {
    static final def USER_ID_1 = "32b6abf2-ff57-451b-8561-a15a02258c2f"
    static final def USER_ID_2 = "569d67ec-5565-4a71-a7ec-abfbcd82dfac"
    static final def USER_ID_3 = "c4abd28f-61b2-4d5a-9487-60ec63ceaa18"

    def "should return a list of users with selected ids"() {
        given:
        thereAreStoredUsers(
                aUser().withId(USER_ID_1).build(),
                aUser().withId(USER_ID_2).build(),
                aUser().withId(USER_ID_3).build()
        )

        when:
        def response = getUsersRequestIsSent(USER_ID_1, USER_ID_3)

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 2
        response.body[0].id == USER_ID_1
        response.body[1].id == USER_ID_3
    }

    def "should return merged user name"() {
        given:
        thereAreStoredUsers(
                aUser().withId(USER_ID_1).withFirstName("firstName").withLastName("lastName").build()
        )

        when:
        def response = getUsersRequestIsSent(USER_ID_1)

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 1
        with(response.body[0]) {
            name == "firstName lastName"
        }
    }

    def "should ignore queried user id when such user doesn't exist"() {
        given:
        thereAreStoredUsers(
                aUser().withId(USER_ID_3).build()
        )

        when:
        def response = getUsersRequestIsSent(USER_ID_1, USER_ID_3)

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 1
        response.body[0].id == USER_ID_3
    }

    def "should return empty list when no user exists"() {
        given:
        thereAreStoredUsers(
                aUser().withId(USER_ID_2).build()
        )

        when:
        def response = getUsersRequestIsSent(USER_ID_1, USER_ID_3)

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 0
    }

    def "should return 400 when no ids provided"() {
        when:
        def response = emptyGetUsersRequestIsSent()

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
    }

    ResponseEntity<List> getUsersRequestIsSent(String ...userIds) {
        restTemplate.getForEntity(url("/users?ids=${userIds.join(",")}"), List)
    }

    ResponseEntity<Map> emptyGetUsersRequestIsSent() {
        restTemplate.getForEntity(url("/users?ids="), Map)
    }

    void thereAreStoredUsers(UserEntity ...users) {
        userRepository.saveAll(users.toList())
    }
}
