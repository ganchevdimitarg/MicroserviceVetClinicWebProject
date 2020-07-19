package d2g.vetclinicwebproject.services.services;


import d2g.vetclinicwebproject.services.models.*;

import java.util.List;

public interface DoctorService {
    DoctorServiceModel getDoctorHome(String username);

    List<ScheduleServiceModel> getSchedule();

    void addSchedule(AddScheduleServiceModel model);

    void addTreatmentToAnimal(AddTreatmentServiceModel model, String scheduleId);

    void deleteSchedule(String scheduleId, AddScheduleServiceModel model);

    List<MedicineServiceModel> getMedicine();

    void addMedicine(MedicineServiceModel model);
}
