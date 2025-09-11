import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    @Step("Создание курьера")
    public Response courierCreate(CourierCreateJson courierCreateJson) {
        return given()
                .baseUri(Constants.SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(courierCreateJson)
                .when()
                .post(Constants.COURIER_CREATE);

    }
    @Step("Логин курьера")
    public Response courierLogin(CourierLoginJson courierLoginJson) {
        return given()
                .baseUri(Constants.SCOOTER_URL)
                .header("Content-type", "application/json")
                .body(courierLoginJson)
                .when()
                .post(Constants.COURIER_LOGIN);

    }
    @Step("Удаление курьера")
    public void courierDelete(int courierId) {
        given()
                .baseUri(Constants.SCOOTER_URL)
                .header("Content-type", "application/json")
                .when()
                .delete(Constants.COURIER_DELETE + courierId);

    }

    @Step("Получение id курьера и удаление по id")
    public void getIdAndDeleteCourier(CourierLoginJson courierDeleteAfterLogin) {
        Response response = courierLogin(courierDeleteAfterLogin)
                .then().extract().response();
        CourierLoginIdResponse courierLoginIdResponse = response.as(CourierLoginIdResponse.class);
        int courierId = courierLoginIdResponse.getId();
        courierDelete(courierId);
    }

}
