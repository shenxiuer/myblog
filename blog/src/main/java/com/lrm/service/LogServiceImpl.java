package com.lrm.service;


import com.lrm.NotFoundException;
import com.lrm.dao.LogRepository;
import com.lrm.po.Log;


import com.lrm.po.Tag;
import com.lrm.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private  LogRepository logRepository;
    //日志资源库



    @Transactional
    @Override
    public Log saveLog(Log log) {
        log.setCreateTime(new Date());
        //存储日志资源
        return logRepository.save(log);
    }

    @Override
    public Page<Log> listLog(Pageable pageable) {
        return logRepository.findAll(pageable);
        //分页查询
    }

    
    @Override
    public void deleteLog(Long id) {  //根据id删除
        logRepository.delete(id);}

    @Transactional
    @Override
    public Log getLog(Long id) {
        return logRepository.findOne(id);
    }


    @Transactional
    @Override
    public Log updateLog(Long id, Log log) {

        Log t = logRepository.findOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(log,t);
        t.setUpdateTime(new Date());
        return logRepository.save(t);
    }

}

