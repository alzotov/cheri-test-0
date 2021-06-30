package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void OnlyOneBookingPerSlot() {
                System.out.println("\nTests only one user per date/room:");
                Booking.book("Alex",1, new Date());
                Booking.book("Hock",1, new Date());
                Booking.table.stream().forEach(System.out::println);
                assertEquals(1, Booking.table.size());
	}

        @Test MultipleBookingsPossible(){
                System.out.println("\nTests different user, different room:");
                Booking.book("Alex",1, new Date());
                Booking.book("Hock",100, new Date());
                Booking.table.stream().forEach(System.out::println);
                assertEquals(2, Booking.table.size());
        }
        
        @Test UserBookings(){        
                System.out.println("\nTests of user bookings:");
                Set<Booking> alexBookings = Booking.getGuestBookings("Alex");
                alexBookings.stream().forEach(System.out::println);
                assertEquals(1, alexBookings.size());
        }