package d2g.vetclinicwebproject.services.services.auth;

//import d2g.vetclinicwebproject.data.models.Doctor;
//import d2g.vetclinicwebproject.data.models.IdentificationKey;
//import d2g.vetclinicwebproject.data.models.User;
//import d2g.vetclinicwebproject.data.repositories.DoctorRepository;
//import d2g.vetclinicwebproject.data.repositories.IdentificationKeyRepository;
//import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.services.base.TestBase;
//import d2g.vetclinicwebproject.services.models.DoctorServiceModel;
//import d2g.vetclinicwebproject.services.models.UserServiceModel;


class AuthServiceImplTest extends TestBase {
//    @MockBean
//    AuthValidationServiceImpl validation;
//    @MockBean
//    DoctorRepository doctorRepository;
//    @MockBean
//    UserRepository userRepository;
//    @MockBean
//    IdentificationKeyRepository keyRepository;
//
//    @Autowired
//    AuthService service;
//
//    @Test
//    void registerDoctor_whenDoctorIsValid_shouldThrow() throws IllegalAccessException {
//        String keyName = "aAa2aF";
//        IdentificationKey key = new IdentificationKey();
//        key.setLogKey(keyName);
//        DoctorServiceModel doctorServiceModel = new DoctorServiceModel("Dimitar", "vet",
//                "The best", key.getLogKey());
//        doctorServiceModel.setPassword("11111q");
//        Mockito.when(keyRepository.findByLogKey(keyName)).thenReturn(key);
//        Mockito.when(validation.isValid(doctorServiceModel)).thenReturn(true);
//
//        service.registerDoctor(doctorServiceModel);
//
//        ArgumentCaptor<Doctor> argument = ArgumentCaptor.forClass(Doctor.class);
//        Mockito.verify(doctorRepository).save(argument.capture());
//
//        Doctor doctor = argument.getValue();
//        assertNotNull(doctor);
//    }
//
//    @Test
//    void registerUser_whenUserIsValid_shouldPast() throws IllegalAccessException {
//        UserServiceModel userServiceModel = new UserServiceModel("Dimitar", "dimitar@gmail.com",
//                "Vladislav Varnenchik", "0888888888");
//
//        Mockito.when(validation.isValid(userServiceModel)).thenReturn(true);
//
//        assertThrows(RuntimeException.class, () -> service.registerUser(userServiceModel));
//    }
//
//    @Test
//    void logIn_whenUserLogIn_shouldPast() {
//        String password = "123456q";
//        String name = "ivan";
//
//        User user = new User();
//        user.setUsername("ivan");
//        user.setPassword("123456q");
//
//        Mockito.when(userRepository.findByUsernameAndPassword(name, password)).thenReturn(Optional.of(user));
//
//        assertEquals(name, user.getUsername());
//    }
//
//    @Test
//    void logIn_whenDoctorLogIn_shouldPast() {
//        String password = "123456q";
//        String name = "ivan";
//
//        Doctor doctor = new Doctor();
//        doctor.setUsername(name);
//        doctor.setPassword(password);
//
//        Mockito.when(doctorRepository.findByUsernameAndPassword(name, password)).thenReturn(doctor);
//
//        assertEquals(name, doctor.getUsername());
//    }
}