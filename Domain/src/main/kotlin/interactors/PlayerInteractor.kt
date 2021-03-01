package interactors

import io.reactivex.rxjava3.core.Completable

interface PlayerInteractor {

    fun stop():Completable

    fun play():Completable

    fun pause():Completable

    fun manageVolume():Completable

}