package com.diploma.project.persistence

import com.diploma.project.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountDao : JpaRepository<Account, Long> {
}
