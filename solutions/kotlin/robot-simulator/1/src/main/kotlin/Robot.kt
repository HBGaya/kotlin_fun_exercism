class Robot(var gridPosition: GridPosition = GridPosition(0, 0),
    var orientation: Orientation = Orientation.NORTH) {

    fun simulate(instructions: String) {
        for(cmd in instructions) {
            when(cmd) {
                'R' -> orientation = when (orientation) {
                    Orientation.NORTH -> Orientation.EAST
                    Orientation.EAST -> Orientation.SOUTH
                    Orientation.SOUTH -> Orientation.WEST
                    Orientation.WEST -> Orientation.NORTH
                }
                'L' -> orientation = when (orientation) {
                    Orientation.NORTH -> Orientation.WEST
                    Orientation.WEST -> Orientation.SOUTH
                    Orientation.SOUTH -> Orientation.EAST
                    Orientation.EAST -> Orientation.NORTH
                }
                'A' -> advance()
                else -> error("Invalid instruction: $cmd")
            }
        }
    }

    private fun advance() {
        when(orientation) {
            Orientation.SOUTH -> gridPosition = gridPosition.copy(y = gridPosition.y - 1)
            Orientation.NORTH -> gridPosition = gridPosition.copy(y = gridPosition.y + 1)
            Orientation.EAST ->  gridPosition = gridPosition.copy(x = gridPosition.x + 1)
            Orientation.WEST ->  gridPosition = gridPosition.copy(x = gridPosition.x - 1)
        }
    }
}

fun main() {
    val robot = Robot(GridPosition(7, 3), Orientation.NORTH)
    robot.simulate("RAALAL")
    println("Final Position: ${robot.gridPosition}, Facing: ${robot.orientation}")
}
