package ru.anikey.mymindcards.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import ru.anikey.mymindcards.R
import kotlin.random.Random

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.CardListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListVH {
        return CardListVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: CardListVH, position: Int) {
        holder.bind(position)
    }

    inner class CardListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.card_title
        private val description = itemView.card_text

        fun bind(position: Int) {
            title.text = itemView.resources.getString(R.string.main_card_title, position + 1)
            description.text = randomDescription[Random.nextInt(randomDescription.size)]
        }

        // todo: реализовать базу карточек
        private val randomDescription = listOf(
            "Например такое описание",
            "Или например такое описание подлиннее",
            "Ну или очень длинное описание, чтобы проверить размер карточек и всякое тому подобное",
            "Или немного"
        )
    }
}