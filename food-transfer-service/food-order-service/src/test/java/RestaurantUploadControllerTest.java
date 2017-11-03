import demo.model.DishOrderRepository;
import demo.model.Restaurant;
import demo.rest.RestaurantUploadController;
import demo.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



public class RestaurantUploadControllerTest {
    private RestaurantService service;
    private DishOrderRepository repository;
	private RestaurantUploadController controller;
	private List<Restaurant> inputDatas;

    @Before
    public void setupMock(){
        this.service = mock(RestaurantService.class);
		this.repository = mock(DishOrderRepository.class);
		controller = new RestaurantUploadController(repository,service);
		//init input data
		inputDatas = new ArrayList<Restaurant>();
//        inputDatas.add(new Restaurant(1,"r1", "beijing","123"));
//		inputDatas.add(new Restaurant(2,"r2", "tianjing","456"));
//		inputDatas.add(new Restaurant(3,"r3", "nanjing","789"));
    }

    @Test
    public void whenbulkUploadAndSaved_expectSuccess() {
		//define service output
        when(service.saveRestaurants(inputDatas)).thenReturn(inputDatas);
		//assert controller output 
        assertEquals(0, controller.bulkUpload(inputDatas).size());
    }
	


}
