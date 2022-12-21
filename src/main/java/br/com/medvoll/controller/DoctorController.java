package br.com.medvoll.controller;

import br.com.medvoll.controller.dto.request.RegisterDoctorRequest;
import br.com.medvoll.controller.dto.request.UpdateDoctorRequest;
import br.com.medvoll.controller.dto.response.DoctorListResponse;
import br.com.medvoll.controller.dto.response.DetailsDoctorResponse;
import br.com.medvoll.entity.doctor.DoctorEntity;
import br.com.medvoll.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @PostMapping("/medicos")
    @Transactional
    public ResponseEntity<DetailsDoctorResponse> register(@RequestBody @Valid RegisterDoctorRequest registerDoctorRequest, UriComponentsBuilder uriComponentsBuilder) {

        final var doctorEntity = new DoctorEntity(registerDoctorRequest);

        doctorRepository.save(doctorEntity);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(doctorEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsDoctorResponse(doctorEntity));
    }

    @GetMapping("/medicos")
    public ResponseEntity<Page<DoctorListResponse>> list(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {

        final var response = doctorRepository.findAllByActiveTrue(pageable).map(DoctorListResponse::new);

        return ok(response);
    }

    @PutMapping("/medicos")
    @Transactional
    public ResponseEntity<DetailsDoctorResponse> update(@RequestBody @Valid UpdateDoctorRequest updateDoctorRequest) {

        final var doctor = doctorRepository.getReferenceById(updateDoctorRequest.id());

        doctor.updateData(updateDoctorRequest);

        return ok(new DetailsDoctorResponse(doctor));
    }

    @DeleteMapping("medicos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {

        final var doctor = doctorRepository.getReferenceById(id);

        doctor.exclude();

        return noContent().build();
    }

    @GetMapping("medicos/{id}")
    public ResponseEntity<?> details(@PathVariable Long id) {

        final var doctor = doctorRepository.getReferenceById(id);

        return ok(new DetailsDoctorResponse(doctor));
    }
}