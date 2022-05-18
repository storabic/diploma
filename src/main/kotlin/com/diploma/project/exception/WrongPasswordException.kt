package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class WrongPasswordException(val username: String): Exception("Wrong password for user with name $username")
