package com.idiot.music.base;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:19
 * @ClassName:CommonService
 * @Description:TODO
 * @Version:1.0
 * @ClassName:CommonService
 * @Description:TODO
 * @Version:1.0
 **/

/**
 *@ClassName:CommonService
 *@Description:TODO
 *@Version:1.0
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class BaseService<T, ID> {
    @Autowired
    private BaseRepository<T, ID> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public Page<T> findAll(Specification spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T findById(ID id) {
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public void save(T model) {
        repository.save(model);
    }

    public void delete(T model) {
        repository.delete(model);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public List<T> findAll(Specification spec) {
        return repository.findAll(spec);
    }


}
