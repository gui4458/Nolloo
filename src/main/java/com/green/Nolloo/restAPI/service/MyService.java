package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.MyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Spring Boot service class
@Service
public class MyService {

    @Autowired
    private MyMapper myMapper;

    @Autowired
    private LuceneIndexer luceneIndexer;

    public List<MyEntity> getAllEntities() {
        System.out.println("-----getAllEntities---");
        return myMapper.getAllEntities();
    }

    public List<MyEntity> searchEntities(String query) {
        // Index entities if not already indexed
        System.out.println("-----searchEntities---");
//        List<MyEntity> entities = myMapper.getAllEntities();
//        luceneIndexer.indexEntities(entities);
        List<String> entities = myMapper.searchEntities(query);
        // Search indexed entities
        return luceneIndexer.searchEntities(query);
    }
}