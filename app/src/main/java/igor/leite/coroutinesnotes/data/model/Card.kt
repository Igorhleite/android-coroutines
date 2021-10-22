package igor.leite.coroutinesnotes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val name: String,
    val type: String,
    val imageUrl: String,
) : Parcelable