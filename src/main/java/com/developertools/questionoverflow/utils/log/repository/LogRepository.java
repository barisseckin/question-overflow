package com.developertools.questionoverflow.utils.log.repository;

import com.developertools.questionoverflow.utils.log.model.Log;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepository {

    private static final List<Log> logList = new ArrayList<>();

    static {
        logList.add(new Log("question overflow started"));
    }

    public Log save(Log log) {
        logList.add(log);

        return log;
    }

    public List<Log> findAll() {
        return logList;
    }

}
