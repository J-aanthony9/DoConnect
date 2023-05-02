package cogent.university.com.DoConnectBackend.controller;

import cogent.university.com.DoConnectBackend.dto.JwtResponse;
import cogent.university.com.DoConnectBackend.dto.LoginRequest;
import cogent.university.com.DoConnectBackend.dto.MessageResponse;
import cogent.university.com.DoConnectBackend.dto.SignUpRequest;
import cogent.university.com.DoConnectBackend.entity.Role;
import cogent.university.com.DoConnectBackend.entity.User;
import cogent.university.com.DoConnectBackend.repository.RoleRepository;
import cogent.university.com.DoConnectBackend.repository.UserRepository;
import cogent.university.com.DoConnectBackend.roles.ERole;
import cogent.university.com.DoConnectBackend.security.JwtUtils;
import cogent.university.com.DoConnectBackend.security.UserDetailsImpl;
import cogent.university.com.DoConnectBackend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtil;

//        @PostMapping("/authenticate")
//    public ResponseEntity<?> generateToken(@RequestBody AuthRequest request) throws Exception{
//
//
//        return ResponseEntity.ok(new JwtResponse(null)
//    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if (role.equals("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    //Add user
//    @PostMapping("/addUser")
//    public User addNewUser(@RequestBody User user){
//        return userService.addNewUser(user);
//    }

    //Get All Users
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    //Get User By ID
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);

    }

    //Get User By Name
    @GetMapping("/getByName/{name}")
    public User getUserByName(@PathVariable("name") String name){
        return userService.getByName(name);
    }

    //Get User By type
//    @GetMapping("/getAllByUserType/{userType}")
//    public List<User> getAllByUserType(@PathVariable("userType") String userType){
//        return userService.getAllByUserType(userType);
//    }

    //Update User
    @PutMapping("/updateUser/{id}")
    public User updateUserById(@RequestBody User user,
                               @PathVariable("id") int id){
        return userService.updateUser(user, id);
    }


}
