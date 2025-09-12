package scooter.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import scooter.pojo.CourierCreateJson;
import scooter.pojo.CourierLoginIdResponse;
import scooter.pojo.CourierLoginJson;

import static io.restassured.RestAssured.given;
import static scooter.Constants.*;

public class CourierSteps {
    @Step("Создание курьера")
    public Response courierCreate(CourierCreateJson courierCreateJson) {
        return given()
                .baseUri(SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(courierCreateJson)
                .when()
                .post(COURIER_CREATE);

    }
    @Step("Логин курьера")
    public Response courierLogin(CourierLoginJson courierLoginJson) {
        return given()
                .baseUri(SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(courierLoginJson)
                .when()
                .post(COURIER_LOGIN);

    }
    @Step("Удаление курьера")
    public void courierDelete(int courierId) {
        given()
                .baseUri(SCOOTER_URL)
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_DELETE + courierId);

    }

    @Step("Получение id курьера")
    public int getIdCourier(CourierLoginJson courierLoginJson) {
        Response response = courierLogin(courierLoginJson)
                .then().extract().response();
        CourierLoginIdResponse courierLoginIdResponse = response.as(CourierLoginIdResponse.class);
        int id = courierLoginIdResponse.getId();
        return id;
    }

}
