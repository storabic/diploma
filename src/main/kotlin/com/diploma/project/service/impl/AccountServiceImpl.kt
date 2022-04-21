package com.diploma.project.service.impl

import com.diploma.project.exception.AccountNotFoundException
import com.diploma.project.model.Account
import com.diploma.project.persistence.AccountDao
import com.diploma.project.service.AccountService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class AccountServiceImpl(
    val userDao: AccountDao
) : AccountService {
    override fun getUser(userId: Long): Account {
        return userDao.findById(userId).orElseThrow { AccountNotFoundException(userId) }
    }
}