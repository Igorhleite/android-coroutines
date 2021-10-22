package igor.leite.coroutinesnotes.utils.extensions

import igor.leite.coroutinesnotes.data.model.Card
import igor.leite.coroutinesnotes.data.model.dto.CardModel

fun CardModel.fromDomainToCard(): Card = Card(
    name = this.name,
    type = this.type,
    imageUrl = this.images.first().imageUrl
)