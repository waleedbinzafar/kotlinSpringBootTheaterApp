package com.uprightmusic.theater.services

import com.uprightmusic.theater.data.BookingRepository
import com.uprightmusic.theater.data.SeatRepository
import com.uprightmusic.theater.domain.Booking
import com.uprightmusic.theater.domain.Performance
import com.uprightmusic.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookingService() {
    @Autowired
    lateinit var bookingRepository: BookingRepository

    @Autowired
    lateinit var seatRepository: SeatRepository

    fun isSeatFree(seat: Seat, performance: Performance): Boolean {
        val matchedBookings = findBooking(seat, performance)
        return matchedBookings == null
    }

    fun findSeat(seatNum: Int, seatRow: Char): Seat? {
        val seats = seatRepository.findAll()
        val foundSeat = seats.filter { it.seatRow==seatRow && it.num==seatNum }
        return foundSeat.firstOrNull()
    }

    fun reserveSeat(seat: Seat, performance: Performance, customerName: String): Booking {
        val booking = Booking(0,customerName)
        booking.seat=seat
        booking.performance=performance
        bookingRepository.save(booking)
        return booking
    }

    fun findBooking(seat: Seat, performace: Performance): Booking? {
        val bookings = bookingRepository.findAll()
        val foundBooking = bookings.filter { it.seat==seat && it.performance==performace }
        return foundBooking.firstOrNull()
    }
}