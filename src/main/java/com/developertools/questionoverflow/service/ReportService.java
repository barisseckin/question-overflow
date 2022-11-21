package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.request.ReportRequest;
import com.developertools.questionoverflow.model.Report;
import com.developertools.questionoverflow.model.enums.ReportType;
import com.developertools.questionoverflow.repository.ReportRepository;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void save(ReportRequest request, ReportType type) {
        Report saved = new Report(
                request.getPublicId(),
                request.getMessage(),
                type
        );
        reportRepository.save(saved);
    }
}
