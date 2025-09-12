import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scooter.pojo.CourierCreateJson;
import scooter.pojo.CourierLoginJson;
import scooter.steps.CourierSteps;


import static org.hamcrest.CoreMatchers.equalTo;
import static scooter.Constants.MESSAGE_LOGIN_IS_BUSY;
import static scooter.Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_CREATE_ACCOUNT;

public class CourierCreateTest {
    public static String login = "ahmedjhon198881";
    public static String password = "123658";
    public static String firstName = "molnyamcquin";
    public static CourierSteps courierSteps;
    public static CourierLoginJson courierLoginJson;
    public static CourierCreateJson courierCreateJson;
    public static int courierId;

    @Before
    public void createCourier(){
        courierCreateJson = new CourierCreateJson(login, password, firstName);
        courierLoginJson = new CourierLoginJson(login, password);
        courierSteps = new CourierSteps();
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Успешное создание курьера")

    public void createNewCourier() {

        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        courierId = courierSteps.getIdCourier(courierLoginJson);
    }

    @Test
    @DisplayName("Создание двух курьеров с одинаковыми логинами")
    @Description("Успешное создание курьера и ошибка при создании второго курьера с таким же логином")

    public void createCourierWithSameLogin() {

        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_LOGIN_IS_BUSY))
                .and()
                .statusCode(409);
        courierId = courierSteps.getIdCourier(courierLoginJson);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Появление ошибки при создании курьера без логина")

    public void createCourierWithoutLogin() {
        courierCreateJson.setLogin("");
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA_FOR_CREATE_ACCOUNT))
                .and()
                .statusCode(400);
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Появление ошибки при создании курьера без пароля")
    public void createCourierWithoutPassword() {
        courierCreateJson.setPassword("");
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(MESSAGE_NOT_ENOUGH_DATA_FOR_CREATE_ACCOUNT))
                .and()
                .statusCode(400);
    }
@After
public void deleteData(){
        courierSteps.courierDelete(courierId);
    }

}