package com.prachang.nftmarketplace

import com.fasterxml.jackson.databind.ObjectMapper
import com.prachang.nftmarketplace.model.NFT
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.GreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class MftMarketplaceApplicationTests(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {
    /**
     * First, you use the get of mockMvc to send a GET request.
     * Then you chain it with andExpect which accepts a function block.
     * Inside the block, you test the status of the response with status and the content type with content.
     * Finally, you validate the JSON result with jsonPath which accepts an argument.
     * As you can see, the $ represents the parsed object from the JSON string. To test
     * the value of the argument of jsonPath, you need to wrap it with value or GreaterThan.
     * */
    @Test
    fun `Assert NFTs has CryptoPunks as the first item`() {
        mockMvc.get("/nfts")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(1) }
                jsonPath("$[0].name") { value("CryptoPunks") }
                jsonPath("$[0].floor_price") { value(100) }
                jsonPath("$.length()") { GreaterThan(1) }
            }
    }

    @Test
    fun `Assert that we can create an NFT`() {
        mockMvc.get("/nfts/6")
            .andExpect {
                status { isNotFound() }
            }
        val newNFT = NFT(0, "Loot", 45.3)
        mockMvc.post("/nfts") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newNFT)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.name") { value(newNFT.name) }
            jsonPath("$.floor_price") { value(newNFT.floor_price) }
            jsonPath("$.id") { value(newNFT.id) }
        }
        mockMvc.get("/nfts/6")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Loot") }
                jsonPath("$.floor_price") { value(45.3) }
                jsonPath("$.id") { value(6) }
            }
    }
}