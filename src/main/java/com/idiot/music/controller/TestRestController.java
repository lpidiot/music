package com.idiot.music.controller;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:14
 **/

import com.idiot.music.entity.TestData;
import com.idiot.music.service.TestDataService;
import com.idiot.music.utils.AjaxResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:TestRestController
 * @Description:TODO
 * @Version:1.0
 **/
@RestController
@RequestMapping("/api")
public class TestRestController {
    @Autowired
    private TestDataService service;

    @PostMapping(value = "/test")
    public AjaxResponse saveArticle(@RequestBody TestData data) {
        try {
            service.save(data);
            return AjaxResponse.success("保存成功");
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @DeleteMapping(value = "/test/{id}")
    public AjaxResponse deleteUser(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return AjaxResponse.success("删除成功");
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @PutMapping(value = "/test/{id}")
    public AjaxResponse updateUser(@PathVariable Integer id, @RequestBody TestData data) {
        try {
            TestData testData = service.findById(id);
            BeanUtils.copyProperties(data, testData, "id");
            service.save(testData);
            return AjaxResponse.success();
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @GetMapping(value = "/test/{id}")
    public AjaxResponse getUser(@PathVariable Integer id) {
        try {
            return AjaxResponse.success(service.findById(id));
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }
}
