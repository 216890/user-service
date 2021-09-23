package pl.lodz.p.stanczyk.userservice.integration

import org.springframework.data.repository.CrudRepository
import pl.lodz.p.stanczyk.userservice.infrastructure.UserEntity

interface UserTestRepository extends CrudRepository<UserEntity, String> {
}
