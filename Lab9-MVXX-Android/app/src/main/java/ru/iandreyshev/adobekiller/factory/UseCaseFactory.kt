package ru.iandreyshev.adobekiller.factory

import ru.iandreyshev.adobekiller.presenter.interfaces.IMainPresenter
import ru.iandreyshev.adobekiller.useCase.MainUseCase
import ru.iandreyshev.adobekiller.useCase.interfaces.IMainUseCase
import ru.iandreyshev.adobekiller.useCase.interfaces.IUseCaseFactory
import ru.iandreyshev.adobekiller.presenter.interfaces.IPresenter
import ru.iandreyshev.adobekiller.ui.shape.ImageGenerator
import ru.iandreyshev.adobekiller.useCase.interfaces.IUseCase
import kotlin.reflect.KClass

object UseCaseFactory : IUseCaseFactory {
    override fun <TUseCase : IUseCase>
            create(useCaseClass: KClass<TUseCase>, presenter: IPresenter): IUseCase {
        return when (useCaseClass) {
            IMainUseCase::class -> {
                MainUseCase(
                        presenter = presenter as IMainPresenter,
                        shapesFactory = IndexedShapesFactory,
                        onRefresh = ImageGenerator::create)
            }
            else -> throw IllegalArgumentException("Unknown use case class ${useCaseClass.qualifiedName}")
        }
    }
}
