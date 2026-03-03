package vod.service;

import vod.repository.CinemaDao;
import vod.repository.MovieDao;
import vod.repository.mem.MemCinemaDao;
import vod.repository.mem.MemMovieDao;
import vod.model.Cinema;
import vod.service.impl.CinemaServiceBean;

import java.util.List;
import java.util.Set;

public class VodServiceMain {

    public static void main(String[] args) {
        System.out.println("Let's find cinemas!");

        // service preparation przygotowanie serwisu który używa repo jednego cinemaservice
        //chcemy wyswielic lisę kin, repo dają dostęp do nich, ale z punktu widzenia apliakcji
        //powiinismy komunikowac sie przez serwis
        //serwis jest taka fasadą ponad repozytoriami - architektura wielowarstwowa
        CinemaDao cinemaDao = new MemCinemaDao();
        MovieDao movieDao = new MemMovieDao();
        //przygotowanie serwisu ma jedna impelemntacja, moze byc ich wiecej
        //ta impelemntacja potrzebuje w swoim konstruktorze dostarczyc obiekty dao
        //tu mamy znowu dwa intefejsy i trzeba sie zastanowic z których impelemntacji skorzystać
        CinemaService service = new CinemaServiceBean(cinemaDao, movieDao);

        // service use
        //cinemaservice ma api zwraca wsyzstkie kina
      //  tylko że ten serwis trzeba przygotowac, trzeba pozyskać tą usługe.
        List<Cinema> cinemas = service.getAllCinemas();
        System.out.println(cinemas.size() + " cinemas found:");
        cinemas.forEach(System.out::println);
    }
}
