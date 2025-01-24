package com.rest.demo.service;

import com.rest.demo.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    List<Campaign> getAllCampaigns();

    Optional<Campaign> getCampaignById(Long id);

    Campaign createCampaign(Campaign campaign);

    Optional<Campaign> updateCampaign(Long id, Campaign campaign);
}
