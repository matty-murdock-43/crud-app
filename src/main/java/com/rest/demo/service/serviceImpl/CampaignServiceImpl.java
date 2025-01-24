package com.rest.demo.service.serviceImpl;

import com.rest.demo.model.Campaign;
import com.rest.demo.repo.CampaignRepo;
import com.rest.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    CampaignRepo campaignRepo;

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignRepo.findAll();
    }

    @Override
    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepo.findById(id);
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        campaign.setId(null);
        return campaignRepo.save(campaign);
    }

    @Override
    public Optional<Campaign> updateCampaign(Long id, Campaign newCampaign) {
//        return update(base -> updateCamp(base, updated));
        return campaignRepo.findById(id).map(base -> update(base, newCampaign)).map(campaignRepo::save);
    }

    private Campaign update(Campaign base, Campaign newCampaign) {
        base.setDescription(newCampaign.getDescription());
        base.setName(newCampaign.getName());
        base.setTasks(newCampaign.getTasks());
        return base;
    }
}
