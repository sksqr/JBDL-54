package io.bootify.l09_visitor_management_app.repos;

import io.bootify.l09_visitor_management_app.domain.Flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatRepository extends JpaRepository<Flat, Long> {
}
