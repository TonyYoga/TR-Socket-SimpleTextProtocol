package testapp;

import app.core.Person;
import app.core.PersonClient;
import com.telran.view.ConsoleInputOutput;
import com.telran.view.InputOutput;
import com.telran.view.Item;
import com.telran.view.Menu;

import java.io.IOException;

public class clientApp {
    public static void main(String[] args) throws IOException {
        try (PersonClient client = new PersonClient("127.0.0.1")){
            InputOutput cio = new ConsoleInputOutput();
            Menu menu = new Menu("Person Client Menu",
                    Item.of("Add new person", io -> {
                        int id = cio.readInt("Type Id ");
                        String fName = cio.readString("Type name ");
                        String lName = cio.readString("Type lastname ");
                        String phone = cio.readString("Type phone ");
                        String res = client.sendAddPersonRequest(Person.of(id, fName, lName, phone));
                        cio.writeLine(res);
                    }),
                    Item.of("Remove person by id", io -> {
                        int id = cio.readInt("Type Id ");
                        String res = client.sendRemovePerson(id);
                        cio.writeLine(res);
                    }),
                    Item.of("Get person by id", io -> {
                        int id = cio.readInt("Type Id ");
                        Person toGet = client.sendGetPersonById(id);
                        String res = toGet != null ? toGet.toString() : String.format("Person with id %d wasn't found", id);
                        cio.writeLine(res);
                    }),
                    Item.exit());
            menu.perform(cio);
        }
    }
}
