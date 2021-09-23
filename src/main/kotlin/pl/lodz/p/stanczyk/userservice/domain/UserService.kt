package pl.lodz.p.stanczyk.userservice.domain

import pl.lodz.p.stanczyk.userservice.domain.port.UserRepository

class UserService(private val userRepository: UserRepository) {
    fun findUsersByIds(userIds: List<UserId>) = userRepository.findByIds(userIds)
}
