package io.bootify.l15_visitor_management_app.util;


import io.bootify.l15_visitor_management_app.domain.Visit;
import io.bootify.l15_visitor_management_app.model.VisitStatus;
import io.bootify.l15_visitor_management_app.repos.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class VisitExpireScheduledTask {
    private static Logger LOGGER = LoggerFactory.getLogger(VisitExpireScheduledTask.class);

    @Autowired
    private VisitRepository visitRepository;

    @Scheduled(fixedDelay = 1000000)
    public void markVisitsExpire(){
        LOGGER.info("Starting the task markVisitsExpire");
        List<Visit> visitList = visitRepository.findByStatus(VisitStatus.WAITING);
        for(Visit visit : visitList){
            visit.setStatus(VisitStatus.EXPIRE);
        }
        visitRepository.saveAll(visitList);
        LOGGER.info("Completed the task markVisitsExpire");
    }
}
