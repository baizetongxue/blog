package com.sc.service.impl;

import com.sc.dao.TagRepository;
import com.sc.pojo.Tag;
import com.sc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/11 14:26.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.getTags();
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.getAllTags();
    }

    @Override
    public int deleteById(Long id) {
        return tagRepository.deleteById(id);
    }

    @Override
    public int saveTag(Tag tag) {
        return tagRepository.saveTag(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.getByName(name);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagRepository.updateTag(tag);
    }

    @Override
    public List<Tag> getTagByTagIds(String tagIds) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(tagIds);
        for (Long aLong : longs) {
            tags.add(tagRepository.getById(aLong));
        }
        return tags;
    }

    public List<Long> convertToList(String tagIds){
        List<Long> tagIdList = new ArrayList<>();
        if (!"".equals(tagIds)&&null!= tagIds){
            String[] tagIdArray = tagIds.split(",");
            for (int i=0; i< tagIdArray.length ;i++){
                tagIdList.add(Long.valueOf(tagIdArray[i]));
            }
        }
        return tagIdList;
    }

    @Override
    public List<Tag> getRightSidebarTags(){
        return tagRepository.getRightSidebarTags();
    };
}
