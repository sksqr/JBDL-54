package io.bootify.l10_visitor_management_app.repos;

import io.bootify.l10_visitor_management_app.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    boolean existsByIdNumberIgnoreCase(String idNumber);

}
