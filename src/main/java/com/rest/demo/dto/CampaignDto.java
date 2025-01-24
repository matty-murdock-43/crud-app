package com.rest.demo.dto;

import com.rest.demo.model.Campaign;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record CampaignDto(Long id, String code, String name, String description, Set<TaskDto> tasks) {
    public static class Mapper{
        public static Campaign toModel(CampaignDto dto){
            if(dto == null) return null;
            Campaign model = new Campaign(dto.code(), dto.name(), dto.description());
            if(!Objects.isNull(dto.id())){
                model.setId(dto.id());
            }
            return model;
        }
        public static CampaignDto toDto(Campaign model){
            if(model == null) return null;
            Set<TaskDto> tasks = model.getTasks().stream().map(t -> TaskDto.Mapper.toDto(t)).collect(Collectors.toSet()); // or TaskDto.Mapper::toDto
            CampaignDto dto = new CampaignDto(model.getId(), model.getCode(), model.getName(), model.getDescription(), tasks); // WorkerDto is an immutable record
            return dto;
        }
    }
}
