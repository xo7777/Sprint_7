import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import scooter.pojo.CourierCreateJson;
import scooter.pojo.CourierLoginJson;
import scooter.Constants;
import scooter.steps.CourierSteps;


import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateTest {
    private String login = "ahmedjhon198881";
    private String password = "123658";
    private String firstName = "molnyamcquin";
    private int courierId;
    private CourierSteps courierSteps;




    @Test
    @DisplayName("Создание курьера")
    @Description("Успешное создание курьера")

    public void createNewCourier() {
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, password, firstName);
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        int courierId = courierSteps.getIdCourier(courierLoginJson);
    }

    @Test
    @DisplayName("Создание двух курьеров с одинаковыми логинами")
    @Description("Успешное создание курьера и ошибка при создании второго курьера с таким же логином")

    public void createCourierWithSameLogin() {
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, password, firstName);
        CourierLoginJson courierLoginJson = new CourierLoginJson(login, password);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_LOGIN_IS_BUSY))
                .and()
                .statusCode(409);
        int courierId = courierSteps.getIdCourier(courierLoginJson);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Появление ошибки при создании курьера без логина")

    public void createCourierWithoutLogin() {
        CourierCreateJson courierCreateJson = new CourierCreateJson("", password, firstName);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_CREATE_ACCOUNT))
                .and()
                .statusCode(400);
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Появление ошибки при создании курьера без пароля")
    public void createCourierWithoutPassword() {
        CourierCreateJson courierCreateJson = new CourierCreateJson(login, "", firstName);
        CourierSteps courierSteps = new CourierSteps();
        courierSteps.courierCreate(courierCreateJson)
                .then()
                .assertThat().body("message", equalTo(Constants.MESSAGE_NOT_ENOUGH_DATA_FOR_CREATE_ACCOUNT))
                .and()
                .statusCode(400);
    }


    @After
    public void deleteData(){

        if (courierId != 0){
        courierSteps.courierDelete(courierId);
        }

    }
}
