//$Id: BookingList.java 5579 2007-06-27 00:06:49Z gavin $
package au.com.chloec.store;

import javax.ejb.Local;

import au.com.chloec.store.domain.Booking;

@Local
public interface BookingList
{
   public void getBookings();
   public Booking getBooking();
   public void cancel();
   public void destroy();
}