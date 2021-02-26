package com.gams.storesystem.services.validation;

import com.gams.storesystem.domain.Client;
import com.gams.storesystem.domain.enums.TypeClient;
import com.gams.storesystem.dto.ClientNewDto;
import com.gams.storesystem.repositories.ClientRepository;
import com.gams.storesystem.resources.exception.FieldMessage;
import com.gams.storesystem.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDto> {

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(ClientNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getType().equals(TypeClient.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF invalid"));
        }
        if(objDto.getType().equals(TypeClient.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ invalid"));
        }

        Client aux = repo.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Existing email"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
