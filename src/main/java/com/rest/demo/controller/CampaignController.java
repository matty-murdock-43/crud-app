package com.rest.demo.controller;

import com.rest.demo.dto.CampaignDto;
import com.rest.demo.model.Campaign;
import com.rest.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/campaigns")
public class CampaignController {
    @Autowired
    CampaignService campaignService;

    @GetMapping
    public List<CampaignDto> findCampaigns(){
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        return campaigns.stream().map(CampaignDto.Mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CampaignDto findOne(@PathVariable Long id){
        return CampaignDto.Mapper.toDto(campaignService.getCampaignById(id).orElseThrow(()-> new RuntimeException("HttpStatus.NOT_FOUND")));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CampaignDto createCampaign(@RequestBody CampaignDto campaignDto){
        Campaign campaign = CampaignDto.Mapper.toModel(campaignDto);
        return CampaignDto.Mapper.toDto(campaignService.createCampaign(campaign));
    }

    @PutMapping(value = "/{id}")
    public CampaignDto updateCampaign(@PathVariable Long id, @RequestBody CampaignDto campaignDto){
        Campaign model = CampaignDto.Mapper.toModel(campaignDto);
        return CampaignDto.Mapper.toDto(campaignService.updateCampaign(id, model).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
