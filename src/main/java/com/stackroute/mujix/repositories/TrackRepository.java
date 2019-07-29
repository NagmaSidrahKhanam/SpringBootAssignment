package com.stackroute.mujix.repositories;

import com.stackroute.mujix.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {
    @Query(value = "SELECT * FROM Track u WHERE u.firstname = ?1",nativeQuery = true)
     List<Track> getUserbyName(String name);
}
