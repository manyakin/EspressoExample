package ru.anikey.mymindcards.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.models.CardModel

class CardListAdapter(private val cardList: List<CardModel>) : RecyclerView.Adapter<CardListAdapter.CardListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListVH {
        return CardListVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardListVH, position: Int) {
        holder.bind(position)
    }

    inner class CardListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.card_title
        private val description = itemView.card_text

        fun bind(position: Int) {
            title.text = cardList[position].title
            description.text = cardList[position].question
        }
    }
}