package model;

import java.util.ArrayList;

public class ReservationList {
	private static ReservationList instance = new ReservationList();
	private ArrayList<Reservation> reservationList;
	
	private ReservationList() {
		reservationList = new ArrayList<Reservation>();
	}

	public void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}
	
    public ArrayList<Reservation> getReservations() {
        return reservationList;
    }
    
    public static ReservationList getInstance() {
    	return instance;
    }

	public ArrayList<Reservation> searchReservationById(String userId) {
		ArrayList<Reservation> result = new ArrayList<>();
		for(Reservation reservation : reservationList) {
			if(reservation.getClientId().equals(userId)) {
				result.add(reservation);
			}
		}
			
		return result; 
	}

	@Override
	public String toString() {
		return "ReservationList [reservationList=" + reservationList + "]";
	}
}
