package com.prachang.nftmarketplace

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
// @Configuration
// @EnableSwagger2
class NftmarketplaceApplication{
    /*@Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.prachang.nftmarketplace"))
            .paths(PathSelectors.any())
            .build()
    }*/
}

fun main(args: Array<String>) {
    runApplication<NftmarketplaceApplication>(*args)
}