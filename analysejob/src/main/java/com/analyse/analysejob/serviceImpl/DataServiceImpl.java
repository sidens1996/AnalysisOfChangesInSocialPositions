package com.analyse.analysejob.serviceImpl;

import com.analyse.analysejob.entity.Tags;
import com.analyse.analysejob.entity.Words;
import com.analyse.analysejob.repository.TagsRepository;
import com.analyse.analysejob.repository.WordsRepository;
import com.analyse.analysejob.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    TagsRepository tagsRepository;
    @Autowired
    WordsRepository wordsRepository;


    @Override
    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

    @Override
    public List<Words> getAllWords() {
        return wordsRepository.findAll();
    }
}
