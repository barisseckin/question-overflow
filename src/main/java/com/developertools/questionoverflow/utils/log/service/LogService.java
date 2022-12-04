package com.developertools.questionoverflow.utils.log.service;

import com.developertools.questionoverflow.utils.log.dto.LogDto;
import com.developertools.questionoverflow.utils.log.model.Log;
import com.developertools.questionoverflow.utils.log.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void save(String description) {
        Log saved = new Log(
                description
        );

        logRepository.save(saved);
    }

    public List<LogDto> getAll() {
        return logRepository.findAll()
                .stream()
                .map(log -> new LogDto(log.getDescription(), log.getCreateDate()))
                .collect(Collectors.toList());
    }
}
