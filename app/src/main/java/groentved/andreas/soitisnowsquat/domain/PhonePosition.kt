package groentved.andreas.soitisnowsquat.domain

enum class PhonePosition(val phoneVal: Int) {
    BACK_PARALLEL_TO_TOP_OF_QUAD(0),
    BACK_PARALLEL_TO_SIDE_OF_QUAD(1),
    BACK_PERPENDICULAR_TO_SIDE_OF_QUAD(2),
    BACK_PERPENDICULAR_TO_TOP_OF_QUAD(3)
    ;


    companion object {
        private val map = values().associateBy(PhonePosition::phoneVal)
        fun getEnumVal(position: Int) = map[position]!!

        /*  position -> x = 1 , y = 2, z = 3 */
        fun getAxis(position: PhonePosition): Int = when (position) {
            PhonePosition.BACK_PARALLEL_TO_TOP_OF_QUAD -> 1
            PhonePosition.BACK_PARALLEL_TO_SIDE_OF_QUAD -> 0
            PhonePosition.BACK_PERPENDICULAR_TO_SIDE_OF_QUAD -> 0
            PhonePosition.BACK_PERPENDICULAR_TO_TOP_OF_QUAD -> 3
        }

        fun getAxisDefaultParallel(position: PhonePosition): Float = when (position) {
            BACK_PARALLEL_TO_TOP_OF_QUAD -> 0f
            BACK_PARALLEL_TO_SIDE_OF_QUAD -> 9.82f
            BACK_PERPENDICULAR_TO_SIDE_OF_QUAD -> 0f
            BACK_PERPENDICULAR_TO_TOP_OF_QUAD -> 9.82f
        }

        fun getAxisDefaultStart(position: PhonePosition): Float = when (position) {
            BACK_PARALLEL_TO_TOP_OF_QUAD -> 9.82f
            BACK_PARALLEL_TO_SIDE_OF_QUAD -> 0f
            BACK_PERPENDICULAR_TO_SIDE_OF_QUAD -> 9.82f
            BACK_PERPENDICULAR_TO_TOP_OF_QUAD -> 0f
        }
    }
}