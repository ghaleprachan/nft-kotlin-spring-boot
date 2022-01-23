package com.prachang.nftmarketplace.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

/**
 * 1. When you annotate the class with @ControllerAdvice, Spring Boot scans and registers this class as ControllerAdvice for your controller.
 * 2. You annotate the method with @ExceptionHandler, which accepts the exception class you created, letting Spring Boot know that this method can handle that exception.
 * 3. You return a ResponseEntity. The first argument of ResponseEntity is the result you send to the client which can be a simple text string or a JSON string. The second argument is the status type.
 */
@ControllerAdvice
class NFTErrorHandler {
    @ExceptionHandler(NFTNotFoundException::class)
    fun handleNotFoundException(
        servletRequest: HttpServletRequest,
        exception: Exception
    ): ResponseEntity<String> {
        return ResponseEntity("NFT not found", HttpStatus.NOT_FOUND)
    }
}