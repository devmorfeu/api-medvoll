package br.com.medvoll.entity;

import br.com.medvoll.controller.dto.request.AddressRequest;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEntity {

    private String publicPlace;
    private String district;
    private String zipCode;
    private String fu;
    private String complement;
    private String number;

    public AddressEntity(AddressRequest addressRequest) {
        this.publicPlace = addressRequest.publicPlace();
        this.district = addressRequest.district();
        this.zipCode = addressRequest.zipCode();
        this.fu = addressRequest.fu();
        this.complement = addressRequest.complement();
        this.number = addressRequest.number();
    }

    public void updateData(AddressRequest addressRequest) {
        this.publicPlace = addressRequest.publicPlace();
        this.district = addressRequest.district();
        this.zipCode = addressRequest.zipCode();
        this.fu = addressRequest.fu();
        this.complement = addressRequest.complement();
        this.number = addressRequest.number();
    }
}