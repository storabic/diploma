package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class NotEnoughPathPointsException(pathName: String) : Exception("Not enough points for path id = $pathName")
