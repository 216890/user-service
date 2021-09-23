package pl.lodz.p.stanczyk.userservice.infrastructure

import org.springframework.data.annotation.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "USER_ENTITY")
data class UserEntity(
    @Id
    @Column(name = "ID")
    val id: String,
    @Column(name = "FIRST_NAME")
    val firstName: String,
    @Column(name = "LAST_NAME")
    val lastName: String
)
