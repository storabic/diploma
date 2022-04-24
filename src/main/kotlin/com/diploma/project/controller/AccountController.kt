package com.diploma.project.controller

import com.diploma.project.model.dto.create.CreateAccountDto
import com.diploma.project.model.dto.get.AccountDto
import com.diploma.project.model.dto.get.UserDto
import com.diploma.project.service.AccountService
import com.diploma.project.util.toDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("account")
class AccountController(
    val accountService: AccountService
) {
    @GetMapping("info")
    fun getAccountInfo(@RequestParam userId: Long): UserDto {
        return accountService.getUser(userId).toDto()
    }

    @PostMapping("register")
    fun register(@RequestParam accountDto: CreateAccountDto): UserDto {
        return accountService.register(accountDto)
    }
}