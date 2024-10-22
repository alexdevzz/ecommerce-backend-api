package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.entity.Option;
import com.alexdev.ecommercebackend.model.mapper.OptionMapper;
import com.alexdev.ecommercebackend.repository.OptionRepository;
import com.alexdev.ecommercebackend.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private OptionMapper optionMapper;


    @Override
    public List<OptionDTO> getOptions() {
        return optionMapper.toOptionDtos(optionRepository.findAll());
    }

    @Override
    public OptionDTO save(OptionDTO optionDTO) {
        optionDTO.setId(0);
        optionDTO.setCreationDate(new Date());
        Option option = optionMapper.toOption(optionDTO);
        return optionMapper.toOptionDto(optionRepository.save(option));
    }

    @Override
    public OptionDTO update(int optionId, OptionDTO optionDTO) {
        Option option = optionRepository.findById(optionId).get();
        optionDTO.setId(optionId);
        optionMapper.updateOption(option, optionDTO);
        return optionMapper.toOptionDto(optionRepository.save(option));
    }

    @Override
    public OptionDTO getOption(int optionDTOId) {
        Option option = optionRepository.findById(optionDTOId).get();
        return optionMapper.toOptionDto(option);
    }

    @Override
    public OptionDTO delete(int optionDTOId) {
        Option option = optionRepository.findById(optionDTOId).get();
        OptionDTO optionDTO = optionMapper.toOptionDto(option);
        optionRepository.delete(option);
        return optionDTO;
    }

    @Override
    public boolean existsById(int id) {
        return optionRepository.existsById(id);
    }
}
