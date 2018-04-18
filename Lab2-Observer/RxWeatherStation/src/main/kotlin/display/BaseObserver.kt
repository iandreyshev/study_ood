package display

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

interface BaseObserver<T> : Observer<T> {
    override fun onComplete() {
        // skip
    }

    override fun onSubscribe(d: Disposable?) {
        // skip
    }

    override fun onNext(data: T?) {
        // skip
    }

    override fun onError(e: Throwable?) {
        // skip
    }
}