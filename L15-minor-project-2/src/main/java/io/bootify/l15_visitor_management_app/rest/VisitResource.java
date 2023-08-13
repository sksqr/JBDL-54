package io.bootify.l15_visitor_management_app.rest;

import io.bootify.l15_visitor_management_app.model.VisitDTO;
import io.bootify.l15_visitor_management_app.service.VisitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/visits", produces = MediaType.APPLICATION_JSON_VALUE)
public class VisitResource {

    private final VisitService visitService;

    public VisitResource(final VisitService visitService) {
        this.visitService = visitService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<VisitDTO> getVisit(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(visitService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVisit(@RequestBody @Valid final VisitDTO visitDTO) {
        final Long createdId = visitService.create(visitDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateVisit(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final VisitDTO visitDTO) {
        visitService.update(id, visitDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVisit(@PathVariable(name = "id") final Long id) {
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
