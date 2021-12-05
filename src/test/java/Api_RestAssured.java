import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Api_RestAssured {

    @Test
    public void getRequest_Registered_Member(){

        baseURI="http://localhost:3000";
        given().queryParam("Occupation","Dentist")
                .when()
                .get("/Registered_Member")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }
    @Test
    public void postRequest_Registered_Member(){

        baseURI="http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("firstName", "Jeff");
        request.put("lastName","Bezos");
        request.put("phone","111-11-111");
        request.put("Occupation","overRich");
        System.out.println(request);
        System.out.println(request.toString());

        given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .body(request.toString())
                .when()
                .post("/Registered_Member")
                .then()
                .statusCode(201);
    }

    @Test
    public void updateRequest_Registered_Member(){
        baseURI="http://localhost:3000";
        JSONObject request = new JSONObject();

        request.put("firstName", "Duygu");
        request.put("lastName","Ozdemir");
        request.put("phone","222-44-044405");
        request.put("Occupation","is not Agent");

        System.out.println(request);
        System.out.println(request.toString());

        given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .body(request.toString())
                .when()
                .put("/Registered_Member")
                .then()
                .statusCode(200);
                // .log.all();
    }
    @Test
    public void deleteRequest_Registered_Member(){

        baseURI="http://localhost:3000";

        given().queryParam("id","100")
                .when()
                .put("/Registered_Member")
                .then()
                .statusCode(204);
             // .log.all();
    }
}