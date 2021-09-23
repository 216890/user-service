package pl.lodz.p.stanczyk.userservice.adapter

import pl.lodz.p.stanczyk.userservice.domain.User
import pl.lodz.p.stanczyk.userservice.domain.UserId
import pl.lodz.p.stanczyk.userservice.domain.port.UserRepository
import pl.lodz.p.stanczyk.userservice.infrastructure.UserDatabaseRepository
import pl.lodz.p.stanczyk.userservice.infrastructure.UserEntity
import java.util.UUID

class UserRepositoryAdapter(private val userRepository: UserDatabaseRepository) : UserRepository {
    override fun findByIds(userIds: List<UserId>): List<User> =
        userRepository.findByIdIn(userIds.toListOfStrings()).map { it.toDomain() }

    private fun List<UserId>.toListOfStrings() = this.map(UserId::value).map(UUID::toString)
    private fun UserEntity.toDomain() = User(
        id = UserId.of(UUID.fromString(this.id)),
        name = "$firstName $lastName"
    )
}
