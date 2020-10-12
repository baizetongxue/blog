package com.sc;

import com.sc.dao.BlogRepository;
import com.sc.dao.TagRepository;
import com.sc.dao.TypeRepository;
import com.sc.dao.UserRepository;
import com.sc.pojo.*;
import com.sc.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class MyblogApplicationTests {

//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    TypeRepository typeRepository;
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    BlogRepository blogRepository;
//    @Autowired
//    TagRepository tagRepsitory;
//    @Test
//    void contextLoads() {
//        User user = userRepository.findByUsernameAndPassword("wujingyi", "111111");
//        System.out.println(user);
//    }
//
//    @Test
//    void  testMd5(){
//        System.out.println(MD5Utils.code("111111"));
//    }
//    @Test
//    void  testType(){
//        int i = typeRepository.deleteById(15);
//        int i2 = typeRepository.deleteById(16);
//        System.out.println(i+"  "+i2);
//    }
//
//    @Test
//    void  testTag(){
//        List<Tag> tags = tagRepsitory.getTags();
//        for (Tag tag : tags) {
//            System.out.println(tag);
//        }
//        int i = tagRepsitory.deleteById((long) 3);
//        System.out.println(i);
//    }
//
//    @Test
//    void testBlog(){
//        int blog = blogRepository.saveBlog(
//                new Blog((long)1,"thymeleaf语法及使用","## 模板引擎简介：模板引擎(这里特指用于Web开发的模板引擎)是为了使用户界面与业务数据(内容)分离而" +
//                        "产生的","http://n.sinaimg.cn/sinacn20111/600/w1920h1080/20190331/0f41-huxwryw5226043.jpg",
//                        "原创",500,true, true, true, false,
//                        true,
//                        null,null,(long)20,(long)1, "1",
//                        null,null,null,null,null));
//        System.out.println(blog);
//    }
//
//    @Test
//    void testSearch(){
//        List<Blog> blogs = blogRepository.conditionalSearchBlogs(new Blog((long) 1, "", "## 模板引擎简介：模板引擎(这里特指用于Web开发的模板引擎)是为了使用户界面与业务数据(内容)分离而" +
//                "产生的", "http://n.sinaimg.cn/sinacn20111/600/w1920h1080/20190331/0f41-huxwryw5226043.jpg",
//                "原创", 500, true, true, true, false,
//                true,
//                null, null, (long) 24, (long) 1,  "1",
//                null, null, null, null,null));
//        for (Blog blog : blogs) {
//            System.out.println(blog);
//        }
//    }
//
//    @Test
//    void  testAll(){
//        List<Blog> blogs = blogRepository.listBlogs();
//        for (Blog blog : blogs) {
//            System.out.println(blog);
//        }
//    }
//
//    @Test
//    void test1(){
//        blogRepository.saveBlogAndTag(new BlogAndTag(20L, 1L));
//    }
//
//    @Test
//    void test2(){
//        List<Blog> indexBlog = blogRepository.getIndexBlog();
//        for (Blog blog : indexBlog) {
//            System.out.println(blog);
//        }
//    }
//    @Test
//    void test3(){
//        List<Type> rightSidebarTypes = typeRepository.getRightSidebarTypes();
//        for (Type type : rightSidebarTypes) {
//            System.out.println(type);
//        }
//    }
//    @Test
//    void test4(){
//        List<Tag> tags = tagRepsitory.getRightSidebarTags();
//        for (Tag tag : tags) {
//            System.out.println(tag);
//        }
//    }
//    @Test
//    void test5(){
//        List<Blog> recommendBlogs = blogRepository.getRecommendBlogs();
//        for (Blog recommendBlog : recommendBlogs) {
//            System.out.println(recommendBlog);
//        }
//    }
//    @Test
//    void test6(){
//        Blog detailedBlogById = blogRepository.getDetailedBlogById((long) 3);
//        System.out.println(detailedBlogById.getTags());
//
//    }
//
//    @Test
//    void test7(){
//        List<Blog> blogs = blogRepository.getBlogsByTagId((long) 4);
//        for (Blog blog : blogs) {
//            System.out.println(blog.getTagId());
//        }
//    }
}
