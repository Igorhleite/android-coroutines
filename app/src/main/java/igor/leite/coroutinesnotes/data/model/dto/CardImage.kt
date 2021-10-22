package igor.leite.coroutinesnotes.data.model.dto

import com.google.gson.annotations.SerializedName

data class CardImage(
    @SerializedName("image_url")
    var imageUrl: String,
    @SerializedName("image_url_small")
    var smallImageUrl: String,
)