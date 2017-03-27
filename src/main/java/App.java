import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/entries/:entryId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Entry entry = Entry.find(Integer.parseInt(request.params(":entryId")));
      model.put("entry", entry);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/entries", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      String mail = request.queryParams("mail");
      String email = request.queryParams("email");
      Entry entry = new Entry(name, phone, mail, email);
      model.put("entry", entry);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
