package ru.anikey.mymindcards.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var mEmptyView: View? = null

    private fun initEmptyView() {
        mEmptyView?.let { emptyView ->
            emptyView.visibility =
                if (adapter!!.itemCount == 0) View.VISIBLE else View.GONE

            this@CustomRecyclerView.visibility =
                if (adapter!!.itemCount == 0) View.GONE else View.VISIBLE
        }
    }

    private val observer = object : AdapterDataObserver() {

        override fun onChanged() {
            super.onChanged()
            initEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            initEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            initEmptyView()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)

        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
    }

    fun setEmptyView(view: View) {
        this.mEmptyView = view
        initEmptyView()
    }

}
