package com.diploma.project.config

import org.springframework.cloud.openfeign.support.PageJacksonModule
import org.springframework.cloud.openfeign.support.SortJacksonModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!test")
class PageableConfig {
    @Bean
    fun pageJacksonModule(): PageJacksonModule {
        return PageJacksonModule()
    }

    @Bean
    fun sortJacksonModule(): SortJacksonModule {
        return SortJacksonModule()
    }
}