package com.prachang.nftmarketplace.controller

import com.prachang.nftmarketplace.model.Message
import com.prachang.nftmarketplace.service.ChatService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chats")
class ChatController(val service: ChatService) {
    @GetMapping
    fun getChats() = service.getChats()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addChat(@RequestBody message: Message) {
        service.addChat(message = message)
    }
}