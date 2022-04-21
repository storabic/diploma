package com.diploma.project.persistence

import com.diploma.project.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountDao : JpaRepository<Account, Long> {
}
