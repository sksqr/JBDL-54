package io.bootify.l15_visitor_management_app.service;

import io.bootify.l15_visitor_management_app.domain.Address;
import io.bootify.l15_visitor_management_app.domain.Visitor;
import io.bootify.l15_visitor_management_app.model.AddressDTO;
import io.bootify.l15_visitor_management_app.model.VisitorDTO;
import io.bootify.l15_visitor_management_app.repos.AddressRepository;
import io.bootify.l15_visitor_management_app.repos.VisitorRepository;
import io.bootify.l15_visitor_management_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final AddressRepository addressRepository;

    public VisitorService(final VisitorRepository visitorRepository,
            final AddressRepository addressRepository) {
        this.visitorRepository = visitorRepository;
        this.addressRepository = addressRepository;
    }

    public List<VisitorDTO> findAll() {
        final List<Visitor> visitors = visitorRepository.findAll(Sort.by("id"));
        return visitors.stream()
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .toList();
    }

    public VisitorDTO get(final Long id) {
        return visitorRepository.findById(id)
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VisitorDTO visitorDTO) {
        final Visitor visitor = new Visitor();
        mapToEntity(visitorDTO, visitor);
        return visitorRepository.save(visitor).getId();
    }

    public void update(final Long id, final VisitorDTO visitorDTO) {
        final Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(visitorDTO, visitor);
        visitorRepository.save(visitor);
    }

    public void delete(final Long id) {
        visitorRepository.deleteById(id);
    }

    private VisitorDTO mapToDTO(final Visitor visitor, final VisitorDTO visitorDTO) {
        visitorDTO.setId(visitor.getId());
        visitorDTO.setName(visitor.getName());
        visitorDTO.setEmail(visitor.getEmail());
        visitorDTO.setPhone(visitor.getPhone());
        visitorDTO.setIdNumber(visitor.getIdNumber());
        final AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLine1(visitor.getAddress().getLine1());
        addressDTO.setLine2(visitor.getAddress().getLine2());
        addressDTO.setPincode(visitor.getAddress().getPincode());
        addressDTO.setCity(visitor.getAddress().getCity());
        addressDTO.setCountry(visitor.getAddress().getCountry());
        visitorDTO.setAddress(addressDTO);
        return visitorDTO;
    }

    private Visitor mapToEntity(final VisitorDTO visitorDTO, final Visitor visitor) {
        visitor.setName(visitorDTO.getName());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setPhone(visitorDTO.getPhone());
        visitor.setIdNumber(visitorDTO.getIdNumber());
        final Address address = new Address();
        address.setLine1(visitorDTO.getAddress().getLine1());
        address.setLine2(visitorDTO.getAddress().getLine2());
        address.setPincode(visitorDTO.getAddress().getPincode());
        address.setCity(visitorDTO.getAddress().getCity());
        address.setCountry(visitorDTO.getAddress().getCountry());
        addressRepository.save(address);
        visitor.setAddress(address);
        return visitor;
    }

    public boolean idNumberExists(final String idNumber) {
        return visitorRepository.existsByIdNumberIgnoreCase(idNumber);
    }

}
