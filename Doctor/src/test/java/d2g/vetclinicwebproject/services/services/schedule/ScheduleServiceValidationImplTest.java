package d2g.vetclinicwebproject.services.services.schedule;

import d2g.vetclinicwebproject.services.base.TestBase;
import d2g.vetclinicwebproject.services.models.ScheduleServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ScheduleServiceValidationImplTest extends TestBase {

    @Autowired
    ScheduleServiceValidation validation;

    @Test
    void isValidSchedule_whenDataIsValid_shouldReturnTrue() {
        ScheduleServiceModel schedule = new ScheduleServiceModel("1", "16", "Ivan", "Max", true);

        assertTrue(validation.isValidSchedule(schedule));
    }

    @Test
    void isValidSchedule_whenDataIsNotValid_shouldReturnTrue() {
        ScheduleServiceModel schedule = new ScheduleServiceModel("1", "", "Ivan", "Max", true);

        assertFalse(validation.isValidSchedule(schedule));
    }
}