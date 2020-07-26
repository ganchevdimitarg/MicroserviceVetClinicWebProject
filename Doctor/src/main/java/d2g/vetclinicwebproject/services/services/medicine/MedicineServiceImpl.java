package d2g.vetclinicwebproject.services.services.medicine;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.models.Medicine;
import d2g.vetclinicwebproject.data.repositories.MedicineRepository;
import d2g.vetclinicwebproject.services.models.MedicineServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private static final String INVALID_DATE = "Invalid date";

    private final MedicineRepository medicineRepository;
    private final MedicineServiceValidation validation;
    private final ModelMapper modelMapper;

    @Override
    public void save(MedicineServiceModel model) {
        if (!validation.isMedicineValid(model)){
            throw new IllegalArgumentException(INVALID_DATE);
        }
        medicineRepository.saveAndFlush(modelMapper.map(model, Medicine.class));
    }

    @Override
    public List<MedicineServiceModel> getAll() {
        return medicineRepository.getAll()
                .stream()
                .map(m -> modelMapper.map(m, MedicineServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public MedicineServiceModel findByName(String name) {
        return modelMapper.map(medicineRepository.findByName(name), MedicineServiceModel.class);
    }
}
