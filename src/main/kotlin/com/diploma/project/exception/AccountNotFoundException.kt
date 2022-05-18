package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException(accountInfo: String) : Exception("Account $accountInfo not found")
