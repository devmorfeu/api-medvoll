package br.com.medvoll.controller.dto.response;

import br.com.medvoll.entity.doctor.DoctorEntity;
import br.com.medvoll.enums.Specialty;

public record DoctorListResponse(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorListResponse(DoctorEntity doctorEntity) {
        this(doctorEntity.getId(), doctorEntity.getName(), doctorEntity.getEmail(), doctorEntity.getCrm(), doctorEntity.getSpecialty());
    }
}