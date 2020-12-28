package com.cfu.mmap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Cathy.Fu on 2020/12/27.
 */
@Repository
public interface SearchResordRepository extends JpaRepository<SearchRecord, Long> {
    List<SearchRecord> findByUserIdAndSearchValue(String userId, String searchValue);
}
