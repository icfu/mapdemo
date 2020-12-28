package com.cfu.mmap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Cathy.Fu on 2020/12/27.
 */
@Service
@RestController
@Slf4j
@RequestMapping("records")
public class SearchRecordService {

    @Autowired
    private SearchResordRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<SearchRecord> findAll(){
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public SearchRecord create(@RequestBody SearchRecord record){
        log.info("received records: {}", record);

        if(record != null) {
            String userId = record.getUserId();
            String searchValue = record.getSearchValue();

            // 先按照用户和关键字查找记录,
            List<SearchRecord> records = repository.findByUserIdAndSearchValue(userId, searchValue);

            // 如果找到对应记录, 更新查询次数, 这样可以找到最常用的关键字, 做查询,做比较都可以用
            if(CollectionUtils.isNotEmpty(records)){
                log.info("found out {} records with same userId: {} and search value:{}", records.size(), userId, searchValue);

                // 如果当前查询关键字相同, 查询次数自动加一, 并更新时间
                for(SearchRecord recordItem : records){
                    recordItem.setCount(recordItem.getCount()+1);
                    record = repository.save(recordItem);
                }
            } else {
                record.setCount(1);
                record = repository.save(record);
            }
        }

        return record;
    }
}
