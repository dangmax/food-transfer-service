package demo.rest;

import demo.domain.PaymentRepository;
import org.junit.Before;

import static org.mockito.Mockito.mock;


public class FoodPaymentControllerTest {
    private PaymentRepository paymentRepository;
    private FoodPaymentController foodPaymentController;

    @Before
    public void setupMock(){

        this.paymentRepository = mock(PaymentRepository.class);

    }




}
