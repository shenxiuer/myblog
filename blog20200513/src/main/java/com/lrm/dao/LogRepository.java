package com.lrm.dao;

import com.lrm.po.Log;
import com.lrm.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by limi on 2017/10/15.
 */
public interface LogRepository extends JpaRepository<Log,Long> {


}
