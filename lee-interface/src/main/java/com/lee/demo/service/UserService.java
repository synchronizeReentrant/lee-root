package com.lee.demo.service;

public interface UserService {

    String save(String jsonStr);

    String update(String jsonStr);

    String delete(String jsonStr);

    String delete(Integer id);

    String findBy(String str);

}
