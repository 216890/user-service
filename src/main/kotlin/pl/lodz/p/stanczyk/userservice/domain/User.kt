package pl.lodz.p.stanczyk.userservice.domain

import java.util.UUID

data class User(
    val id: UserId,
    val name: String
)

data class UserId private constructor(val value: UUID) {
    companion object {
        fun of(id: UUID) = UserId(id)
    }
}
