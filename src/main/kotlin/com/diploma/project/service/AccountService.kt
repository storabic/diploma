package com.diploma.project.service

import com.diploma.project.model.Account

interface AccountService {
    fun getUser(userId: Long) : Account
}
