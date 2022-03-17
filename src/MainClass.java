public class MainClass {
    public static void main(String[] args) {
        Thread thread = new Thread(MainFrame::new);
        thread.start();
    }
}
