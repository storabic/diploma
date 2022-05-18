package com.diploma.project.service

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.Rate

interface RateService {
    fun getRate(rateId: Long): Rate?
    fun getByCommentAndAccount(comment: Comment, account: Account): Rate?
    fun create(account: Account, comment: Comment?, path: Path?, rate: Double?)
    fun update(rate: Rate)
    fun delete(rate: Rate)
    fun getByPathAndAccount(path: Path, user: Account): Rate?
}