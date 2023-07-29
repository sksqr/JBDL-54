package io.bootify.l11_visitor_management_app.repos;

import io.bootify.l11_visitor_management_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

}
