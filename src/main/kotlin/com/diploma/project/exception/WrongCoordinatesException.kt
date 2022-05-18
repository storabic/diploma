package com.diploma.project.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class WrongCoordinatesException() : Exception("Enter coordinates in range 0-90 (angle)")
