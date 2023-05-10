/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digideramers.amena.models;

/**
 *
 * @author Ahlem
 */
public class Joke {

    private String id;
    private String value;

    @Override
    public String toString() {
        return "Joke{" + "id=" + id + ", value=" + value + '}';
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Joke(String id, String value) {
        this.id = id;
        this.value = value;
    }

}
