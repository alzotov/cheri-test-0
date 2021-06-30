package com.example.demo;

import java.util.*;
import java.util.stream.*;
import java.time.*;

class Booking
{
    String guestName;
    int roomNo; 
    Date date;
 
 //static int[] rooms = {1,2,3};
 
    static Set<Booking> table = new HashSet<Booking>();
    
    static public boolean book(String guestName, int roomNo, Date date)
    {
        Booking newBooking = new Booking(guestName, roomNo, date);
        if (table.contains(newBooking)) return false;
        else table.add(newBooking);
        return true;
    }
    
    static public Set<Booking> getGuestBookings(String guestName)
    {
        return table.stream().filter(e -> e.guestName.equals(guestName)).collect(Collectors.toSet());
    }
    
    private Booking(String guestName, int roomNo, Date date)
    {
        if(guestName == null || roomNo <= 0 || date == null) throw new IllegalArgumentException();
        this.guestName = guestName;
        this.roomNo = roomNo;
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public int hashCode(){
        return roomNo + date.hashCode()*3;
    }
    
    public String toString(){
        return String.format("guest '%s' booked room #%d @%tF",guestName,roomNo,date);
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        Booking booking = (Booking) obj;
        if(roomNo==booking.roomNo && date.equals(booking.date))
            return true;
        else
            return false;
    }
 
}   