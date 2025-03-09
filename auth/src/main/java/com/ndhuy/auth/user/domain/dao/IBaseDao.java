package com.ndhuy.auth.user.domain.dao;

public interface IBaseDao<K,T> {
    T insert(T input);
    T update(K id,T input);
    T delete(T input);
    T findById(K id);
}
