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

    fun setData(newCards: List<CardModel>) {
        mCardList.clear()
        mCardList.addAll(newCards)
        notifyDataSetChanged()
    }

    fun insertCard(card: CardModel) {
        mCardList.add(card)
        notifyItemInserted(mCardList.lastIndex)
    }

    fun updateCard(newCard: CardModel, position: Int) {
        mCardList[position] = newCard
        notifyItemChanged(position)
    }

    fun deleteCard(position: Int) {
        mCardList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemId(position: Int): Long {
        return mCardList[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListVH {
        return CardListVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    override fun onBindViewHolder(holder: CardListVH, position: Int) {
        holder.bind(position)
    }

    inner class CardListVH(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val title = itemView.card_title
        private val description = itemView.card_text

        fun bind(position: Int) {
            title.text = mCardList[position].title
            description.text = mCardList[position].question
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mainView.showPopup(view, adapterPosition, mCardList[adapterPosition])
        }
    }
}