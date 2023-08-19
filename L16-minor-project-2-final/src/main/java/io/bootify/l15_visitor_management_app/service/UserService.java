package io.bootify.l15_visitor_management_app.service;

import io.bootify.l15_visitor_management_app.domain.Address;
import io.bootify.l15_visitor_management_app.domain.Flat;
import io.bootify.l15_visitor_management_app.domain.User;
import io.bootify.l15_visitor_management_app.model.AddressDTO;
import io.bootify.l15_visitor_management_app.model.UserDTO;
import io.bootify.l15_visitor_management_app.model.UserStatus;
import io.bootify.l15_visitor_management_app.repos.AddressRepository;
import io.bootify.l15_visitor_management_app.repos.FlatRepository;
import io.bootify.l15_visitor_management_app.repos.UserRepository;
import io.bootify.l15_visitor_management_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final FlatRepository flatRepository;
    private final AddressRepository addressRepository;

    public UserService(final UserRepository userRepository, final FlatRepository flatRepository,
            final AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.flatRepository = flatRepository;
        this.addressRepository = addressRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void markInactive(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        userDTO.setFlatNumber(user.getFlat() == null ? null : user.getFlat().getNumber());
        final AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLine1(user.getAddress().getLine1());
        addressDTO.setLine2(user.getAddress().getLine2());
        addressDTO.setPincode(user.getAddress().getPincode());
        addressDTO.setCity(user.getAddress().getCity());
        addressDTO.setCountry(user.getAddress().getCountry());
        userDTO.setAddress(addressDTO);
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());
        Flat flat = flatRepository.findByNumber(userDTO.getFlatNumber());
        if(flat == null && userDTO.getFlatNumber() != null){
            flat = new Flat();
            flat.setUser(user);
            flat.setNumber(userDTO.getFlatNumber());
            flatRepository.save(flat);
        }
        user.setFlat(flat);
        if(flat!=null && flat.getUser()==null){
            flat.setUser(user);
            flatRepository.save(flat);
        }
        final Address address = new Address();
        address.setLine1(userDTO.getAddress().getLine1());
        address.setLine2(userDTO.getAddress().getLine2());
        address.setPincode(userDTO.getAddress().getPincode());
        address.setCity(userDTO.getAddress().getCity());
        address.setCountry(userDTO.getAddress().getCountry());
        addressRepository.save(address);
        user.setAddress(address);
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return user;
    }
}
