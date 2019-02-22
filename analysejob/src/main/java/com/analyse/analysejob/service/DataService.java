package com.analyse.analysejob.service;

import com.analyse.analysejob.entity.Tags;
import com.analyse.analysejob.entity.Words;

import java.util.List;

public interface DataService {
    List<Tags> getAllTags();
    List<Words> getAllWords();
}
