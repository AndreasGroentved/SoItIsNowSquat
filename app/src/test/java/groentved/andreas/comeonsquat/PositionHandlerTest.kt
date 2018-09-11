package groentved.andreas.comeonsquat

import groentved.andreas.comeonsquat.domain.PhonePosition
import groentved.andreas.comeonsquat.domain.PositionHandler
import org.junit.Assert.assertTrue
import org.junit.Test


class PositionHandlerTest {
    @Test
    fun crossed50Percent() {
        var phonePosition = PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD
        var start = PhonePosition.getAxisDefaultStart(phonePosition)
        var parallel = PhonePosition.getAxisDefaultParallel(phonePosition)
        val positionHandler = PositionHandler()
        BackParallelToSideOfQuad(positionHandler, parallel, start)

        phonePosition = PhonePosition.BACK_PERPENDICULAR_TO_SIDE_OF_QUAD
        start = PhonePosition.getAxisDefaultStart(phonePosition)
        parallel = PhonePosition.getAxisDefaultParallel(phonePosition)
        BackPerpendicularToSideOfQuad(positionHandler, parallel, start)

    }

    fun BackPerpendicularToSideOfQuad(positionHandler: PositionHandler, parallel: Float, start: Float) {
        val notCrossed1 = 10.0
        val crossedHalf1 = positionHandler.crossed50Percent(notCrossed1, parallel, start)
        assertTrue(!crossedHalf1)

        val notCrossed2 = 0.0
        val crossedHalf2 = positionHandler.crossed50Percent(notCrossed2, parallel, start)
        assertTrue(!crossedHalf2)

        val crossed1 = -5.0
        val crossedHalf3 = positionHandler.crossed50Percent(crossed1, parallel, start)
        assertTrue(crossedHalf3)

        val crossed2 = -100.0
        val crossedHalf4 = positionHandler.crossed50Percent(crossed2, parallel, start)
        assertTrue(crossedHalf4)
    }


    fun BackParallelToSideOfQuad(positionHandler: PositionHandler, parallel: Float, start: Float) {
        val notCrossed1 = 0.0
        val crossedHalf1 = positionHandler.crossed50Percent(notCrossed1, parallel, start)
        assertTrue(!crossedHalf1)

        val notCrossed2 = 4.0
        val crossedHalf2 = positionHandler.crossed50Percent(notCrossed2, parallel, start)
        assertTrue(!crossedHalf2)

        val crossed1 = 6.0
        val crossedHalf3 = positionHandler.crossed50Percent(crossed1, parallel, start)
        assertTrue(crossedHalf3)

        val crossed2 = 10.0
        val crossedHalf4 = positionHandler.crossed50Percent(crossed2, parallel, start)
        assertTrue(crossedHalf4)


        val negative = -5.0
        val crossedHalf5 = positionHandler.crossed50Percent(negative, parallel, start)
        assertTrue(!crossedHalf5)
    }

    @Test
    fun parallel() {
        var phonePosition = PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD
        var start = PhonePosition.getAxisDefaultStart(phonePosition)
        var parallel = PhonePosition.getAxisDefaultParallel(phonePosition)
        val positionHandler = PositionHandler()
        var buffer = 1f

        val crossedAcceleration1 = 10.0
        var crossed = positionHandler.hasCrossedParallel(crossedAcceleration1, buffer, parallel, start)
        assertTrue(crossed)

        buffer = 1.1f
        val notCrossedAcceleration1 = 10.0
        crossed = positionHandler.hasCrossedParallel(notCrossedAcceleration1, buffer, parallel, start)
        assertTrue(!crossed)


        buffer = 1.0f
        val notCrossedAcceleration2 = -7.0
        crossed = positionHandler.hasCrossedParallel(notCrossedAcceleration2, buffer, parallel, start)
        assertTrue(!crossed)




        phonePosition = PhonePosition.BACK_PERPENDICULAR_TO_SIDE_OF_QUAD
        start = PhonePosition.getAxisDefaultStart(phonePosition)
        parallel = PhonePosition.getAxisDefaultParallel(phonePosition)
        buffer = 1f

        val AnotCrossedAcceleration1 = 10.0
        crossed = positionHandler.hasCrossedParallel(AnotCrossedAcceleration1, buffer, parallel, start)
        assertTrue(!crossed)

        buffer = 1.1f
        val AnotCrossedAcceleration2 = -0.01
        crossed = positionHandler.hasCrossedParallel(AnotCrossedAcceleration2, buffer, parallel, start)
        assertTrue(!crossed)


        buffer = 1.0f
        val AcrossedAcceleration1 = -0.01
        crossed = positionHandler.hasCrossedParallel(AcrossedAcceleration1, buffer, parallel, start)
        assertTrue(crossed)

    }

    @Test
    fun squatPercentage() {
        var phonePosition = PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD
        var start = PhonePosition.getAxisDefaultStart(phonePosition)
        var parallel = PhonePosition.getAxisDefaultParallel(phonePosition)
        val positionHandler = PositionHandler()
        var buffer = 1f
        var acceleration = 9.85
        var checkAgainst = 1f

        percentageAssert(start, parallel, positionHandler, buffer, acceleration, checkAgainst, greaterThan)

        acceleration = 8.0
        percentageAssert(start, parallel, positionHandler, buffer, acceleration, checkAgainst, lessThan)

        acceleration = 9.82
        percentageAssert(start, parallel, positionHandler, buffer, acceleration, checkAgainst, equal)

        checkAgainst = 0.5f
        acceleration = 4.0
        percentageAssert(start, parallel, positionHandler, buffer, acceleration, checkAgainst, lessThan)


        acceleration = 9.82
        buffer = 1.1f
        checkAgainst = 1f
        percentageAssert(start, parallel, positionHandler, buffer, acceleration, checkAgainst, lessThan)


    }

    private val greaterThan: (Float, Double) -> Boolean = { a, b -> a < b }
    private val lessThan: (Float, Double) -> Boolean = { a, b -> a > b }
    private val equal: (Float, Double) -> Boolean = { a, b -> b in (a - 0.001)..(a + 0.001) }

    private fun percentageAssert(start: Float, parallel: Float, positionHandler: PositionHandler, buffer: Float, acceleration: Double, greaterThan: Float, toCheck: (Float, Double) -> Boolean) {
        val percentage = positionHandler.getSquatPercentage(acceleration, buffer, parallel, start)
        assertTrue(toCheck(greaterThan, percentage))
    }

}
