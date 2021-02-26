package com.gams.storesystem.services.validation;

import com.gams.storesystem.domain.Client;
import com.gams.storesystem.dto.ClientDTO;
import com.gams.storesystem.repositories.ClientRepository;
import com.gams.storesystem.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request; //restore id client url

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientUpdate ann) {
    }
    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked") //stop warning in class
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id")); //restore id client url

        List<FieldMessage> list = new ArrayList<>();

        Client aux = repo.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
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
