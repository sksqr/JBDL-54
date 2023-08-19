package io.bootify.l15_visitor_management_app.rest;

import io.bootify.l15_visitor_management_app.model.VisitDTO;
import io.bootify.l15_visitor_management_app.model.VisitorDTO;
import io.bootify.l15_visitor_management_app.service.VisitService;
import io.bootify.l15_visitor_management_app.service.VisitorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/gatekeeper/")
public class GateKeeperPanelController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitService visitService;


    @PostMapping("/createVisitor")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVisitor(@RequestBody @Valid final VisitorDTO visitorDTO) {
        final Long createdId = visitorService.create(visitorDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PostMapping("/createVisit")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVisit(@RequestBody @Valid final VisitDTO visitDTO) {
        final Long createdId = visitService.create(visitDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }


    @PutMapping("/markEntry/{id}")
    public ResponseEntity<Void> markEntry(@PathVariable(name = "id") final Long id) {
        visitService.markEntry(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/markExit/{id}")
    public ResponseEntity<Void> markExit(@PathVariable(name = "id") final Long id) {
        visitService.markExit(id);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String response = "";
        try {
            String uploadPath = "/tmp/images/testFile_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath));
            response = uploadPath;
        } catch (Exception ex){
            response="Exception "+ex.getMessage();
        }
        return ResponseEntity.ok().body(response);
    }


}
