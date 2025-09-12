import scooter.pojo.CourierCreateJson;
import scooter.pojo.CourierLoginJson;
import scooter.Constants;
import scooter.steps.CourierSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    public static String login = "ahmedjhon198881";
    public static String password = "123658";
    public static String firstName = "molnyamcquin";


    @Test
    @DisplayName("Логин курьера")
    @Description("Успешная авторизация курьера")

    public void courierLogin() {
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, password, firstName);
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson);
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
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, password, firstName);
        CourierLoginJson courierLoginJsonWithOutLoginJson = new CourierLoginJson("", password);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson);
        courierSteps.courierLogin(courierLoginJsonWithOutLoginJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_ENTRY))
                .and()
                .statusCode(400);
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);

    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Ошибка при авторизации курьера без пароля")
    public void courierLoginWithoutPassword() {
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, password, firstName);
        CourierLoginJson courierLoginJsonWithOutPasswordJson = new CourierLoginJson(login, "");
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson);
        courierSteps.courierLogin(courierLoginJsonWithOutPasswordJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_ENTRY))
                .and()
                .statusCode(400);
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);

    }


    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Ошибка при авторизации несуществующего курьера")
    public void courierLoginWithNonExistentLogin () {
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierLogin(courierLoginJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_NOT_FOUND_ACCOUNT))
                .and()
                .statusCode(404);
    }

}
