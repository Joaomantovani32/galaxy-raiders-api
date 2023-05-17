@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

data class Point2D(val x: Double, val y: Double) {
  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + x, p.y + y)
  }

  operator fun plus(v: Vector2D): Point2D {
    return Point2D(x + v.dx, y + v.dy)
  }

  override fun toString(): String {
    return "Point2D(x=$x, y=$y)"
  }

  fun toVector(): Vector2D {
    return Vector2D(x, y)
  }

  fun impactVector(p: Point2D): Vector2D {
    return Vector2D((p.x - x), (p.y - y))
  }

  fun impactDirection(p: Point2D): Vector2D {
    return Vector2D(x - p.x, y - p.y)
  }

  fun contactVector(p: Point2D): Vector2D {
    val vet: Vector2D = impactVector(p)
    return vet.normal
  }

  fun contactDirection(p: Point2D): Vector2D {
    val vet: Vector2D = this.impactDirection(p)
    return vet.normal
  }

  fun distance(p: Point2D): Double {
    return Math.hypot(p.x - x, p.y - y)
  }
}
