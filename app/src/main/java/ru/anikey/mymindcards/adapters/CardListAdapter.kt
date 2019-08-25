package ru.anikey.mymindcards.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.views.MainView
import java.util.*

class CardListAdapter(private val mainView: MainView) :
    RecyclerView.Adapter<CardListAdapter.CardListVH>() {

    private val mCardList: MutableList<CardModel> = LinkedList()
    private val mFilteredList: MutableList<CardModel> = LinkedList()

    fun filter(query: String) {
        mFilteredList.clear()
        mCardList.forEach { card ->
            if (card.title.contains(query, ignoreCase = true)
                || card.question.contains(query, ignoreCase = true)
            ) {
                mFilteredList.add(card)
            }
        }
        notifyDataSetChanged()
    }

    fun setData(newCards: List<CardModel>) {
        mCardList.clear()
        mCardList.addAll(newCards)
        filter(query = "")
    }

    fun insertCard(card: CardModel) {
        mFilteredList.add(card)
        notifyItemInserted(mCardList.lastIndex)
    }

    fun updateCard(newCard: CardModel, position: Int) {
        mFilteredList[position] = newCard
        notifyItemChanged(position)
    }

    fun deleteCard(position: Int) {
        mFilteredList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemId(position: Int): Long {
        return mFilteredList[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListVH {
        return CardListVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mFilteredList.count()
    }

    override fun onBindViewHolder(holder: CardListVH, position: Int) {
        holder.bind(position)
    }

    inner class CardListVH(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val title = itemView.card_title
        private val description = itemView.card_text

        fun bind(position: Int) {
            title.text = mFilteredList[position].title
            description.text = mFilteredList[position].question
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mainView.showPopup(view, adapterPosition, mFilteredList[adapterPosition])
        }
    }
}