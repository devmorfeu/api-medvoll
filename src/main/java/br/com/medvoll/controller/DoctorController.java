package br.com.medvoll.controller;

import br.com.medvoll.controller.dto.request.RegisterDoctorRequest;
import br.com.medvoll.controller.dto.response.DoctorListResponse;
import br.com.medvoll.entity.DoctorEntity;
import br.com.medvoll.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @PostMapping("/medicos")
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorRequest registerDoctorRequest) {
        doctorRepository.save(new DoctorEntity(registerDoctorRequest));
    }

    @GetMapping("/medicos")
    public List<DoctorListResponse> list() {
        return doctorRepository.findAll().stream().map(DoctorListResponse::new).toList();
    }
}