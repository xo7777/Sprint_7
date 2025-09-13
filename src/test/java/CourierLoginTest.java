import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scooter.pojo.CourierCreateJson;
import scooter.pojo.CourierLoginJson;
import scooter.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static scooter.Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_ENTRY;
import static scooter.Constants.MESSAGE_NOT_FOUND_ACCOUNT;

public class CourierLoginTest {
    public static String login = "ahmedjhon199933";
    public static String password = "123658";
    public static String firstName = "molnyamcquin";
    public static CourierSteps courierSteps;
    public static CourierLoginJson courierLoginJson;
    public static CourierCreateJson courierCreateJson;
    public static int courierId;
    public static Response courierCreate;

    @Before
    public void createCourier() {
        courierCreateJson = new CourierCreateJson(login, password, firstName);
        courierLoginJson = new CourierLoginJson(login, password);
        courierSteps = new CourierSteps();
        courierCreate = courierSteps.courierCreate(courierCreateJson);
        courierId = courierSteps.getIdCourier(courierLoginJson);
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Успешная авторизация курьера")

    public void courierLogin() {

        courierSteps.courierLogin(courierLoginJson)
                .then()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Ошибка при авторизации курьера без логина")
    public void courierLoginWithoutLogin() {
        courierLoginJson.setLogin("");
        courierSteps.courierLogin(courierLoginJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA_FOR_ENTRY))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Ошибка при авторизации курьера без пароля")
    public void courierLoginWithoutPassword() {
        courierLoginJson.setPassword("");
        courierSteps.courierLogin(courierLoginJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA_FOR_ENTRY))
                .and()
                .statusCode(400);
    }


    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Ошибка при авторизации несуществующего курьера")
    public void courierLoginWithNonExistentLogin() {
        courierLoginJson.setLogin("fdkfdf");
        courierLoginJson.setPassword("12354");
        courierSteps.courierLogin(courierLoginJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_NOT_FOUND_ACCOUNT))
                .and()
                .statusCode(404);
    }

    @After
    public void deleteData() {
        courierSteps.courierDelete(courierId);
    }
}