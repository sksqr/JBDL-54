package io.bootify.l15_visitor_management_app.service;

import io.bootify.l15_visitor_management_app.domain.Flat;
import io.bootify.l15_visitor_management_app.domain.User;
import io.bootify.l15_visitor_management_app.domain.Visit;
import io.bootify.l15_visitor_management_app.domain.Visitor;
import io.bootify.l15_visitor_management_app.model.VisitDTO;
import io.bootify.l15_visitor_management_app.model.VisitStatus;
import io.bootify.l15_visitor_management_app.repos.FlatRepository;
import io.bootify.l15_visitor_management_app.repos.VisitRepository;
import io.bootify.l15_visitor_management_app.repos.VisitorRepository;
import io.bootify.l15_visitor_management_app.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;
    private final FlatRepository flatRepository;

    public VisitService(final VisitRepository visitRepository,
            final VisitorRepository visitorRepository, final FlatRepository flatRepository) {
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
        this.flatRepository = flatRepository;
    }

    public List<VisitDTO> findAll(Pageable pageable) {
     //   final List<Visit> visits = visitRepository.findAll(Sort.by("id"));
        final List<Visit> visits = visitRepository.findAll(pageable).toList();
        return visits.stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .toList();
    }

    public VisitDTO get(final Long id) {
        return visitRepository.findById(id)
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .orElseThrow(NotFoundException::new);
    }
    public void markEntry(Long id){
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit not found"));
        if(VisitStatus.APPROVED.equals(visit.getStatus())){
            visit.setInTime(LocalDateTime.now());
        }
        else {

        }
        visitRepository.save(visit);
    }

    public void markExit(Long id){
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit not found"));
        if(VisitStatus.APPROVED.equals(visit.getStatus())){
            visit.setOutTime(LocalDateTime.now());
            visit.setStatus(VisitStatus.COMPLETED);
        }
        else {

        }
        visitRepository.save(visit);
    }

    public Long create(final VisitDTO visitDTO) {
        final Visit visit = new Visit();
        mapToEntity(visitDTO, visit);
        return visitRepository.save(visit).getId();
    }

    public void update(final Long id, final VisitDTO visitDTO) {
        final Visit visit = visitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(visitDTO, visit);
        visitRepository.save(visit);
    }


    @Transactional
    public void approveVisit(Long id){
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit not found"));
        Flat flat = visit.getFlat();
        User flatUser = flat.getUser();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!userDetails.getUsername().equals(flatUser.getUsername())){
            throw new NotFoundException("No visit for you");
        }
        if(VisitStatus.WAITING.equals(visit.getStatus())){
            visit.setStatus(VisitStatus.APPROVED);
        }
        else {

        }
        visitRepository.save(visit);
    }

    public void rejectVisit(Long id){
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Visit not found"));
        Flat flat = visit.getFlat();
        User flatUser = flat.getUser();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!userDetails.getUsername().equals(flatUser.getUsername())){
            throw new NotFoundException("No visit for you");
        }
        if(VisitStatus.WAITING.equals(visit.getStatus())){
            visit.setStatus(VisitStatus.REJECTED);
        }
        else {

        }
        visitRepository.save(visit);
    }

    public void delete(final Long id) {
        visitRepository.deleteById(id);
    }

    private VisitDTO mapToDTO(final Visit visit, final VisitDTO visitDTO) {
        visitDTO.setId(visit.getId());
        visitDTO.setStatus(visit.getStatus());
        visitDTO.setInTime(visit.getInTime());
        visitDTO.setOutTime(visit.getOutTime());
        visitDTO.setPurpose(visit.getPurpose());
        visitDTO.setImageUrl(visit.getImageUrl());
        visitDTO.setNumOfPeople(visit.getNumOfPeople());
        visitDTO.setVisitor(visit.getVisitor() == null ? null : visit.getVisitor().getId());
        visitDTO.setFlat(visit.getFlat() == null ? null : visit.getFlat().getId());
        return visitDTO;
    }

    private Visit mapToEntity(final VisitDTO visitDTO, final Visit visit) {
        visit.setStatus(VisitStatus.WAITING);
        visit.setInTime(visitDTO.getInTime());
        visit.setOutTime(visitDTO.getOutTime());
        visit.setPurpose(visitDTO.getPurpose());
        visit.setImageUrl(visitDTO.getImageUrl());
        visit.setNumOfPeople(visitDTO.getNumOfPeople());
        final Visitor visitor = visitDTO.getVisitor() == null ? null : visitorRepository.findById(visitDTO.getVisitor())
                .orElseThrow(() -> new NotFoundException("visitor not found"));
        visit.setVisitor(visitor);
        final Flat flat = visitDTO.getFlat() == null ? null : flatRepository.findById(visitDTO.getFlat())
                .orElseThrow(() -> new NotFoundException("flat not found"));
        visit.setFlat(flat);
        return visit;
    }

}
