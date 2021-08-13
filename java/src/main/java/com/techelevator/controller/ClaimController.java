package com.techelevator.controller;

import com.techelevator.model.Claim;
import com.techelevator.services.ClaimService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class ClaimController {

    private ClaimService claimService;

    public ClaimController(ClaimService claimService){
        this.claimService = claimService;
    }

    @RequestMapping(path ="/claim", method = RequestMethod.POST)
    public void create(@Valid @RequestBody Claim claim){
        claimService.makeClaim(claim);
    }
}
