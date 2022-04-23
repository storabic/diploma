package com.diploma.project.controller

import com.diploma.project.model.dto.get.AccountDto
import com.diploma.project.model.dto.get.UserDto
import com.diploma.project.service.AccountService
import com.diploma.project.util.toDto
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    val accountService: AccountService
) {
    @GetMapping("info")
    fun getAccountInfo(@RequestParam userId: Long): UserDto {
        return accountService.getUser(userId).toDto()
    }

    //TODO
//    @GetMapping("register")
//    fun register(@RequestParam userId: Long): AccountDto {
//        return accountService.getUser(userId).toDto()
//    }
}