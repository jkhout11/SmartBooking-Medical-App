package com.techelevator.controller;

import com.techelevator.dao.OfficeInfoDao;
import com.techelevator.model.DoctorOfficeDto;
import com.techelevator.model.OfficeInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/office-info")
public class OfficeInfoController {

    private final OfficeInfoDao officeInfoDao;


    public OfficeInfoController(OfficeInfoDao officeInfoDao) {
        this.officeInfoDao = officeInfoDao;
    }

    // Working
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<OfficeInfo> getOffices() {
        List officeInfo = officeInfoDao.getOfficeLists();
//        if (officeInfo != null) {
//
//        } else {
//            System.out.println("No Offices Found");
//        }
        return officeInfo;
    }


//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    public OfficeInfo getOfficeInfoById(@PathVariable int id) {
//        OfficeInfo officeInfo= officeInfoDao.getOfficeById(id);
//        if (officeInfo != null) {
//        } else {
//            System.out.println("No Office Found For Selected ID");
//        }
//        return officeInfo;
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OfficeInfo getOfficeInfoById(@PathVariable int id, Principal principal) {
        if(id==0){

            DoctorOfficeDto doctorOfficeDto = officeInfoDao.getOfficeIdByUserId(principal);
            id=doctorOfficeDto.getOfficeId();
        }

        OfficeInfo officeInfo = officeInfoDao.getOfficeById(id);
        if (officeInfo != null) {

        } else {
            System.out.println("No Office Found For Selected ID");
        }
        return officeInfo;
    }



    // Working
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public OfficeInfo createOffice(@Valid @RequestBody OfficeInfo office) {
        return officeInfoDao.createOffice(office);
    }

    // Working
    
    @PutMapping("/update/{id}")
    public ResponseEntity<OfficeInfo> updateOffice(@ Valid @PathVariable int id, @RequestBody OfficeInfo officeInfo) {
        officeInfo.setId(id);
        OfficeInfo updatedOffice = officeInfoDao.updateOffice(officeInfo);
        if (updatedOffice != null) {
            return new ResponseEntity<>(updatedOffice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
