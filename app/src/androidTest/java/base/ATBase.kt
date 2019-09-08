package base

import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import ru.anikey.mymindcards.activities.MainActivity

open class ATBase {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

}

