package com.example.bmta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val player1Name: TextView = view.findViewById(R.id.player1_name)
        val player2Name: TextView = view.findViewById(R.id.player2_name)
        val winner: TextView = view.findViewById(R.id.game_winner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.player1Name.text = game.player1Name
        holder.player2Name.text = game.player2Name
        holder.winner.text = game.winner
    }

    override fun getItemCount() = games.size
}