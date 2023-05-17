@file:Suppress("UNUSED_PARAMETER") // <- REMOVE
package galaxyraiders.core.physics

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("unit", "normal", "degree", "magnitude")
data class Vector2D(val dx: Double, val dy: Double) {
  override fun toString(): String {
    return "Vector2D(dx=$dx, dy=$dy)"
  }

  val magnitude: Double
    get() = Math.hypot(dx, dy)

  val radiant: Double
    get() = angle(this)

  val degree: Double
    get() = this.radiant * 180 / Math.PI

  val unit: Vector2D
    get() = this / this.magnitude

  val normal: Vector2D
    get() = Vector2D(dy/this.magnitude, -dx/this.magnitude)

  operator fun times(scalar: Double): Vector2D {
    return Vector2D(dx * scalar, dy * scalar)
  }

  fun angle(v: Vector2D): Double{
    if(v.dy > 0 && v.dx < 0){return Math.atan(v.dy/v.dx) + Math.PI}
    if(v.dy < 0 && v.dx < 0){return Math.atan(v.dy/v.dx) - Math.PI}
    return Math.atan(v.dy/v.dx)
  }

  operator fun div(scalar: Double): Vector2D {
    return Vector2D(this.dx / scalar, this.dy / scalar)
  }

  operator fun times(v: Vector2D): Double {
    return dx*v.dx + dy*v.dy
  }

  operator fun plus(v: Vector2D): Vector2D {
    return Vector2D(dx + v.dx, dy + v.dy)
  }

  operator fun plus(p: Point2D): Point2D {
    return Point2D(p.x + dx, p.y + dy)
  }

  operator fun unaryMinus(): Vector2D {
    return Vector2D(-dx, -dy)
  }

  operator fun minus(v: Vector2D): Vector2D {
    return Vector2D(dx - v.dx, dy - v.dy)
  }

  fun scalarProject(target: Vector2D): Double {
    return (target * this) / target.magnitude
  }

  fun vectorProject(target: Vector2D): Vector2D {
    return this.scalarProject(target) * target.unit
  }
}

operator fun Double.times(v: Vector2D): Vector2D {
  return Vector2D(v.dx * this, v.dy * this)
}
