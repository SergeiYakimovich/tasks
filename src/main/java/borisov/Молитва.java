package borisov;

@Foreign
public class Молитва implements Лечение {
    @Override
    public void применить() {
        System.out.println("Молитва");
    }
}
