package com.diploma.project.persistence

import com.diploma.project.model.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountDao : JpaRepository<Account, Long> {
    fun findByPhoneOrName(phone: String?, name: String?) : Account?
    fun findByNameLike(name: String, pageable: Pageable): Page<Account>
}
