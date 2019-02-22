package com.analyse.analysejob.repository;

import com.analyse.analysejob.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Words, Integer> {

}
