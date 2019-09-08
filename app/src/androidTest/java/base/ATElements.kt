package base

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers

enum class Data(val value: String) {
    HEAD("Head card"),
    QUEST("Quest card"),
    ANSWER("Answer card")
}

object ATElements {

    /** Локаторы родителя */
    fun mParent(id: Int? = null, text: String? = null, matcher: Matcher<View>? = null): Matcher<View> {
        val matchers = mutableListOf<Matcher<View>>()
        id?.let { matchers.add(withId(id)) }
        text?.let { matchers.add(withText(text)) }
        matcher?.let { matchers.add(matcher) }
        return withParent(Matchers.allOf(matchers))
    }

    /** Локаторы предка */
    fun mAncestor(id: Int? = null, text: String? = null, matcher: Matcher<View>? = null): Matcher<View> {
        val matchers = mutableListOf<Matcher<View>>()
        id?.let { matchers.add(withId(id)) }
        text?.let { matchers.add(withText(text)) }
        matcher?.let { matchers.add(matcher) }
        return isDescendantOfA(allOf(matchers))
    }

}
