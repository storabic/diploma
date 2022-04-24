package com.diploma.project.service

import com.diploma.project.model.Account
import com.diploma.project.model.dto.create.CreateAccountDto
import com.diploma.project.model.dto.get.UserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface AccountService {
    fun getUser(userId: Long) : Account
    fun register(accountDto: CreateAccountDto): UserDto
    fun searchAccounts(searchString: String, page: Pageable): Page<UserDto>
}
