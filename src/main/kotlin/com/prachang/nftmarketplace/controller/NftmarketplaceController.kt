package com.prachang.nftmarketplace.controller

import com.prachang.nftmarketplace.exception.NFTNotFoundException
import com.prachang.nftmarketplace.model.NFT
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * 1. First, you annotate the class with the @RestController annotation. Spring Boot will scan it and recognize it as a controller class. Auto-configuration in Spring Boot means no need to write an XML file manually to register your controller.
 * 2. To map a GET request to a method, you annotate the method with @GetMapping passing the "/homepage" path as parameter. In this example, the method returns a string.
 * */
@RestController
@RequestMapping("/nfts")
class NftmarketplaceController {
    @Value("\${company_name}")
    private lateinit var name: String

    @GetMapping("/homepage")
    fun getHomePage() = "$name: NFTs Marketplace"

    @GetMapping("")
    fun getNfts() = NFTs

    @GetMapping("/{id}")
    fun getHomePage(@PathVariable id: Int): NFT? {
        return NFTs.firstOrNull { it.id == id } ?: throw NFTNotFoundException()
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun postNft(@RequestBody nft: NFT): NFT {
        val maxId = NFTs.map { it.id }.maxOrNull() ?: 0
        val nextId = maxId + 1
        val newNft = nft.copy(id = nextId)
        NFTs.add(newNft)
        return newNft
    }
}

private var NFTs = mutableListOf(
    NFT(1, "CryptoPunks", 100.0),
    NFT(2, "Sneaky Vampire Syndicate", 36.9),
    NFT(3, "The Sevens (Official)", 0.6),
    NFT(4, "Art Blocks Curated", 1.1),
    NFT(5, "Pudgy Penguins", 2.5),
)
