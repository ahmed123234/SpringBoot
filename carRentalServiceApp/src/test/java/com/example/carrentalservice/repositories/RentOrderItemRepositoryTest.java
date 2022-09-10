package com.example.carrentalservice.repositories;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RentOrderItemRepositoryTest {

}

//    @Autowired
//    private RentOrderItemRepository rentOrderItemRepository;
//    @BeforeEach
//    void setUp() {
//        List<RentOrderItem> orderItems = List.of(
//                new RentOrderItem(1L, 1L),
//                new RentOrderItem(2L, 2L),
//                new RentOrderItem(3L, 3L),
//                new RentOrderItem(4L, 4L)
//        );
//        rentOrderItemRepository.saveAll(orderItems);
//    }
//
//    @AfterEach
//    void tearDown() {
//        rentOrderItemRepository.deleteAll();
//    }
//
//    @Test
//    void canGetOrderItems() {
//        //given
//        Long orderId = 1L;
//
//        //when
//        Iterable<Car> actual = rentOrderItemRepository.getOrderItems(orderId);
//
//        AtomicInteger validIdFound = new AtomicInteger();
//        actual.forEach(
//                car -> {
//                    if (car.getCarId() > 0) {
//                        validIdFound.getAndIncrement();
//                    }
//                }
//        );
//        assertThat(validIdFound.intValue()).isEqualTo(3);
//    }
//}