package com.stackroute.mujix.service;

import com.stackroute.mujix.exceptions.TrackAlreadyExistsException;
import com.stackroute.mujix.exceptions.TrackNotFoundException;
import com.stackroute.mujix.model.Track;
import com.stackroute.mujix.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {
    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository userRepository)
    {
        this.trackRepository=userRepository;
    }

    @Override
    public Track saveTrack(Track user) throws TrackAlreadyExistsException {

        Track savedUser=trackRepository.save(user);
        if(trackRepository.existsById(user.getId()))
        {
            throw new TrackAlreadyExistsException();
        }
        if(savedUser==null)
        {
            throw new TrackAlreadyExistsException();
        }
        return savedUser;
    }

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    @Override
    public void deleteTrack(int id)
    {

        trackRepository.deleteById(id);
    }

    @Override
    public Track getTrackById(int userid) {
        return null;
    }

    @Override
    public List<Track> getTrackbyName(String firstName) throws TrackNotFoundException {
        List<Track> user=trackRepository.getUserbyName(firstName);
        if(user.isEmpty())
        {
            throw new TrackNotFoundException();
        }

        return trackRepository.getUserbyName(firstName);
    }


    @Override
    public Track updateTrack(int id,Track user) {
        Track updateUser=trackRepository.save(user);
        return updateUser;
    }

}