package scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.pojo.OrderCreateJson;

import static io.restassured.RestAssured.given;
import static scooter.Constants.*;

public class OrderSteps {
    @Step("Создание нового заказа")
    public Response newOrderCreate(OrderCreateJson orderCreateJson) {
        return given()
                .baseUri(SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(orderCreateJson)
                .when()
                .post(CREATE_ORDER);

    }


    @Step("Получение списка заказов")
    public Response showListOrder() {
        return given()
                .baseUri(SCOOTER_URL)
                .when()
                .get(GET_ORDER_LIST);


    }

}
