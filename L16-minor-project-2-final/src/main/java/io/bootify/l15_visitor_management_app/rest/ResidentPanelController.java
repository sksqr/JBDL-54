package io.bootify.l15_visitor_management_app.rest;

import io.bootify.l15_visitor_management_app.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resident")
public class ResidentPanelController {

    @Autowired
    private VisitService visitService;

    @PutMapping("/approveVisit/{id}")
    public ResponseEntity<Void> approveVisit(@PathVariable Long id){
        visitService.approveVisit(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rejectVisit/{id}")
    public ResponseEntity<Void> rejectVisit(@PathVariable Long id){
        visitService.rejectVisit(id);
        return ResponseEntity.ok().build();
    }

}
