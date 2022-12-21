package br.com.medvoll.controller;

import br.com.medvoll.controller.dto.request.RegisterDoctorRequest;
import br.com.medvoll.controller.dto.request.UpdateDoctorRequest;
import br.com.medvoll.controller.dto.response.DoctorListResponse;
import br.com.medvoll.entity.DoctorEntity;
import br.com.medvoll.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Page<DoctorListResponse> list(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {

        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorListResponse::new);
    }

    @PutMapping("/medicos")
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctorRequest updateDoctorRequest) {

        final var doctor = doctorRepository.getReferenceById(updateDoctorRequest.id());

        doctor.updateData(updateDoctorRequest);
    }

    @DeleteMapping("medicos/{id}")
    public void delete(@PathVariable Long id) {

        final var doctor = doctorRepository.getReferenceById(id);

        doctor.exclude();
    }
}