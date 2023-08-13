package io.bootify.l15_visitor_management_app.service;

import io.bootify.l15_visitor_management_app.domain.Address;
import io.bootify.l15_visitor_management_app.model.AddressDTO;
import io.bootify.l15_visitor_management_app.repos.AddressRepository;
import io.bootify.l15_visitor_management_app.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> findAll() {
        final List<Address> addresss = addressRepository.findAll(Sort.by("id"));
        return addresss.stream()
                .map(address -> mapToDTO(address, new AddressDTO()))
                .toList();
    }

    public AddressDTO get(final Long id) {
        return addressRepository.findById(id)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }

    public void update(final Long id, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setId(address.getId());
        addressDTO.setLine1(address.getLine1());
        addressDTO.setLine2(address.getLine2());
        addressDTO.setCity(address.getCity());
        addressDTO.setPincode(address.getPincode());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setLine1(addressDTO.getLine1());
        address.setLine2(addressDTO.getLine2());
        address.setCity(addressDTO.getCity());
        address.setPincode(addressDTO.getPincode());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

}
