package pl.lodz.p.stanczyk.userservice.infrastructure

import org.springframework.data.repository.Repository

interface UserDatabaseRepository : Repository<UserEntity, String> {
    fun findByIdIn(ids: Collection<String>): Iterable<UserEntity>
}
