package br.com.medvoll.controller.dto.response;

import br.com.medvoll.entity.AddressEntity;
import br.com.medvoll.entity.DoctorEntity;
import br.com.medvoll.enums.Specialty;

public record DetailsDoctorResponse(Long id, String name, String email, String crm, Specialty specialty, AddressEntity addressEntity) {

    public DetailsDoctorResponse(DoctorEntity doctorEntity) {
        this(doctorEntity.getId(), doctorEntity.getName(), doctorEntity.getEmail(), doctorEntity.getCrm(), doctorEntity.getSpecialty(), doctorEntity.getAddressEntity())
    }
}
