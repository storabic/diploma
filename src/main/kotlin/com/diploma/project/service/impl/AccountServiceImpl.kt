package com.diploma.project.service.impl

import com.diploma.project.exception.AccountAlreadyExistsException
import com.diploma.project.exception.AccountNotFoundException
import com.diploma.project.model.Account
import com.diploma.project.model.dto.create.CreateAccountDto
import com.diploma.project.model.dto.get.AccountDto
import com.diploma.project.model.dto.get.UserDto
import com.diploma.project.persistence.AccountDao
import com.diploma.project.service.AccountService
import com.diploma.project.util.toDto
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class AccountServiceImpl(
    val userDao: AccountDao
) : AccountService {
    override fun getUser(userId: Long): Account {
        return userDao.findById(userId).orElseThrow { AccountNotFoundException(userId) }
    }

    override fun register(accountDto: CreateAccountDto): UserDto {
        if (userDao.findByPhoneOrName(accountDto.phone, accountDto.name) != null) {
            throw AccountAlreadyExistsException(accountDto.phone, accountDto.name)
        }
        val newUser = Account(
            phone = accountDto.phone,
            password = accountDto.password,
            name = accountDto.name
        )
        return userDao.save(newUser).toDto()
    }
}