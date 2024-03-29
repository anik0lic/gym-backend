package raf.gymuserservice.mapper;

import raf.gymuserservice.domain.Client;
import raf.gymuserservice.domain.Manager;
import raf.gymuserservice.domain.User;
//import raf.gymuserservice.dto.UserCreateDto;
import raf.gymuserservice.dto.*;
import raf.gymuserservice.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setBan(user.isBan());
        return userDto;
    }

    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setNumberOfReservations(client.getNumberOfReservations());
        return clientDto;
    }

    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setGym(manager.getGym());
        return managerDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setRole(roleRepository.findRoleByName("ROLE_CLIENT").get());
        client.setDateOfBirth(clientCreateDto.getDateOfBirth());
        client.setNumberOfReservations(0);
        client.setBan(true);
        return client;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setRole(roleRepository.findRoleByName("ROLE_MANAGER").get());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setGym(managerCreateDto.getGym());
        manager.setBan(true);
        return manager;
    }

    public UpdateDto userToUpdateDto(User user){
        UpdateDto updateDto = new UpdateDto();
        updateDto.setEmail(user.getEmail());
        updateDto.setFirstName(user.getFirstName());
        updateDto.setLastName(user.getLastName());
        updateDto.setUsername(user.getUsername());
        updateDto.setDateOfBirth(user.getDateOfBirth());
        return updateDto;
    }
}
