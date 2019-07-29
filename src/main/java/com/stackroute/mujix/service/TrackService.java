package com.stackroute.mujix.service;

import com.stackroute.mujix.exceptions.TrackAlreadyExistsException;
import com.stackroute.mujix.exceptions.TrackNotFoundException;
import com.stackroute.mujix.model.Track;


import java.util.List;

public interface TrackService {


    public Track saveTrack(Track user) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public void deleteTrack(int id)throws TrackNotFoundException;

    public Track getTrackById(int trackid);

    public Track updateTrack(int id, Track track);

    public List<Track> getTrackbyName(String name)throws TrackNotFoundException ;

}
