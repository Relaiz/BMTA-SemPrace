package com.example.bmta

import java.io.Serializable

data class Game(
    val player1Name: String,
    val player2Name: String,
    val winner: String
): Serializable