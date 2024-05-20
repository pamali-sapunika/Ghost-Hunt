import android.content.Context
import android.content.SharedPreferences

object HighScoreManager {
    private const val HIGH_SCORE_KEY = "high_score"
    private lateinit var sharedPreferences: SharedPreferences
    private var isInitialized = false

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("HighScorePref", Context.MODE_PRIVATE)
        isInitialized = true
    }

    private fun checkInitialized() {
        if (!isInitialized) {
            throw IllegalStateException("HighScoreManager must be initialized before use")
        }
    }

    fun updateHighScore(score: Int) {
        checkInitialized()
        val editor = sharedPreferences.edit()
        editor.putInt(HIGH_SCORE_KEY, score)
        editor.apply()
    }

    fun getHighScore(): Int {
        checkInitialized()
        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
    }
}
