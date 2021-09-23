package pl.lodz.p.stanczyk.userservice.integration.api

import groovy.transform.builder.Builder
import groovy.transform.builder.SimpleStrategy
import pl.lodz.p.stanczyk.userservice.infrastructure.UserEntity

@Builder(builderStrategy = SimpleStrategy, prefix = "with")
class UserEntityBuilder {
    String id = "c4abd28f-61b2-4d5a-9487-60ec63ceaa18"
    String firstName = "firstName"
    String lastName = "lastName"

    static UserEntityBuilder aUser() {
        new UserEntityBuilder()
    }

    UserEntity build() {
        new UserEntity(
                id,
                firstName,
                lastName
        )
    }
}
