package com.diploma.project.service

import com.diploma.project.model.Account
import com.diploma.project.model.dto.create.CreateAccountDto
import com.diploma.project.model.dto.get.UserDto

interface AccountService {
    fun getUser(userId: Long) : Account
    fun register(accountDto: CreateAccountDto): UserDto
}
