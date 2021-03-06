package com.lambdaschool.projectrestdogs.controllers;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.Services.MessageSender;
import com.lambdaschool.projectrestdogs.exceptions.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.models.Dog;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dogs")
public class DogController
{

    private static final Logger logger = LoggerFactory.getLogger(Dog.class);


    @Autowired
    MessageSender msgSender;

    // localhost:8080/dogs/dogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("/All Dogs Accessed");
        msgSender.sendMessage("!!ACCESSED ALL DOGS, DOG!!");
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);

    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;
        logger.info("/dog " + id + " Accessed");
        msgSender.sendMessage("ACCESSED DOG WITH THE ID#: " + id);
        if ((ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId()) == id)) == null)
        {
            throw new ResourceNotFoundException("Dog with id " + id + " not found");
        } else
        {
            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId() == id));
        }
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        logger.info("/dog " + breed + " Accessed");
        msgSender.sendMessage("ACCESSED BREED TYPE: " + breed);
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }

    //localhost:1969/dogs/dogtable
    @GetMapping(value = "/dogtable")
    public ModelAndView displayDogTable()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dogs");
        mav.addObject("dogList", ProjectrestdogsApplication.ourDogList.dogList);

        return mav;
    }
}
