package com.mintic.usa.AlquilerBarcos.Service;

import com.mintic.usa.AlquilerBarcos.Repository.ReservationRepository;
import com.mintic.usa.AlquilerBarcos.Modelo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> r = reservationRepository.getReservation(reservation.getIdReservation());
            if(r.isPresent()){
                return reservation;
            }else{
                return reservationRepository.save(reservation);
            }
        }
    }
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() != null ){
            Optional<Reservation> r = reservationRepository.getReservation(reservation.getIdReservation());
            if(r.isPresent()){
                if(reservation.getStartDate() != null){
                    r.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate() != null){
                    r.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getBoat() != null){
                    r.get().setBoat(reservation.getBoat());
                }
                if(reservation.getClient() != null){
                    r.get().setClient(reservation.getClient());
                }
                reservationRepository.save(r.get());
                return r.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }
    public boolean delete(int id){
        boolean flag = false;
        Optional<Reservation> r = reservationRepository.getReservation(id);
        if(r.isPresent()){
            reservationRepository.delete(r.get());
            flag = true;
        }
        return flag;
    }

}
