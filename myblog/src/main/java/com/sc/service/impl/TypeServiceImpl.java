package com.sc.service.impl;

import com.sc.dao.TypeRepository;
import com.sc.exception.NotFoundIndexException;
import com.sc.pojo.Type;
import com.sc.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 19:29.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Transactional
    @Override
    public List<Type> getTypes() {
        return repository.getTypes();
    }
    @Transactional
    @Override
    public List<Type> getAllTypes() {
        return repository.getAllTypes();
    }
    @Transactional
    @Override
    public Type getById(long id) {
        return repository.getById(id);
    }
    @Transactional
    @Override
    public int deleteById(long id) {
         return repository.deleteById(id);
    }

    @Transactional
    @Override
    public int SaveType(Type type) {
        return repository.saveType(type);
    }

    @Transactional
    @Override
    public Type getByName(String name) {
        return repository.getByName(name);
    }
    @Transactional
    @Override
    public int updateType(Type type)  {

      Type flag = type;
      if (null ==flag){
          throw new NotFoundIndexException("不存在该类型");
      }
      BeanUtils.copyProperties(type,flag);
        return repository.updateType(flag);
    }

    @Override
    public List<Type> getRightSidebarTypes() {
        return repository.getRightSidebarTypes();
    }
}
