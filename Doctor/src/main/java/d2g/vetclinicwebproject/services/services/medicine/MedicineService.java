package d2g.vetclinicwebproject.services.services.medicine;

import d2g.vetclinicwebproject.services.models.MedicineServiceModel;

import java.util.List;

public interface MedicineService {
    void save(MedicineServiceModel model);

    List<MedicineServiceModel> getAll();

    MedicineServiceModel findByName(String name);
}
