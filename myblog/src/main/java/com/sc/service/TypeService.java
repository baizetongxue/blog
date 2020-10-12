package com.sc.service;

import com.sc.pojo.Type;
import com.sun.jdi.LongType;
import org.apache.ibatis.javassist.NotFoundException;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by BaiZe schoolmate on 2020/5/10 19:22.
 */
public interface TypeService {

    List<Type> getTypes();

    List<Type> getAllTypes();

    Type getById(long id);

    int deleteById(long id);

    int SaveType(Type type);


    Type getByName(String name);

    int updateType(Type type) throws NotFoundException;

    List<Type> getRightSidebarTypes();
}
