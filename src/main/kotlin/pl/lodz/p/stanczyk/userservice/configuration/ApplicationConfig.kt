package pl.lodz.p.stanczyk.userservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.lodz.p.stanczyk.userservice.adapter.UserRepositoryAdapter
import pl.lodz.p.stanczyk.userservice.domain.UserService
import pl.lodz.p.stanczyk.userservice.infrastructure.UserDatabaseRepository

@Configuration
class ApplicationConfig {
    @Bean
    fun userService(userDatabaseRepository: UserDatabaseRepository) = UserService(
        UserRepositoryAdapter(userDatabaseRepository)
    )
}
