public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.random();
        //graph.add();

        System.out.println("The column length is " + graph.getCol());
        System.out.println("The row length is " + graph.getRow());

        graph.view();
        graph.random();
        graph.view();
    }
}