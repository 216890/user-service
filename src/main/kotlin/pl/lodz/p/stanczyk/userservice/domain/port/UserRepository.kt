package pl.lodz.p.stanczyk.userservice.domain.port

import pl.lodz.p.stanczyk.userservice.domain.User
import pl.lodz.p.stanczyk.userservice.domain.UserId

interface UserRepository {
    fun findByIds(userIds: List<UserId>): List<User>
}
