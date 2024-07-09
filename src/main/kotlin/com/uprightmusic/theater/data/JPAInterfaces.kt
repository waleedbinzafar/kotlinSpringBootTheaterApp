package com.uprightmusic.theater.data

import com.uprightmusic.theater.domain.Booking
import com.uprightmusic.theater.domain.Performance
import com.uprightmusic.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<Seat, Long>

interface PerformanceRepository: JpaRepository<Performance, Long>

interface BookingRepository: JpaRepository<Booking, Long>