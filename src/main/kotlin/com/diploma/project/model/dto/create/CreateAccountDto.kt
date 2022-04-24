package com.diploma.project.model.dto.create

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class CreateAccountDto(
    @field:NotBlank
    @field:Size(max = 12)
    @field:Pattern(regexp = "^.*[0-9]{10}$", message = "Invalid phone")
    val phone: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val password: String?
)