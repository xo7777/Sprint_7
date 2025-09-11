import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)

public class OrderCreateTest {
    private List<String> color;

    public OrderCreateTest(List<String> color) {
        this.color = color;
    }
    @Parameterized.Parameters (name = "Цвет самоката - {0}")
    public static Object[][] testData() {
        return new Object[][] {
                {List.of("BLACK", "GREY")},
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of()}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа c разнцми цветами самоката")
    public void orderCreate() {
        OrderCreateJson orderCreateJson = new OrderCreateJson(color);
        OrderSteps orderSteps = new OrderSteps();
        orderSteps.newOrderCreate(orderCreateJson)
                .then()
                .assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }
}