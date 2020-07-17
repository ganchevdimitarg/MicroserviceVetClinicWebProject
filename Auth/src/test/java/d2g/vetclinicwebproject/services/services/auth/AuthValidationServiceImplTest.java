package d2g.vetclinicwebproject.services.services.auth;

//import d2g.vetclinicwebproject.data.models.Doctor;
//import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
//import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.services.base.TestBase;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthValidationServiceImplTest extends TestBase {

//    @MockBean
//    DoctorRepository doctorRepository;
//    @MockBean
//    UserRepository userRepository;
//
//    @Autowired
//    AuthValidationServiceImpl service;
//
//    @Test
//    void isValid_whenPasswordsIsNOTSame_shouldThrowException() {
//        BaseServiceModel baseServiceModel = new BaseServiceModel();
//        baseServiceModel.setPassword("1");
//        baseServiceModel.setConfirmPassword("2");
//        baseServiceModel.setUsername("Iva");
//
//        assertThrows(IllegalAccessException.class, () -> service.isValid(baseServiceModel));
//    }
//
//    @Test
//    void isValid_whenPasswordsIsSameAndUsernameNOTFree_shouldThrowException() {
//        BaseServiceModel baseServiceModel = new BaseServiceModel();
//        baseServiceModel.setPassword("1");
//        baseServiceModel.setConfirmPassword("1");
//        baseServiceModel.setUsername("Ivan");
//
//        Doctor doctor = new Doctor();
//        doctor.setName("Ivan");
//        Mockito.when(doctorRepository.existsDoctorByUsername("Ivan")).thenReturn(true);
//
//        assertThrows(IllegalAccessException.class, () -> service.isValid(baseServiceModel));
//    }
}