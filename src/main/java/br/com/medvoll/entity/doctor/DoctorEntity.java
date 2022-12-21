package br.com.medvoll.entity.doctor;

import br.com.medvoll.controller.dto.request.doctor.RegisterDoctorRequest;
import br.com.medvoll.controller.dto.request.doctor.UpdateDoctorRequest;
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
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String crm;

    @Enumerated(STRING)
    private Specialty specialty;

    @Embedded
    private AddressEntity addressEntity;

    private Boolean active;

    public DoctorEntity(RegisterDoctorRequest registerDoctorRequest) {
        this.name = registerDoctorRequest.name();
        this.email = registerDoctorRequest.email();
        this.phone = registerDoctorRequest.phone();
        this.crm = registerDoctorRequest.crm();
        this.specialty = registerDoctorRequest.specialty();
        this.addressEntity = new AddressEntity(registerDoctorRequest.addressRequest());
        this.active = true;
    }

    public void updateData(UpdateDoctorRequest updateDoctorRequest) {
        this.name = updateDoctorRequest.name();
        this.phone = updateDoctorRequest.phone();
        this.addressEntity.updateData(updateDoctorRequest.addressRequest());
    }

    public void exclude() {
        this.active = false;
    }
}
