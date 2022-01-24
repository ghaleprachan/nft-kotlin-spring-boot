package com.prachang.nftmarketplace.service

import com.prachang.nftmarketplace.model.Message
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

@Service
class ChatService(val db: ChatRepository) {
    fun getChats(): List<Message> = db.getChats()

    fun addChat(message: Message) {
        db.save(message)
    }
}

interface ChatRepository : CrudRepository<Message, String> {
    @Query("""SELECT * FROM messages""")
    fun getChats(): List<Message>
}