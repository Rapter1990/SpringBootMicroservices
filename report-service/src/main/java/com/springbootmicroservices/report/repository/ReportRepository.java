package com.springbootmicroservices.report.repository;

import com.springbootmicroservices.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {

}
