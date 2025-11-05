import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

class Meetup(private val month: Int, private val year: Int) {

    fun day(dayOfWeek: DayOfWeek, schedule: MeetupSchedule): LocalDate {
        val yearMonth = YearMonth.of(year, month)

        return when (schedule) {
            MeetupSchedule.TEENTH -> {
                (13..19)
                  .map { LocalDate.of(year, month, it) }
                  .first { it.dayOfWeek == dayOfWeek }
            }

            MeetupSchedule.FIRST -> nthWeekDay(dayOfWeek, yearMonth, 1)
            MeetupSchedule.SECOND -> nthWeekDay(dayOfWeek, yearMonth, 2)
            MeetupSchedule.THIRD -> nthWeekDay(dayOfWeek, yearMonth, 3)
            MeetupSchedule.FOURTH -> nthWeekDay(dayOfWeek, yearMonth, 4)
            
            MeetupSchedule.LAST -> {
                val lastDay = yearMonth.lengthOfMonth()
                (lastDay downTo lastDay -  6)
                  .map { LocalDate.of(year, month, it) }
                  .first { it.dayOfWeek == dayOfWeek }
            }
        }
    }

    private fun nthWeekDay(dayOfWeek: DayOfWeek, yearMonth: YearMonth, n: Int): LocalDate {
        var date = LocalDate.of(yearMonth.year, yearMonth.month, 1)

        while(date.dayOfWeek != dayOfWeek) {
            date = date.plusDays(1)
        }

        return date.plusWeeks((n - 1).toLong())
    }
}

fun main() {
    val meetup = Meetup(8, 1953)
    val date = meetup.day(DayOfWeek.SATURDAY, MeetupSchedule.TEENTH)
    println(date) // Output: 1953-08-15
}