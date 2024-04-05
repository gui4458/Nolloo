package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.MyEntity;
import org.springframework.stereotype.Component;

import java.util.List;

// Lucene Indexing class
@Component
public class LuceneIndexer {
    public void indexEntities(List<MyEntity> entities) {
        System.out.println("&&&&&&&&&&&&&Lucene Index Entities");
        // Code to index entities using Lucene
    }

    public List<MyEntity> searchEntities(String query) {
        // Code to search entities using Lucene
        System.out.println("*************Lucene Search Entities");
        return null;
    }
}