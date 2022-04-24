package com.diploma.project.service.impl

import com.diploma.project.exception.AccountAlreadyExistsException
import com.diploma.project.exception.AccountNotFoundException
import com.diploma.project.model.Account
import com.diploma.project.model.dto.create.CreateAccountDto
import com.diploma.project.model.dto.get.UserDto
import com.diploma.project.persistence.AccountDao
import com.diploma.project.service.AccountService
import com.diploma.project.util.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

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

    override fun searchAccounts(searchString: String, page: Pageable): Page<UserDto> {
        return userDao.findByNameLike(searchString, page).map { it.toDto() }
    }
}