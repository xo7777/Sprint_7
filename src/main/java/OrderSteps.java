import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Создание нового заказа")
    public Response newOrderCreate(OrderCreateJson orderCreateJson) {
        return given()
                .baseUri(Constants.SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(orderCreateJson)
                .when()
                .post(Constants.CREATE_ORDER);

    }


    @Step("Получение списка заказов")
    public Response showListOrder() {
        return given()
                .baseUri(Constants.SCOOTER_URL)
                .when()
                .get(Constants.GET_ORDER_LIST);


    }

}
