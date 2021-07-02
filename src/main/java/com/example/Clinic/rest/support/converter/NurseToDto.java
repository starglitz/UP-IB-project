package com.example.Clinic.rest.support.converter;

import com.example.Clinic.model.Nurse;
import com.example.Clinic.model.User;
import com.example.Clinic.rest.support.dto.NurseDto;
import com.example.Clinic.rest.support.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class NurseToDto implements Converter<Nurse, NurseDto> {

    @Autowired
    private ClinicToDto clinicToDto;

    @Autowired
    private UserToDto userToDto;

    @Override
    public NurseDto convert(Nurse source) {

        NurseDto retVal = new NurseDto();

        retVal.setId(source.getId());
        retVal.setUser(userToDto.convert(source.getUser()));
        if(source.getClinic() != null) {
            retVal.setClinic(clinicToDto.convert(source.getClinic()));
        }
        return retVal;
    }

    public List<NurseDto> convert(List<Nurse> source) {
        List<NurseDto> ret = new ArrayList();
        Iterator var3 = source.iterator();

        while(var3.hasNext()) {
            Nurse z = (Nurse) var3.next();
            ret.add(this.convert(z));
        }

        return ret;
    }
}
