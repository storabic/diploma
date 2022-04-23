package com.diploma.project.persistence

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.Rate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RateDao: JpaRepository<Rate, Long> {
    fun getFirstByCommentAndAccount(comment: Comment, account: Account): Rate?

    fun getFirstByPathAndAccount(path: Path, account: Account): Rate?
}