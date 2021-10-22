package igor.leite.coroutinesnotes.data.model.dto

import com.google.gson.annotations.SerializedName

data class CardModel(
    var name: String,
    var type: String,
    @SerializedName("card_images")
    var images: List<CardImage>,
)