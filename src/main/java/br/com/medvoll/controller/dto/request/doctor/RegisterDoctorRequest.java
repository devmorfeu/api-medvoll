package br.com.medvoll.controller.dto.request.doctor;

import br.com.medvoll.enums.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorRequest(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^([1-9]{2})9?[0-9]{4}-[0-9]{4}$")
        String phone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Specialty specialty,

        @NotNull
        @Valid
        AddressRequest addressRequest
) {}