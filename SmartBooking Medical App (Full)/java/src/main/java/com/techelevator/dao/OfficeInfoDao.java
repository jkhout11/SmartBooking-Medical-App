package com.techelevator.dao;

import com.techelevator.model.DoctorOfficeDto;
import com.techelevator.model.OfficeInfo;

import java.security.Principal;
import java.util.List;

public interface OfficeInfoDao {

    List<OfficeInfo> getOfficeLists();

    OfficeInfo getOfficeById(int id);

    OfficeInfo createOffice(OfficeInfo office);

    OfficeInfo updateOffice(OfficeInfo office);

    DoctorOfficeDto getOfficeIdByUserId(Principal principal);


}
