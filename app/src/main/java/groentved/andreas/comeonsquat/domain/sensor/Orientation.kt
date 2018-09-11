package groentved.andreas.comeonsquat.domain.sensor

import io.reactivex.Observable

/**
 * Created by Andreas Grøntved on 22-02-2017.
 */
interface Orientation {
    fun start(delayInMilliseconds: Int): Observable<List<FloatArray>>

    fun stop()
}
