package com.lrm.service;

import com.lrm.po.Comment;
import com.lrm.po.Log;
import com.lrm.po.Tag;
import com.lrm.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Administrator
 */
public interface LogService {



    Log saveLog(Log log);

    Log getLog(Long id);


    Page<Log> listLog(Pageable pageable);

    void deleteLog(Long id);
    Log updateLog(Long id,Log log);
}
