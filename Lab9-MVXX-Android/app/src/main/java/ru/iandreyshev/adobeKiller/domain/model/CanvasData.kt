package ru.iandreyshev.adobeKiller.domain.model

import ru.iandreyshev.localstorage.entity.ICanvasEntity

data class CanvasData(
        val id: Long,
        val name: String
) {

    constructor(entity: ICanvasEntity) : this(
            id = entity.id,
            name = entity.name
    )

}
