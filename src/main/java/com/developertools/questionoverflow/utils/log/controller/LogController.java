package com.developertools.questionoverflow.utils.log.controller;

import com.developertools.questionoverflow.utils.log.dto.LogDto;
import com.developertools.questionoverflow.utils.log.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/log")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<LogDto>> getAll() {
        return ResponseEntity
                .ok(logService.getAll());
    }
}
