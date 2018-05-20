package ru.iandreyshev.adobeKiller.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
class DbShape(
        @Id var id: Long,
        @Index var canvasId: Long
)
