package com.driver.Repositories;


import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class HotelRepository {

    Map<Integer, User> userMap=new HashMap<>();
    Map<String, Hotel> hotelMap=new TreeMap<>();
    Map<String, Booking> bookingMap=new HashMap<>();


    public String addHotel(Hotel hotel) {
        if(hotelMap.containsKey(hotel.getHotelName())) return "FAILURE";

        hotelMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }

    public void addUser(User user) {
        if(userMap.containsKey(user.getaadharCardNo())){
            throw  new RuntimeException("User is already registered");
        }
        userMap.put(user.getaadharCardNo(),user);
    }

    public String getHotelWithMostFacilities() {
        int max=Integer.MIN_VALUE;
        Hotel resHotel=null;
        for (String hotel: hotelMap.keySet()){
            Hotel hotel1=hotelMap.get(hotel);
            if(hotel1.getFacilities().size()>max){
                resHotel=hotel1;
                max=hotel1.getFacilities().size();
            }
        }
        if (max==Integer.MIN_VALUE || max==0)return "";
        return resHotel.getHotelName();
    }

    public int bookRooms(Booking booking) {
       Hotel hotel=hotelMap.get(booking.getHotelName());
        if (hotel.getAvailableRooms()<booking.getNoOfRooms()) return -1;

        int totalAmount=hotel.getPricePerNight()*booking.getNoOfRooms();
        int availableRooms= hotel.getAvailableRooms()-booking.getNoOfRooms();

        hotel.setAvailableRooms(availableRooms);
        hotelMap.put(hotel.getHotelName(),hotel);

        booking.setAmountToBePaid(totalAmount);
        bookingMap.put(booking.getBookingId(),booking);
        return totalAmount;
    }

    public int getBooking(Integer aadharCard) {
        int totalBooking=0;

        for (String booking : bookingMap.keySet()){
            Booking booking1=bookingMap.get(booking);
            if(booking1.getBookingAadharCard()==aadharCard) totalBooking++;
        }

        return totalBooking;
    }

    public Hotel newFacilities(List<Facility> facilities,String hotelName) {
        Hotel hotel=hotelMap.get(hotelName);

        List<Facility> oldFasility=hotel.getFacilities();

        for(Facility facility:facilities){
            if (!oldFasility.contains(facility))oldFasility.add(facility);
        }
        hotel.setFacilities(oldFasility);
        hotelMap.put(hotelName,hotel);
        return hotel;
    }
}
