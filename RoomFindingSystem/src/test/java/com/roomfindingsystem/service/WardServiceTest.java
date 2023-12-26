package com.roomfindingsystem.service;

import com.roomfindingsystem.repository.WardRepository;
import com.roomfindingsystem.service.impl.WardServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WardServiceTest {
    @Mock
    WardRepository wardRepository;

    @InjectMocks
    WardServiceImpl wardService;


}
