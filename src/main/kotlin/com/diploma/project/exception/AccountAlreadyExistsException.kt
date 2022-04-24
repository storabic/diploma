package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AccountAlreadyExistsException(phone: String?, name: String?) : Exception("Account with $phone or $name already exists")
