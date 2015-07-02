//$Id: HotelBooking.java 5579 2007-06-27 00:06:49Z gavin $
package au.com.chloec.store;

import javax.ejb.Local;

import au.com.chloec.store.domain.Hotel;

@Local
public interface HotelBooking
{
   public void selectHotel(Hotel selectedHotel);
   
   public void bookHotel();
   
   public void setBookingDetails();
   public boolean isBookingValid();
   
   public void confirm();
   
   public void cancel();
   
   public void destroy();
   
}