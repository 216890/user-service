package pl.lodz.p.stanczyk.userservice.adapter.api

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.lodz.p.stanczyk.userservice.domain.User
import pl.lodz.p.stanczyk.userservice.domain.UserId
import pl.lodz.p.stanczyk.userservice.domain.UserService
import java.util.UUID
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Size

@RestController
@Validated
class UserEndpoint(private val userService: UserService) {
    @GetMapping(
        path = ["/users"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findUsersByIds(@RequestParam(required = true) @Size(min = 1) ids: List<UUID>): List<UserResponse> =
        userService.findUsersByIds(
            ids.map(UserId::of)
        ).map(UserResponse::of)

    @ExceptionHandler(ConstraintViolationException::class)
    private fun handleConstraintViolation(exception: ConstraintViolationException): ResponseEntity<Error> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            Error(exception.constraintViolations.joinToString(", "))
        )
}

data class UserResponse(
    val id: UUID,
    val name: String
) {
    companion object {
        fun of(user: User) = UserResponse(
            id = user.id.value,
            name = user.name
        )
    }
}
