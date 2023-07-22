package io.bootify.l09_visitor_management_app.repos;

import io.bootify.l09_visitor_management_app.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
