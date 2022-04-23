package com.diploma.project.service.impl

import com.diploma.project.model.Account
import com.diploma.project.model.Comment
import com.diploma.project.model.Path
import com.diploma.project.model.Rate
import com.diploma.project.persistence.RateDao
import com.diploma.project.service.RateService
import org.springframework.stereotype.Service

@Service
class RateServiceImpl(
    val rateDao: RateDao
) : RateService {
    override fun getRate(rateId: Long): Rate? {
        return rateDao.findById(rateId).get()
    }

    override fun getByCommentAndAccount(comment: Comment, account: Account): Rate? {
        return rateDao.getFirstByCommentAndAccount(comment, account)
    }

    override fun create(account: Account, comment: Comment?, path: Path?) {
        val rate = Rate(
            account = account,
            comment = comment
        )
        rateDao.save(rate)
    }

    override fun update(rate: Rate) {
        rateDao.save(rate)
    }

    override fun delete(rate: Rate) {
        rateDao.delete(rate)
    }

    override fun getByPathAndAccount(path: Path, user: Account): Rate? {
        return rateDao.getFirstByPathAndAccount(path, user)
    }
}