package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.MedicineServiceModel;
import d2g.vetclinicwebproject.services.services.medicine.MedicineService;
import d2g.vetclinicwebproject.web.api.models.medicine.MedicineApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class MedicineApiController {
    private final ModelMapper modelMapper;
    private final MedicineService medicineService;

    @GetMapping("/medicines")
    public ResponseEntity<List<MedicineApiControllerModel>> getAllMedicines() {
        List<MedicineApiControllerModel> medicines = medicineService.getAll()
                .stream()
                .map(m -> modelMapper.map(m, MedicineApiControllerModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @PostMapping("/add-medicine")
    private ResponseEntity<Void> addMedicine(@RequestBody MedicineApiControllerModel medicine){
        medicineService.save(modelMapper.map(medicine, MedicineServiceModel.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
