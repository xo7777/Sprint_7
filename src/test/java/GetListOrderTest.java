import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetListOrderTest {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Получение списка заказов без указания id курьера")
    public void getListOrder() {
        OrderSteps orderSteps = new OrderSteps();
        orderSteps.showListOrder()
                .then()
                .assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200);
    }

}
