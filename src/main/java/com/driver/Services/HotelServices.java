package com.driver.Services;

import com.driver.Repositories.HotelRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HotelServices {

    @Autowired
    HotelRepository hotelRepository= new HotelRepository();
    public String addHotel(Hotel hotel) {
        if(hotel==null)return "FAILURE";
        if(hotel.getHotelName()==null)return  "FAILURE";

        String res=hotelRepository.addHotel(hotel);
        return res;
    }

    public Integer addUser(User user) {
        hotelRepository.addUser(user);

        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        String res=hotelRepository.getHotelWithMostFacilities();
        return res;
    }

    public int bookRooms(Booking booking) {
        int totalAmount=hotelRepository.bookRooms(booking);
        return totalAmount;
    }

    public int getBookings(Integer aadharCard) {
        int res= hotelRepository.getBooking(aadharCard);

        return res;
    }

    public Hotel updateFacilities(List<Facility> facilities,String hotelName) {
        Hotel hotel=hotelRepository.newFacilities(facilities,hotelName);
        return hotel;
    }
}
