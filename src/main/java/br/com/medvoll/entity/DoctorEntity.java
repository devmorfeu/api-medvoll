package br.com.medvoll.entity;

import br.com.medvoll.controller.dto.request.RegisterDoctorRequest;
import br.com.medvoll.enums.Specialty;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Table(name = "doctors")
@Entity(name = "Doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String name;

    private String email;

    private String phone;

    private String crm;

    @Enumerated(STRING)
    private Specialty specialty;

    @Embedded
    private AddressEntity addressEntity;

    public DoctorEntity(RegisterDoctorRequest registerDoctorRequest) {
        this.name = registerDoctorRequest.name();
        this.email = registerDoctorRequest.email();
        this.phone = registerDoctorRequest.phone();
        this.crm = registerDoctorRequest.crm();
        this.specialty = registerDoctorRequest.specialty();
        this.addressEntity = new AddressEntity(registerDoctorRequest.addressRequest());
    }
}