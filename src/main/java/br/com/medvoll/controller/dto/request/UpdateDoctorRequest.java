package br.com.medvoll.controller.dto.request;

public record UpdateDoctorRequest(
        Long id,
        String name,
        String phone,
        AddressRequest addressRequest
) {}
