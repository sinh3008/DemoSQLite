package com.example.sqlitedatabase.dao;

import com.example.sqlitedatabase.entity.Information;

import java.util.List;

public interface IInformation {
    public List<Information> selectAll();

    public boolean insert(Information i);

    public Information detail(int ID);

    public boolean update(Information i);

    public boolean delete(int ID);
}
