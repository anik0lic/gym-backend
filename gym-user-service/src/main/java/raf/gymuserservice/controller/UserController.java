package raf.gymuserservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.gymuserservice.dto.TokenRequestDto;
import raf.gymuserservice.dto.TokenResponseDto;
import raf.gymuserservice.dto.UserDto;
import raf.gymuserservice.secutiry.CheckSecurity;
import raf.gymuserservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all users")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllClients(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }
}