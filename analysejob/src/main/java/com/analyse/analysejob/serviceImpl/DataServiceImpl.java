package com.analyse.analysejob.serviceImpl;

import com.analyse.analysejob.entity.Tags;
import com.analyse.analysejob.repository.TagsRepository;
import com.analyse.analysejob.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    TagsRepository tagsRepository;


    @Override
    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

}
