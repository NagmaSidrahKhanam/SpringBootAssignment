package com.stackroute.mujix.controller;

import com.stackroute.mujix.exceptions.TrackAlreadyExistsException;
import com.stackroute.mujix.exceptions.TrackNotFoundException;
import com.stackroute.mujix.model.Track;
import com.stackroute.mujix.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
//@ControllerAdvice(basePackages="com.stackroute.muzix")
public class TrackController {
    TrackService trackService;
    TrackControllerAdvice trackControllerAdvice;


    public TrackController(TrackService userService)
    {
        this.trackService=userService;
    }

    @PostMapping("user")
    @ExceptionHandler(TrackAlreadyExistsException.class)
    public ResponseEntity<?> saveuser(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully created",HttpStatus.CREATED);
        }
        catch(TrackAlreadyExistsException ex)
        {
            responseEntity=trackControllerAdvice.alreadyExistsException(ex);
        }
        return responseEntity;
    }

    @GetMapping("user")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        ResponseEntity responseEntity;
        try
        {
            trackService.deleteTrack(id);
            responseEntity=new ResponseEntity("successfully deleted",HttpStatus.OK);
        }
        catch(Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.updateTrack(id,track);
            responseEntity=new ResponseEntity("successfully updated",HttpStatus.OK);
        }
        catch(Exception ex)
        {
            responseEntity=new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("user/{firstName}")
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<?> getTrackbyName(@PathVariable String firstName) {

        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getTrackbyName(firstName), HttpStatus.CREATED);


        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;

    }
}

