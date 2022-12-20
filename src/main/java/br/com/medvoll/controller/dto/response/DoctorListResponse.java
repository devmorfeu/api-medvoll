package br.com.medvoll.controller.dto.response;

import br.com.medvoll.entity.DoctorEntity;
import br.com.medvoll.enums.Specialty;

public record DoctorListResponse(String name, String email, String crm, Specialty specialty) {

    public DoctorListResponse(DoctorEntity doctorEntity) {
        this(doctorEntity.getName(), doctorEntity.getEmail(), doctorEntity.getCrm(), doctorEntity.getSpecialty());
    }
}